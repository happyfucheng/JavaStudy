package study.zookeeper.examples;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @ClassName: ZkClientExamples
 * @Description: 直接使用zk的api实现业务功能比较繁琐。因为要处理session loss，session expire等异常，
 *               在发生这些异常后进行重连。又因为ZK的watcher是一次性的，如果要基于wather实现发布/订阅模式，
 *               还要自己包装一下，将一次性订阅包装成持久订阅。另外如果要使用抽象级别更高的功能，比如分布式锁，leader选举等，
 *               还要自己额外做很多事情。
 * @author BIC wanghz5@asiainfo.com
 * @date 2015-8-17 下午5:22:24
 * 
 */
public class ZkClientMaster {

	private ZkClient zkCli;
	static ExecutorService es = Executors.newFixedThreadPool(1);

	public ZkClient getZkCli() {
		if (zkCli == null) {
			zkCli = new ZkClient("10.1.54.75:2181", 50000);
		}
		return zkCli;
	}

	public static void main(String[] args) {
		ZkClientMaster zkMaster = new ZkClientMaster();
		ZkClient zkCli = zkMaster.getZkCli();

		if (!zkCli.exists("/root1")) {
			zkCli.create("/root1", new Long(System.currentTimeMillis()), CreateMode.EPHEMERAL);
		}
		
		Runnable run = zkMaster.new RootNodeChangeThread();
		
		es.execute(run);
	}
	
	private class RootNodeChangeThread implements Runnable
	{
        public void run() {
            while(true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //ignore
                }
                
                String uuidStr=UUID.randomUUID().toString();    
                System.out.println("master >>>>>>>>>> 产生随机的 uuid string,'uuidStr'===>"+uuidStr);
                zkCli.writeData("/root1", uuidStr);
            }
        }
    }
}

