package study.java.thread.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFixedThreadPool{ 
	public static void main(String[] args){ 
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
//      ExecutorService executorService = Executors.newScheduledThreadPool(5);
        for (int i = 1; i < 10; i++){ 
			executorService.execute(new TestRunnable1()); 
		} 
//        executorService.shutdown(); 
	} 
} 

class TestRunnable1 implements Runnable{ 
	public void run(){ 
		System.out.println(Thread.currentThread().getName() + "线程被调用了。"); 
    } 
}
