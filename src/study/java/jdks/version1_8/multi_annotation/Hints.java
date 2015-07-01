package study.java.jdks.version1_8.multi_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Hints {
	Hint[] value();
}
