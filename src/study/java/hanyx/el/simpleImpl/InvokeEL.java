package study.java.hanyx.el.simpleImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 简单的表达式引擎
 * @author 韩元旭
 */
public class InvokeEL {
	/** 编译器 */
	private static com.sun.tools.javac.Main JAVAC = new com.sun.tools.javac.Main();
	private Map<String, Object> CONTEXT;
	/** Debug模式,开启后可以看到每个过程的时间 */
	private static boolean isDebug = false;
	
	public static InvokeEL instance = new InvokeEL();
	
	public InvokeEL() {
		CONTEXT = new HashMap<String, Object>();
	}
	public InvokeEL(Map<String, Object> context) {
		if (context != null) {
			CONTEXT = context;
		} else {
			CONTEXT = new HashMap<String, Object>();
		}
	}
	
	/**
	 * 编译JavaCode,返回临时文件对象
	 * @param code
	 * @return 编译失败返回null
	 * @throws IOException
	 * @throws Exception
	 */
	private synchronized static File compile(String code,Map<String, Object> context) throws Exception {
		long ioStart = System.currentTimeMillis();
		
		// 在用户当前文件目录创建一个临时代码文件
		File file = File.createTempFile("Iel", ".java", new File(System.getProperty("user.dir")));
		// 当虚拟机退出时,删除此临时java源文件
		file.deleteOnExit();
		// 获得文件名和类名字
		String fileName = file.getName();
		String className = getClassName(fileName);
		// 将代码输出到文件
		PrintWriter out = new PrintWriter(new FileOutputStream(file));
		out.println("public class " + className + " {");
		out.println("	public static Object main(String[] args) throws Exception {");
		if (context != null && context.size() > 0) {
			for (Entry<String, Object> entry: context.entrySet()) {
				Object value = entry.getValue();
				String key = entry.getKey();
				if (value instanceof Double) {
					out.println("double " + key + " = " + value + "D;");
				} else if (value instanceof Long) {
					out.println("long " + key + " = " + value + "L;");
				} else if (value instanceof Float) {
					out.println("float " + key + " = " + value + "F;");
				} else if (value instanceof Integer) {
					out.println("int " + key + " = " + value + ";");
				} else if (value instanceof Short) {
					out.println("short " + key + " = " + value + ";");
				} else if (value instanceof Byte) {
					out.println("byte " + key + " = " + value + ";");
				} else if (value instanceof String) {
					out.println("String " + key + " = \"" + value + "\";");
				} else if (value instanceof Character) {
					out.println("char " + key + " = '" + value + "';");
				} else if (value instanceof Boolean) {
					out.println("boolean " + key + " = " + value + ";");
				}
			}
		}
		
		out.println("		return (" + code + ");");
		out.println("	}");
		out.println("}");
		// 关闭文件流
		out.flush();
		out.close();
		
		long ioEnd = System.currentTimeMillis();
		if(isDebug) {
			System.out.println("生成文件用时:" + (ioEnd-ioStart) + "ms");
		}
		
		// 编译代码文件
		String[] args = new String[]{"-d", System.getProperty("user.dir"),fileName};
		// 返回编译的状态代码
		@SuppressWarnings("static-access")
		int status = JAVAC.compile(args);
		
		long cEnd = System.currentTimeMillis();
		if(isDebug) {
			System.out.println("编译文件用时:" + (cEnd-ioEnd) + "ms");
		}
		
		// 处理编译状态
		return status==0?file:null;
	}
	/**
	 * 根据一个java源文件名获得类名
	 * @param filename
	 * @return
	 */
	private static String getClassName(String filename) {
		return filename.substring(0, filename.length() - 5);
	}
	
	/**
	 * 执行JavaClass
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private static synchronized Object run(File file) throws Exception{
		long runStart = System.currentTimeMillis();
		
		if (file == null) {
			return "Expression Compile Exception";
		}
		String className = getClassName(file.getName());
		// 当虚拟机退出时,删除此临时编译的类文件
		File clsFile = new File(file.getParent(), className + ".class"); 
		moveFile(clsFile, className).deleteOnExit();
		// 访问这个类
		Class<?> cls = Class.forName(className);
		// 映射main方法
		Method main = cls.getMethod("main", new Class[] { String[].class });
		// 执行main方法
		Object result = main.invoke(null, new Object[] { new String[0] });
		
		long runEnd = System.currentTimeMillis();
		if(isDebug) {
			System.out.println("运行文件用时:" + (runEnd-runStart) + "ms");
		}
		return result;
	}
	
	/**
	 * 移动文件 如果不移动到bin目录下,就会报ClassNotFoundException
	 * @param file
	 * @param className
	 * @return
	 */
	private static File moveFile(File oldFile, String className) {
		String newPath = System.getProperty("user.dir") + "\\bin\\"; 
		//new一个新文件夹 
		File fnewpath = new File(newPath); 
		//判断文件夹是否存在 
		if(!fnewpath.exists()) {
			fnewpath.mkdirs(); 
		}
		//将文件移到新文件里 
		File fnew = new File(newPath +oldFile.getName()); 
		oldFile.renameTo(fnew);
		
		return fnew;
	}
	
	/**
	 * 表达式计算
	 * @param code
	 * @return
	 */
	public Object eval(String code) {
		try {
			return run(compile(code,this.CONTEXT));
		} catch (Exception e) {
			return e;
		}
	}
	
	/**
	 * 上下文存入值
	 * @param key
	 * @param value
	 */
	public InvokeEL push (String key, Object value) {
		if (value != null) {
			this.CONTEXT.put(key, value);
		}
		return this;
	}
	
	/**
	 * 清空上下文
	 */
	public Map<String, Object> clearContext() {
		Map<String, Object> map = this.CONTEXT;
		this.CONTEXT.clear();
		return map;
	}
	
	public static void main(String[] args) {
		InvokeEL.instance.eval("1000+100.0*99-(600-3*15)/(((68-9)-3)*2-100)+10000%7*71");
	}
	
//File clsFile = new File(file.getParent(), className + ".class"); 
//com.sun.org.apache.bcel.internal.util.ClassLoader classLoader
//	= new com.sun.org.apache.bcel.internal.util.ClassLoader(
//			new String[] {file.getParent()});//类根路径
//Class<?> cls = classLoader.loadClass(className);//类名
}
