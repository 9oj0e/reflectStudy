package ex03;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void findUri(List<Object> instances, String path) {
        /*
        * responsibility :
        * find path from instances
        * */
        for (Object instance : instances) {
            Method[] methods = instance.getClass().getDeclaredMethods();

            for (Method method : methods) {
                RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);
                if (rm == null) continue;
                if (rm.uri().equals(path)) {
                    try {
                        method.invoke(instance);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static List<Object> componentScan(String pkg) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        /*
        * responsibility :
        * find @Controller from "pkg"
        * */
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageUrl = classLoader.getResource("ex03"); // "ex03"을 찾아서 해당 위치를 읽어서 packageUrl에 담는다..

        // dir(directory)도 os관점에서 file이다.
        File dir = new File(packageUrl.toURI()); // packageUrl을 dir에 넣고,

        List<Object> instances = new ArrayList<>();
        // 선언자가 어떤 type의 object를 만들지 모른다. 제네릭에 Object를 받는다.

        for (File file : dir.listFiles()) {
            // System.out.println(file.getName());
            if (file.getName().endsWith(".class")) {
                String className = "ex03" + "." + file.getName().replace(".class", "");
                System.out.println(className);
                Class cls = Class.forName(className);
                if (cls.isAnnotationPresent(Controller.class)) { // ()의 annotation이 붙어있다면 true, 아니면 false
                    Object instance = cls.newInstance();
                    instances.add(instance); // 자동으로 실행
                }
            }
        }
        return instances;
    }

    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Object> instances = componentScan("ex03");
        findUri(instances, "/login");
    }
}
