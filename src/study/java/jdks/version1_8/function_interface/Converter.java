package study.java.jdks.version1_8.function_interface;

//写不写注解，效果一样，写了编译器会检查接口而已
@FunctionalInterface
public interface Converter<F,T> {
	T convert(F from);
	
	default T convert2(F from) {
		return (T)from;
	}

}
