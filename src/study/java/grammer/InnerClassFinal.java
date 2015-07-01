package study.java.grammer;

public class InnerClassFinal {
	
	/**
	 *  为了语义一致性，若方法内部类引用了局部变量，则必须为final类型
	 * @param i
	 */
	public static void print(final int i) {
		 class MyInner {
			public void print() {
				System.out.println(i);
			}
		}
		 
		MyInner myInner = new MyInner();
		myInner.print();
	}
	
	public static void main(String[] args) {
		print(33);
	}
}


/**
package study.java.grammer;

import java.io.PrintStream;

class InnerClassFinal$1MyInner
{
  InnerClassFinal$1MyInner(int paramInt)
  {
  }

  public void print()
  {
    System.out.println(this.val$i);
  }
}
 */
