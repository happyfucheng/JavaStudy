package study.java.string;

public class IndexOfTest {
	private int loop;
	public static String alphabet = "12355x3255";
	public static void main(String[] args) {
	    IndexOfTest test = new IndexOfTest(50000000);
	    
	    test.run();
	}

	public IndexOfTest(int loop) {
	    this.loop = loop;
	}

	public void run() {
	    long start, end;
	    start = System.currentTimeMillis();
	    char c = 'x';
	    String s = "x";
	    
	    for(int i = 0 ; i < loop ; i++) {
	    	"12355x3255".indexOf(c);
	    }
	    end = System.currentTimeMillis();
	    System.out.println("indexOf(char) : " + (end - start) + "ms");

	    start = System.currentTimeMillis();
	    
	    for(int i = 0 ; i < loop ; i++) {
	        "2235532x56".indexOf(s);
	    }
	    end = System.currentTimeMillis();
	    System.out.println("indexOf(String) : " + (end - start) + "ms");
	    

	}


}
