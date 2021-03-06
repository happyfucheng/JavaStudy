package study.java.jdks.version1_8.multi_annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(NewHints.class)
public @interface NewHint {
	String value();
}
