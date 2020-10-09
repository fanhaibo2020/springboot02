package com.example.annonation;

/** lambda表达式的测试工作
 * @ClassName LambdaTest
 * @Author fhb
 * @Date 2020/7/12 15:33
 * @Version 1.0
 **/
public class LambdaTest {
    public static void main(String[] args) {
        //1、使用接口实现类
        Comparator comparator = new MyComparetor();
        //2、使用匿名内部类
        Comparator comparator1 = new Comparator() {
            @Override
            public int compare(int a, int b) {
               return a-b;
            }
        };
        //3、使用Lambda表达式来实现接口
        Comparator comparator2 = (a,b)->a-b;

        int result = comparator2.compare(8,3);
        System.out.println("result="+result);
    }
}

//接口实现类
class MyComparetor implements Comparator{
    @Override
    public int compare(int a, int b) {
        return a-b;
    }
}

//函数式接口的注解，有该注解后接口中就只能有一个抽象方法
@FunctionalInterface
interface  Comparator{
    int compare(int a,int b);
}