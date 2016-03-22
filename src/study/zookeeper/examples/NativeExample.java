package study.zookeeper.examples;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @ClassName: NativeExample
 * @Description: zookeeper原始API用法，主要的操作就是对znode的增删改操作，监听znode的变化以及处理
 * 1.配置管理：集中式的配置管理在应用集群中是非常常见的，一般商业公司内部都会实现一套集中的配置管理中心，
 * 应对不同的应用集群对于共享各自配置的需求，并且在配置变更时能够通知到集群中的每一个机器。
 * 2：集群管理：应用集群中，我们常常需要让每一个机器知道集群中（或依赖的其他某一个集群）哪些机器是活着的，
 * 并且在集群机器因为宕机，网络断链等原因能够不在人工介入的情况下迅速通知到每一个机器。
 * @author BIC wanghz5@asiainfo.com
 * @date 2015-8-17 下午2:48:56
 * 
 */
public class NativeExample {
	
	public static class myWatcher implements Watcher
	{
		private CountDownLatch countDown;
		
		public myWatcher(CountDownLatch cl)
		{
			this.countDown = cl;
		}

		public void process(WatchedEvent event) {
			// dosomething
			if (event.getState() == KeeperState.SyncConnected) {  
				countDown.countDown();  
	           }  
			System.out.println(event.getPath() + "||" + event.getType());
			
		}
		
	}

	public static void main(String[] args) {
		try {
			//定义一个计数器，在zookeeper连接完毕后再进行其它操作
			CountDownLatch cl = new CountDownLatch(1);
			
			// 创建一个Zookeeper实例，第一个参数为目标服务器地址和端口，第二个参数为Session超时时间，第三个为节点变化时的回调方法
			ZooKeeper zk = new ZooKeeper("10.1.51.66:2181", 5000, new myWatcher(cl));
			
			cl.await();
			// 创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)
			zk.create("/root", "mydata".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

			zk.exists("/root/childone", false);
			// 在root下面创建一个childone znode,数据为childone,不进行ACL权限控制，节点为永久性的
			zk.create("/root/childone", "mychildata1".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/root/childtwo", "mychildata2".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/root/childthree", "mychildata3".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/root/childthree", "mychildata3".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			zk.create("/root/childfour", "mychildata4".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			zk.create("/root/childfive", "mychildata".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			zk.create("/root/childsix", "mychildata".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

			// 取得/root节点下的子节点名称,返回List<String>
			zk.getChildren("/root", true);

			// 取得/root/childone节点下的数据,返回byte[]
			zk.getData("/root/childone", true, null);

			// 修改节点/root/childone下的数据，第三个参数为版本，如果是-1，那会无视被修改的数据版本，直接改掉
			zk.setData("/root/childone", "childonemodify".getBytes(), -1);

			// 删除/root/childone这个节点，第二个参数为版本，－1的话直接删除，无视版本
			zk.delete("/root/childone", -1);
			

			// 关闭session
			zk.close();

		}catch(KeeperException.SessionExpiredException ke)
		{
			ke.printStackTrace();
		}catch (KeeperException.ConnectionLossException ke) {
			ke.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
