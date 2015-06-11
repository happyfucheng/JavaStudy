package study.java.jdks.version1_5.varargs;

public class VarArgs {


// 优先级高
public static int add(int x,int y) {
	return 0;
}
public static int add(int x,int...args) {
	
	int sum =x;
	for(int arg:args) {
		sum += arg;
	}
	return sum;
}


public static void main(String[] args) {
	
	System.out.println(add(2,6));
	System.out.println(add(2));
	System.out.println(add(2,3,5));
	System.out.println(add(2,new int[]{3,5}));
}
}
