package study.java.jdks.version1_8.new_interface;

interface Formula {
	double calculate(int a);
	default double sqrt(int a) {
		return Math.sqrt(a);
	}

}
public class ConcreteFormula implements Formula {

	@Override
	public double calculate(int a) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) {
		
		System.out.println(new ConcreteFormula().sqrt(2));
	}
}
