package study.java.jdks.version1_5;

public class AutoBoxingUnBoxing {
	
	public static void main(String[] args) {
		Integer a = 297;
		Integer b = 297;
		System.out.println(a==b);
		
		//-128~127(FlyWeight模式)
		Integer c = 111;
		Integer d = 111;
		System.out.println(c==d);

		
		// 自动装箱
		Integer data1 = 10; 
		Integer data2 = 20; 
		// 转为double值再除以3 
		System.out.println(data1.doubleValue() / 3); 
		// 进行两个值的比较 
		System.out.println(data1.compareTo(data2));
		
		// 自动拆箱
		Integer data3 = new Integer(255);
		System.out.println(data3 + 5);
	}

}
