package study.zookeeper.distributedlock;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @ClassName: NativeDistributedLock
 * @Description: TODO
 * @author BIC wanghz5@asiainfo.com
 * @date 2015-8-18 下午4:34:55
 * 
 */
public class NativeDistributedLock {

	// 超时时间
	private static final int SESSION_TIMEOUT = 50000;
	// zookeeper server列表
	private String hosts = "10.1.54.114:2181";

	private ZooKeeper zk = null;

	private CountDownLatch latch = new CountDownLatch(1);
	
	public ZooKeeper getZKConnection() throws Exception
	{
		if(zk == null)
		{
			this.connectZookeeper();
		}
		
		return zk;
	}

	/**
	 * 连接zookeeper
	 */
	public void connectZookeeper() throws Exception {  
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, new Watcher() {  
            public void process(WatchedEvent event) {  
                try {  
                    // 连接建立时, 打开latch, 唤醒wait在该latch上的线程  
                    if (event.getState() == KeeperState.SyncConnected) {  
                        latch.countDown();  
                    }  
  
                    // 发生了waitPath的删除事件  
//                    if (event.getType() == EventType.NodeDeleted && event.getPath().equals(waitPath)) {  
                    if (event.getType() == EventType.NodeDeleted) {  
                        System.out.println("-------->zookeeper 监控器监控到节点删除[" + Calendar.getInstance().getTime().toString() + ":" + event.getPath() + "]"); 
                    }  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
  
        // 等待连接建立  
        latch.await();  
    }
	
	private void doSomething(int i, String path) throws Exception {
		try {
			System.out.println("i am " + i + ", i gain lock: " + path);
			Thread.sleep(5000);
			// do something
		} finally {
			System.out.println("i am " + i + ", i finished: " + path);
			// 将thisPath删除, 监听thisPath的client将获得通知
			// 相当于释放锁
			zk.delete(path, -1);
		}
	}
	
	public static class DistributedClient extends Thread
	{
		private ZooKeeper _zk;
		private NativeDistributedLock _lock;
		private int _i;
		private String groupNode = "locks";
		private String subNode = "iwantu";
		// 当前client创建的子节点
		private String thisPath;
		// 当前client等待的子节点
		private String waitPath;
		
		public DistributedClient(int i, ZooKeeper zk, NativeDistributedLock lock)
		{
			this._zk = zk;
			this._lock = lock;
			this._i = i;
		}
		public void iwantthelock()
		{
			try {
		        // 创建子节点  
				thisPath = _zk.create("/" + groupNode + "/" + subNode, null, Ids.OPEN_ACL_UNSAFE,  
		                CreateMode.EPHEMERAL_SEQUENTIAL);  
		  
		        // wait一小会, 让结果更清晰一些  
		        Thread.sleep(1000);  
		  
		        // 注意, 没有必要监听"/locks"的子节点的变化情况  
		        List childrenNodes = _zk.getChildren("/" + groupNode, false);  
		  
		        // 列表中只有一个子节点, 那肯定就是thisPath, 说明client获得锁  
		        if (childrenNodes.size() == 1) {  
		        	_lock.doSomething(_i, thisPath);  
		        } else {  
		            String thisNode = thisPath.substring(("/" + groupNode + "/").length());  
		            // 排序  
		            Collections.sort(childrenNodes);  
		            int index = childrenNodes.indexOf(thisNode);  
		            if (index == -1) {  
		                // never happened  
		            } else if (index == 0) {  
		                // inddx == 0, 说明thisNode在列表中最小, 当前client获得锁  
		            	_lock.doSomething(_i, thisPath);   
		            } else {  
		                // 获得排名比thisPath前1位的节点  
		                this.waitPath = "/" + groupNode + "/" + childrenNodes.get(index - 1);  
		                // 在waitPath上注册监听器, 当waitPath被删除时, zookeeper会回调监听器的process方法  
		                _zk.getData(waitPath, new Watcher() {  
		                    public void process(WatchedEvent event) {  
		                        try {  
		                            // 发生了waitPath的删除事件  
		                            if (event.getType() == EventType.NodeDeleted && event.getPath().equals(waitPath)) {  
		                                System.out.println("-------->zookeeper 监控器监控到节点删除[" + Calendar.getInstance().getTime().toString() + ":" + event.getPath() + "]");
		                                _lock.doSomething(_i, thisPath);
		                            }  
		                        } catch (Exception e) {  
		                            e.printStackTrace();  
		                        }  
		                    }  
		                }, new Stat());  
		            }  
		        }  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run()
		{
			System.out.println("小" + _i + "开始争取lock！");
			iwantthelock();
		}
	}

	public static void main(String[] args) throws Exception {
		NativeDistributedLock dl = new NativeDistributedLock();
		ZooKeeper zk = dl.getZKConnection();
		zk.create("/locks", null, Ids.OPEN_ACL_UNSAFE,  CreateMode.PERSISTENT);  
		
		System.out.println("zookeeper连接完毕！");
		
		for (int i = 0; i < 10; i++) {
			new DistributedClient(i,zk,dl).start();
		}
		
		Thread.sleep(Long.MAX_VALUE);
	}
}
