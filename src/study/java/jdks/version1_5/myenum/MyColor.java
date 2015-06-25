package study.java.jdks.version1_5.myenum;

/**
 * 带成员变量的枚举类
 * @author hadoop2
 *
 */
public enum MyColor {
	RED(255,0,0),BLUE(0,0,255);
	private MyColor(int rv,int gv,int bv) {
		this.redValue = rv;
		this.greenValue = gv;
		this.blueValue = bv;
		
	}
	public String mytest() {
		return redValue + "," + greenValue + "," + blueValue;
	}
	public String toString() {
		return super.toString() + "(" + redValue+ "," + greenValue+ "," + blueValue+ ")";
	}
	private int redValue;
	private int greenValue;
	private int blueValue;
}
