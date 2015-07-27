package study.java.thread.interrupt;


public class PendingInterrupt implements Runnable{
	public void run(){
		try{
			System.out.println("in run() - sleeping start ");
			Thread.sleep(20000);
			System.out.println("in run() - sleep over ");
		}catch(InterruptedException e){
			System.out.println("in run() - interrupted while sleeping");
			//处理完中断异常后，返回到run（）方法人口，
			//如果没有return，线程不会实际被中断，它会继续打印下面的信息
			return;  
		}
		System.out.println("in run() - leaving normally");
	}
	public static void main(String[] args) {
		SleepInterrupt si = new SleepInterrupt();
		Thread t = new Thread(si);
		t.start();
		//中断线程t
		t.interrupt();
	}
}