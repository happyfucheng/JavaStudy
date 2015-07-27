package study.java.jdks.version1_5.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static class ReentrantLockTest{
		Lock lock = new ReentrantLock();
		double value = 0d;
		int addtimes = 0;
	}

}
