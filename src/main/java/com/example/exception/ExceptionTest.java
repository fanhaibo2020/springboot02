package com.example.exception;

/**
 * @ClassName ExceptionTest
 * @Author fhb
 * @Date 2020/5/18 23:20
 * @Version 1.0
 **/
public class ExceptionTest {

    public static void main(String[] args) {
//          a();
        try{
            b();
        }catch(Exception e){
            System.out.println("ee="+e.getMessage());
        }



    }

    public static void a() {
        try{
            System.out.println("111");
            throw new Exception("手动抛出异常");
        }catch(Exception e){
            System.out.println(222);
            System.out.println("信息="+e.getMessage());
        }
    }

    public static  void b() throws Exception{  //Exception捕获异常的一种
            System.out.println("333");
            int a =1,b=0,c;
            try{
                c = a/b;
            }catch (Exception e){
                //
                throw new Exception("分母不能为0"); //手动抛出异常，与虚拟机抛出异常对应的
            }

    }

}
