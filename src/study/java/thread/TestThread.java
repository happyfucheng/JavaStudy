package study.java.thread;

public class TestThread extends Thread{
    
	public void run(){
		for (int i=0;i<10;i++) {
			System.out.println("i="+i);
		}
	}
	
	public static void main(String[] args) {
		TestThread myThread = new TestThread();
		myThread.start();//启动线程
		//myThread.run(); //方法调用
		for (int i = 0; i < 10; i++) {
			System.out.println("main i="+i);
		}
	}
	
}
