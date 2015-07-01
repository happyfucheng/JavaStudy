package study.java.jdks.version1_8.method_refrence;

@FunctionalInterface
public interface PersonFactory<P extends Person> {
	
	public P create(String firstName,String lastName);

}
