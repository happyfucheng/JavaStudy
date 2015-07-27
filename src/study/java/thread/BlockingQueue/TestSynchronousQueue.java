package study.java.thread.BlockingQueue;

import java.util.concurrent.SynchronousQueue;


public class TestSynchronousQueue {
	public static void main(String[] args) throws InterruptedException {
		final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
		// 往queue放进去一个element以后就一直wait直到有其他thread进来把这个element取走
	/*	queue.take();//  取出并且remove掉queue里的element，取不到东西他会一直等
		queue.peek();// 永远返回null
    */	
		
	new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					queue.put(11);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	Thread.sleep(1000);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(queue.peek());
					System.out.println(queue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}

