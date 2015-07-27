package study.java.jdks.version1_8.parameter_name;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 右键双击工程 选择Java Compile,"store information about method parameters(usable via reflection)"勾上
 * 如果是javac，得加上参数 javac -parameters
 * @author hadoop2
 *
 */
public class GetRuntimeParameterName {

    public void createUser(String name, int age, int version) {
        //
    }

    public static void main(String[] args) throws Exception {
        for (Method m : GetRuntimeParameterName.class.getMethods()) {
            System.out.println("----------------------------------------");
            System.out.println("   method: " + m.getName());
            System.out.println("   return: " + m.getReturnType().getName());
            // Parameter since1.8
            for (Parameter p : m.getParameters()) {
                System.out.println("parameter: " + p.getType().getName() + ", " + p.getName());
            }
        }
    }
}
