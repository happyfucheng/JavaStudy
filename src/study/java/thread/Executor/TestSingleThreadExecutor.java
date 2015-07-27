package study.java.thread.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadExecutor {
	public static void main(String[] args){ 
		ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i < 10; i++){ 
			executorService.execute(new TestRunnable2()); 
		} 
        executorService.shutdown(); 
	} 
} 

class TestRunnable2 implements Runnable{ 
	public void run(){ 
		System.out.println(Thread.currentThread().getName() + "线程被调用了。"); 
    } 
}

