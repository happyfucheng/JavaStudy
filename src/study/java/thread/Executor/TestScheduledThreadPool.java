package study.java.thread.Executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TestScheduledThreadPool {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(3);
		/**
		 * 延迟3秒执行
		 */
		/*scheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);*/
		

		/**
		 * 延迟1秒后每3秒执行一次
		 */
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out
						.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);

//		scheduledThreadPool.shutdown();
	}
}

class TestRunnable3 implements Runnable {
	public void run() {
		System.out.println(Thread.currentThread().getName() + "线程被调用了。");
	}
}
