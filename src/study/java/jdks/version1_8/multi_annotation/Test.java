package study.java.jdks.version1_8.multi_annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;

/**
 * 老写法
 * @author hadoop2
 *
 */
@Hints({@Hint("hint1"),@Hint("hint2")})
class Person {
}

/**
 * 新写法
 * @author hadoop2
 *
 */
@NewHint("hint3")
@NewHint("hint4")
class NewPerson{
}
public class Test{
	public static void main(String[] args) {
		Hints hints = Person.class.getAnnotation(Hints.class);
		Hint[] hintA = hints.value();
		for(Hint hint:hintA) {
			System.out.println(hint.value());
		}
		
		// getAnnotationsByType since 1.8
		NewHint[] newHints = NewPerson.class.getAnnotationsByType(NewHint.class);
		for(NewHint newHint:newHints) {
			System.out.println(newHint.value());
		}
		
	}
}
