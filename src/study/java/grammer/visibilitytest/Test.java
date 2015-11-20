package study.java.grammer.visibilitytest;

interface A{
	int x =7;
}
interface  B {
	 int x = 1;
}
public class Test implements A,B{
	public void s(){
		System.out.println(A.x);
	}
	public static void main(String[] args) {
		new Test().s();
		System.out.println(B.x);
	}

}
