package study.java.collection.map;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {
	public static void main(String[] args) {
		String v = new String("abc");
		ReferenceQueue<String> rq = new ReferenceQueue<String>();
		WeakReference<String> wr = new WeakReference<String>(v, rq);
		v =null;
		System.out.println(wr.get());
		System.out.println(rq.poll() == wr);
		System.gc();
		System.out.println(wr.get());
		System.out.println(rq.poll() == wr);
		System.out.println(wr.getClass().getName());
		
	}

}
