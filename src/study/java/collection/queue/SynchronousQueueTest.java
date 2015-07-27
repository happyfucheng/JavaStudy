package study.java.collection.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
iterator()永远返回空
peek()永远返回null
put()往queue放进去一个element以后就一直wait直到有其他thread进来把这个element取走
offer()往queue里放进一个element后立即返回，如果碰巧这个element被另一个thread取走了，offer方法返回ture，
                       认为offer成功，否则返回false
offer(2000,TimeUnit.SECONDS)往queue里放一个element但是等待指定的时间后才返回，返回的逻辑和offer()方法一样。
take()取出并且remove掉queue里的element（认为是在queue里的），取不到东西他会一直等。
poll() 取出并且remove掉queue里的element（认为是在queue里的。。。），只有到碰巧另外一个线程正在往queue里offer
                       数据或者put数据的时候，该方法才会取到东西。否则立即返回null。
poll(2000, TimeUnit.SECONDS) 等待指定的时间然后取出并且remove掉queue里的element,其实就是再等其他的thread来往里塞。
isEmpty()永远是true。
remainingCapacity() 永远是0。
remove()和removeAll() 永远是false。


这是一个很有意思的阻塞队列，其中每个插入操作必须等待另一个线程的移除操作，同样任何一个移除操作都等待另一个线程的插入操作。
因此此队列内部其 实没有任何一个元素，或者说容量是0，严格说并不是一种容器。由于队列没有容量，因此不能调用peek操作，因为
只有移除元素时才有元素。

一个没有容量的并发队列有什么用了？或者说存在的意义是什么？
SynchronousQueue 的实现非常复杂，当然了如果真要去分析还是能够得到一些经验的，但是前面分析了过多的结构后，发现越来
越陷于数据结构与算法里面了。我的初衷是通过研究并 发实现的原理来更好的利用并发来最大限度的利用可用资源。所以在后面的章节中
尽可能的少研究数据结构和算法，但是为了弄清楚里面的原理，必不可免的会涉及 到一些这方面的知识，希望后面能够适可而止。

再回到话题。SynchronousQueue 内部没有容量，但是由于一个插入操作总是对应一个移除操作，反过来同样需要满足。那么一个元
素就不会再SynchronousQueue 里面长时间停留，一旦有了插入线程和移除线程，元素很快就从插入线程移交给移除线程。也就是说
这更像是一种信道（管道），资源从一个方向快速传递到另一方 向。

需要特别说明的是，尽管元素在SynchronousQueue 内部不会“停留”，但是并不意味之SynchronousQueue 内部没有队列。
实际上SynchronousQueue 维护者线程队列，也就是插入线程或者移除线程在不同时存在的时候就会有线程队列。既然有队列，同
样就有公平性和非公平性特性，公平性保证正在等待的插入线 程或者移除线程以FIFO的顺序传递资源。

显然这是一种快速传递元素的方式，也就是说在这种情况下元素总是以最快的方式从插入着（生产者）传递给移除着（消费者），这在多任
务队列中是最快处理任务的方式。在线程池的相关章节中还会更多的提到此特性。

*/
public class SynchronousQueueTest {
	public static void main(String[] args) throws InterruptedException {
		
		SynchronousQueue<String> sq = new SynchronousQueue<String>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					sq.put("abc");
					Thread.sleep(10);
					sq.put("efg");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		Thread.sleep(2000);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println(sq.take());
					System.out.println(sq.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
	}

}
