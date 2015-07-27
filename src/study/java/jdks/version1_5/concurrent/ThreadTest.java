package study.java.jdks.version1_5.concurrent;

import java.util.ArrayList;  
import java.util.Collections;  
import java.util.List;  
import java.util.concurrent.Callable;  
import java.util.concurrent.CountDownLatch;  
import java.util.concurrent.ExecutionException;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.Future;  
  
/**
 *  
 * @author hadoop2
 * 问题，.await()方法什么时候不阻塞
 */
public class ThreadTest {  
  
	public static String test = "fucheng";
   public static void main(String[] args) {  
	   // 这个是开始的信号(begin初始化为1,当begin.countDown()为0时，begin.await()可以开始执行)
       CountDownLatch begin = new CountDownLatch(1);    
       // 这个是结束的信号（end初始化为5，当调用5次end.countDown()减为0时,end.await()可以开始执行）
       CountDownLatch end = new CountDownLatch(5);    
       // 这个用来保存每个选手跑的时间  
       List<Future<Runner>> fs = new ArrayList<Future<Runner>>();  
       ExecutorService es = Executors.newFixedThreadPool(10);  
       //new Sych
       // new CachedThreadPool();
       //SynchronousQueue
 
       Runner r1 = new Runner(1 + "号选手：", 5);  
       Runner r2 = new Runner(2 + "号选手：", 1);  
       Runner r3 = new Runner(3 + "号选手：", 4);  
       Runner r4 = new Runner(4 + "号选手：", 3);  
       Runner r5 = new Runner(5 + "号选手：", 3);  
         
       fs.add(es.submit(new T(r1, begin, end)));  
       fs.add(es.submit(new T(r2, begin, end)));  
       fs.add(es.submit(new T(r3, begin, end)));  
       fs.add(es.submit(new T(r4, begin, end)));  
       fs.add(es.submit(new T(r5, begin, end)));  
 
       //倒计时     
       for(int i = 3 ;i>0 ;i--)    
       {  
           try {  
               Thread.sleep(1000);  
               System.out.println(i);  
           } catch (InterruptedException e) {  
               e.printStackTrace();  
           }  
       }  
       System.out.println("开始");  
       // 主线程这里说开始，子线那里才可以开始跑(这里阻塞着选手)   
       begin.countDown(); 
//    try {
//		Thread.sleep(1);
//	} catch (InterruptedException e1) {
//		e1.printStackTrace();
//	}
       test = "fucheng changed";
       try {  
    	// 主线程在这里等他们跑(阻塞式)，跑完之后收集他们的结果，然后评出排名.
        end.await();    
       } catch (InterruptedException e) {  
           e.printStackTrace();  
       }  
       System.out.println("大家都跑完了 开始计算结果，大家稍等下");  
 
       List<Runner> sort = new ArrayList<Runner>();  
       for (Future<Runner> f : fs) {//////////////////////这里在等所有的子线程完成   
           try {  
        	   // f.get()方法阻塞当前线程
               sort.add(f.get());  
           } catch (InterruptedException e) {  
               e.printStackTrace();  
           } catch (ExecutionException e) {  
               e.printStackTrace();  
           }  
       }  
       Collections.sort(sort);  
       for (int i = 0; i < sort.size(); i++) {  
           System.out.println("第" + (i + 1) + "名：" + sort.get(i).$name + ":"  
                   + "成绩" + sort.get(i).$time + "秒." + " 能量:"  
                   + sort.get(i).$power);  
       }  
       // ////跑完了。   
       es.shutdown();  
 
   }  
 
   static class T implements Callable<Runner> {  
       private Runner $runner;  
       private CountDownLatch $begin;  
       private CountDownLatch $end;  
 
       public T(Runner _runner, CountDownLatch _begin, CountDownLatch _end) {  
           $begin = _begin;  
           $end = _end;  
           this.$runner = _runner;  
       }  
 
       @Override  
       public Runner call() throws Exception {  
           $begin.await(); // //等begin.countDown();这条开始命令   
           System.out.println(ThreadTest.test);
           System.out.println("大家好，我是" + $runner.$name + "开跑了.");  
           $runner.run(); // //跑啊跑啊跑啊跑啊   
           $end.countDown(); // 到了,你妹！ 快掐表!!!我的成绩是s！   
           return $runner;  
       }  
    }  
  
    static class Runner implements Comparable<Runner> {  
        private String $name;  
        private int $power;   ////这个是选手的能量值，初始化选手的时候给定的，能量值越多，跑得越快   
        private long $time;   /////跑的时间   
  
        public long get$time() {  
            return $time;  
        }  
  
        public void set$time(long $time) {  
            this.$time = $time;  
        }  
  
        public String get$name() {  
            return $name;  
        }  
  
        public void set$name(String $name) {  
            this.$name = $name;  
        }  
  
        public int get$power() {  
            return $power;  
        }  
  
        public void set$power(int $power) {  
            this.$power = $power;  
        }  
  
        public Runner(String _name, int _power) {  
            this.$name = _name;  
            this.$power = _power;  
        }  
  
        public void run() {  
            // //   
            long st = System.currentTimeMillis();  
            if ($power > 5 || $power < 0) //   
                try {  
                    throw new Exception();  
                } catch (Exception e1) {  
                    // TODO Auto-generated catch block   
                    e1.printStackTrace();  
                }  
            try {  
                Thread.sleep(5000 - 1000 * $power);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block   
                e.printStackTrace();  
            } // 跑步的能力，能力越大，睡的时间越段   
            $time = System.currentTimeMillis() - st;  
        }  
  
        @Override  
        public int compareTo(Runner o) {  
            // TODO Auto-generated method stub   
            if (this.$time < o.$time)  
                return -1;  
            if (this.$time > o.$time)  
                return 1;  
            return 0;  
        }  
    }  
}  
/**
说明：
Callable/Future
CountDownLatch
ExecutorService/Executors
主要是以上几个并发包类的用法、
简单描述下：
Callable/Future   ： Callable<T>这个接口类似于Runnble接口，要重写里面的call()方法
（类似于run方法）具体区别是，call()可以返回值T，run()不能返回、返回的值通过Future.get()获得。

CountDownLatch ：这个是一个计时器，主要方法是 await() 和countDown()  ,  
await() 是一个阻塞式的，等countDown()  调用完再执行下面的代码。countDown()
要调N次，在构造CountDownLatch 的时候，要指定N次。  可以实现一个  总分总  的线程模型。

ExecutorService/Executors：  这个是线程池的内容，大家记住就行，到时候用的时候再查，
线程池总类有些多，要根据具体场景才能选择合适自己的池。

下面实例程序，有些长，大家慢慢看，写程序的要静的下心。
/////// 描述下场景。
就是5个选手赛跑，每个选手有个能量值，在初始化选手的时候可以给定，值越高，跑得越快。(最高不高过5，不小过0).
主线程可以看作是裁判，他在起跑线上等待5个选手初始化。每个选手初始化完后，在起跑线上$begin.await(); 
// //等begin.countDown();这条开始命令

begin.countDown();后，每个选手都开始跑了，然后主线程在等着end.await(); 
// /主线程在这里等他们跑(阻塞式)，跑完之后收集他们的结果，然后评出排名.。
当每个选手跑完后，end.countDown()一下，意思是我跑完了。直到5个人都end.countDown()一下后，
主线程就执行end.await()方法下面的代码了。就是开始收集他们的成绩，然后统计，然后输出。

 */






