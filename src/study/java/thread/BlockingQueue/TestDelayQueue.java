package study.java.thread.BlockingQueue;


import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestDelayQueue implements Runnable {
    DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();

    public void addTask(DelayedTask e) {
       queue.put(e);
    }

    public void removeTask() {
       queue.poll();
    }

    public Collection<DelayedTask> getAllTasks() {
       return Collections.unmodifiableCollection(queue);
    }

    public int getTaskQuantity() {
       return queue.size();
    }

    public void run() {
       while (!queue.isEmpty())
           try {
              queue.take().run();
           } catch (InterruptedException e) {
              System.out.println("Interrupted");
           }
       System.out.println("Finished DelayedTask");
    }

    public static class DelayedTask implements Delayed, Runnable {
       private static int counter = 0;
       private final int id = counter++;
       private final int delta;
       private final long trigger;

       public DelayedTask(int delayInSeconds) {
           delta = delayInSeconds;
           trigger = System.nanoTime() + NANOSECONDS.convert(delta, SECONDS);
       }

       public long getDelay(TimeUnit unit) {
    	   long time=   unit.convert(trigger - System.nanoTime(), NANOSECONDS);
           return time;
       }

       public int compareTo(Delayed arg) {
           DelayedTask that = (DelayedTask) arg;
           if (trigger < that.trigger)
              return -1;
           if (trigger > that.trigger)
              return 1;
           return 0;
       }

       public void run() {
           System.out.println(this);
       }

       public String toString() {
           return "[" + delta + "s]" + "Task" + id;
       }
    }

    public static void main(String[] args) {
       Random rand = new Random();
       ExecutorService exec = Executors.newCachedThreadPool();
       TestDelayQueue tasker = new TestDelayQueue();
       for (int i = 0; i < 10; i++){
           tasker.addTask(new DelayedTask(rand.nextInt(20)));
       }
       exec.execute(tasker);
       exec.shutdown();
    }
}
