package study.java.jdks.version1_5.importstatic;

import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static study.java.jdks.version1_5.myenum.Color.RED;
import static java.lang.String.valueOf;
/**
 * 静态导入
 * @author hadoop2
 *
 */
public class ImportStatic {
	public static void main(String[] args) throws InterruptedException {
		sleep(1000);
		out.println("static void");
		out.println(RED);
		out.println(valueOf(1234));
	}

}
