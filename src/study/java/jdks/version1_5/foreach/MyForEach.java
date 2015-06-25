package study.java.jdks.version1_5.foreach;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyForEach<T> implements Iterable<T>{

	T[] value;
	
	@Override
	public Iterator<T> iterator() {
		List<T> valueList = Arrays.asList(value);
		return valueList.iterator();
	}

	public T[] getValue() {
		return value;
	}
	
	public void setValue(T[] value) {
		this.value = value;
	}

}

