package ex02;

import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) {
        String path = "/userinfo";

        UserController con = new UserController();

        Method[] methods = con.getClass().getDeclaredMethods();// con내부의 method를 모두 호출.
        //System.out.println(methods.length);
        for (Method method : methods) { // methods 를 idx 0 ~ idx end까지.. for문을 돌린다.
            //System.out.println(method.getName());
            RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);
            // method에서 annotation선언된 것들을 긁어 모아서 -> rm
            if (rm == null) continue; // 없으면 생략
            if (rm.uri().equals(path)) {
                try {
                    method.invoke(con); // 있으면 해당 메서드를 run, 매개변수는 클래스 타입.
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
