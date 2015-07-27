package study.java.jdks.version1_8.saybyeto_pergen;

public class ClassA { 
	public void method(String name) {  
		String a1 = "正如大家所知，JDK 8 Early Access版已经提供下载。这使开发者可以体验Java8的新特性。其中之一，是Oracle从JDK7发布以来就一直宣称的要完全移除永久代空间。例如，字符串内部池，已经在JDK7中从永久代中移除。JDK8的发布将宣告它的终结。这篇文章将会分享到目前为止对 PermGen 继任者：Metaspace的了解。我们将通过运行一个存在类元数据对象“泄漏”的程序，来对比HotSpot1.7与HotSpot1.8（b75，译者注：翻译文章时已经到b118）的运行时行为。待Java 8 正式发布之后，才会提供最终的规范，优化参数和相关文档。元空间（Metaspace）：一种新的内存空间的诞生    JDK8 HotSpot JVM 使用本地内存来存储类元数据信息并称之为：元空间（Metaspace）；这与Oracle JRockit 和IBM JVM’s很相似。这将是一个好消息：意味着不会再有java.lang.OutOfMemoryError: PermGen问题，也不再需要你进行调优及监控内存空间的使用……但请等等，这么说还为时过早。在默认情况下，这些改变是透明的，接下来我们的展示将使你知道仍然要关注类元数据内存的占用。请一定要牢记，这个新特性也不能神奇地消除类和类加载器导致的内存泄漏。你需求使用不同的方法以及遵守新的命名约定来追踪这些问题。我推荐大家阅读有关PermGen移除总结和Jon对此的评论。    总结如下：•这部分内存空间将全部移除。•JVM的参数：PermSize 和 MaxPermSize 会被忽略并给出警告（如果在启用时设置了这两个参数）。";
		String a2 = "1";
		
	}
}