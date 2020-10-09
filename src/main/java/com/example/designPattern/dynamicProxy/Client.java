package com.example.designPattern.dynamicProxy;

/**
 * @ClassName Client
 * @Author fhb
 * @Date 2020/5/8 14:19
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        //创建目标对象
        ITeacherDao target = new TeacherDao();
        //给目标对象创建代理对象
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(target).getProxyFactoty();
        System.out.println("proxyInstance="+proxyInstance);
        //class=class com.sun.proxy.$Proxy0表示内存中动态生成了代理对象
        System.out.println("class="+proxyInstance.getClass());

        //通过代理对象，调用目标对象方法
        proxyInstance.teach();
        proxyInstance.sayHello("Tom");
    }
}
