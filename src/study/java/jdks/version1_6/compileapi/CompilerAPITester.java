package study.java.jdks.version1_6.compileapi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CompilerAPITester {
    private static String JAVA_SOURCE_FILE = "DynamicObject.java";
    //private static String JAVA_CLASS_FILE = "DynamicObject.class";
    private static String JAVA_CLASS_NAME = "DynamicObject";
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        generateJavaClass();
        try {
            Iterable<? extends JavaFileObject> sourcefiles = fileManager.getJavaFileObjects("./src/test/test/"+ JAVA_SOURCE_FILE);
            compiler.getTask(null, fileManager, null, null, null, sourcefiles).call();
            fileManager.close();
            
            //Thread.sleep(2000);
            URLClassLoader ucl = new URLClassLoader(new URL[]{new URL("file:\\E:\\eclipse_luna\\aidefaultws\\JavaStudy\\src\\")});
            Class<?> clazz = ucl.loadClass("test.test."+JAVA_CLASS_NAME);
            clazz.newInstance();
          //Runtime.getRuntime().exec("cmd /c move E:\\eclipse_luna\\aidefaultws\\JavaStudy\\src\\"+JAVA_CLASS_FILE+" E:\\eclipse_luna\\aidefaultws\\JavaStudy\\bin\\");
            //Class.forName(JAVA_CLASS_NAME).newInstance();//创建动态编译得到的DynamicObject类的实例
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void generateJavaClass(){
        try {
            FileWriter fw = new FileWriter("./src/test/test/" +JAVA_SOURCE_FILE);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("package test.test;");
            bw.write("public class "+JAVA_CLASS_NAME+"{");
            bw.newLine();
            bw.write("public "+JAVA_CLASS_NAME+"(){System.out.println(\"In the constructor of DynamicObject\");}}");
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


