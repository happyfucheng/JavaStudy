package study.java.jdks.version1_5.myenum;

/*
 * 枚举
 */
public class Invoke {

	public static void main(String[] args) {
		Color red = Color.RED;
		System.out.println(red.toString());
		
		MyColor myred = MyColor.RED;
		System.out.println(myred);
		System.out.println(myred.mytest());
		
		// 返回枚举值在枚举类种的顺序。这个顺序根据枚举值声明的顺序而定。
		System.out.println(myred.ordinal());
		// Enum实现了java.lang.Comparable接口，因此可以比较象与指定对象的顺序。
		// Enum中的compareTo返回的是两个枚举值的顺序之差。当然，前提是两个枚举值必须属于同一个枚举类，否则会抛出ClassCastException（）异常
		System.out.println(myred.compareTo(MyColor.BLUE));
		System.out.println(red.compareTo(Color.YELLOW));
		
		// 遍历枚举值
		Color[] colors = Color.values();
		for(Color color:colors) {
			System.out.println(color);
		}
		
		// valueOf方法
		System.out.println(MyColor.valueOf("RED"));
		
		// switch语句中判断枚举
		Color color = Color.RED;
		switch(color) {
		case BLUE:System.out.println("it's blue");break;
		case RED:System.out.println("it's red");break;
		case YELLOW:System.out.println("it's yellow");break;
		}
		
	}
}
