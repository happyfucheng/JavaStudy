public class MyRemoteDebug {

	public static void main(String[] args) throws Exception{

		while(true) {
			System.out.println("start");
			System.out.println("ing....");
			System.out.println("end");
			Thread.sleep(2000);
			System.out.println(1/0);
		}

	}
}