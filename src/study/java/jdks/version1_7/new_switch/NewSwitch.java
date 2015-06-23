package study.java.jdks.version1_7.new_switch;

public class NewSwitch {
	public static void main(String[] args) {

		String s = "red";
		switch (s) {
		case "yellow":
			System.out.println("this is yellow");
			break;
		case "red":
			System.out.println("tihs is red");
			break;
		case "green":
			System.out.println("this is green");
			break;
		default:
			System.out.println("end");

		}
	}
}




/**
 //∑¥±‡“Î¥˙¬Î
 package study.java.jdks.version1_7.new_switch;

import java.io.PrintStream;

public class NewSwitch
{
  public static void main(String[] args)
  {
    String s = "red";
    String str1;
    switch ((str1 = s).hashCode()) { case -734239628:
      if (str1.equals("yellow")) break; break;
    case 112785:
      if (str1.equals("red"));
      break;
    case 98619139:
      if (!str1.equals("green")) { break label113;

        System.out.println("this is yellow");
        return;

        System.out.println("tihs is red");
        return;
      } else {
        System.out.println("this is green");
      }break;
    }
    label113: System.out.println("end");
  }
}
 
*/
