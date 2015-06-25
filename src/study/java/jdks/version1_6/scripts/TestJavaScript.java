package study.java.jdks.version1_6.scripts;

import java.io.FileReader;
import java.util.Scanner;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestJavaScript {
	public static void main(String[] args) throws Exception {
		
		
		
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Bindings bind = engine.createBindings();
        bind.put("factor",2);
        engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
        Scanner input = new Scanner(System.in);
        while(input.hasNext()) {
        	int first = input.nextInt();
        	int sec = input.nextInt();
        	System.out.println("输入的参数为:" + first + "," + sec);
        	engine.eval(new FileReader("E:\\eclipse_luna\\aidefaultws\\JavaStudy\\src\\study\\java\\jdks\\version1_6\\scripts\\cal.js"));
        	if(engine instanceof Invocable) {
        		Invocable in = (Invocable)engine;
        		Double result = (Double)in.invokeFunction("cal", first,sec);
        		System.out.println("运算结果为:" + result.intValue());
        	}
        }

	}

}
