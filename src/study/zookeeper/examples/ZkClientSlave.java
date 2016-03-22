package study.zookeeper.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/** 
 * @ClassName: ZkClientSlave 
 * @Description: TODO
 * @author BIC wanghz5@asiainfo.com 
 * @date 2015-8-17 下午5:48:25 
 *  
 */
public class ZkClientSlave {
	
	private ZkClient zkCli;
	static ExecutorService es = Executors.newFixedThreadPool(1);

	public ZkClient getZkCli() {
		if (zkCli == null) {
			zkCli = new ZkClient("10.1.51.66:2181", 50000);
		}
		return zkCli;
	}
	
	public void monitor()
	{
		es.execute(new Runnable() {
	         public void run() {
	        	 zkCli.subscribeDataChanges("/root1", new IZkDataListener() {
	                 public void handleDataDeleted(String dataPath) throws Exception {
	                     System.out.println("the node 'dataPath'===>" + dataPath);    
	                 }
	                 public void handleDataChange(String dataPath, Object data) throws Exception {
	                     System.out.println("the node 'dataPath'===>"+dataPath+", data has changed.it's data is "+String.valueOf(data));
	                 }
	        	 });
	         }
		});
	}

	public static void main(String[] args) {
		ZkClientSlave zkSlave = new ZkClientSlave();
		ZkClient zkCli = zkSlave.getZkCli();

		zkSlave.monitor();
	}
}
