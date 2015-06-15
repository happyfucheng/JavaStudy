package study.java.jdks.version1_5.generic;

public class MyValue<T,U> {
	private T value1;
	private U Value2;
	public MyValue(T value1,U value2) {
		this.value1 = value1;
		this.Value2 = value2;
		
	}
	public T getValue1() {
		return value1;
	}
	public void setValue1(T value1) {
		this.value1 = value1;
	}
	public U getValue2() {
		return Value2;
	}
	public void setValue2(U value2) {
		Value2 = value2;
	}
	
	public static void main(String[] args) {
		MyValue<Long,String> myValue = new MyValue<Long,String>(3l,"fucheng");
		System.out.println(myValue.getValue1() + 1);
		System.out.println(myValue.getValue2());
		
	}

}
