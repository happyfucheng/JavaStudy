package study.java.jdks.version1_7.perm_gen;




public class StringInternTest {
	public static void main(String[] args) {
		 String str1 = new StringBuilder("计算机").append("软件").toString(); 
		 System.out.println(str1.intern() == str1);    
		 String str2 = new StringBuilder("计算机").toString();  
		 System.out.println(str2.intern() == str2);  
		 
		 //jdk1.6 false false
		 //jdk1.7 true false
	}

}
