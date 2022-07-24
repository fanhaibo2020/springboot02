package com.example.algorithm;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Recursive
 * @describe 递归算法(要有退出程序的出口),
 * 理解:递归调用就是通过栈这种数据结构完成的。整个过程实际上就是一个栈的入栈和出栈问题。
 * 递归中的“递”就是入栈，递进；“归”就是出栈，回归
 * @Author fhb
 * @Date 2021/1/31 15:34
 * @Version 1.0
 **/
public class Recursive {

//    public static void main(String[] args) throws Exception{
//        long value = factorial(4);
//        System.out.println("value="+value);
//    }

    public static long factorial(int n) throws Exception {
        if (n < 0)
            throw new Exception("参数不能为负！");
        else if (n == 1 || n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }

    //参考博客:https://www.cnblogs.com/cainiao-chuanqi/p/11320972.html

//        public static void main(String[] args) {
//            File file = new File("D:\\文档集合");
//            listAllFile(file);
//        }
//
//        public static void listAllFile(File file) {
//            File[] strs = file.listFiles();
//            for (int i = 0; i < strs.length; i++) {   //此处的i++表示每次完成本次循环后,再下次进入循环体时会自动加一
//                // 判断strs[i]是不是目录
//                if (strs[i].isDirectory()) {
//                    listAllFile(strs[i]);//递归调用自己   ,这里的循环完成就是递归调用的出口
//                    System.out.println("目录="+strs[i].getName());
//                } else {
//                    System.out.println("文件名="+strs[i].getName());
//                }
//            }
//        }

    //理解不够透彻点:递归完成后是怎样返回上一次递归调用处的???
    //每次递归完成后都会回到上一次的调用处


    public static void main(String[] args) {
        recursion_display(3);
    }

    /*关于 递归中 递进和回归的理解(走断点理解整个过程)*/
    public static void recursion_display(int n) {
        int temp=n;//保证前后打印的值一样
        System.out.println("递进:" + temp);
        if (n > 0) {
            recursion_display(--n);
        }
        System.out.println("回归:" + temp);  //此处为退出递归调用前用执行的语句
    }

    //再纸上模拟单位树遍历过程
    public void getSubUnitUuid(String rootUnitUuid){
        Set<String> set = new HashSet<>();
        //查询当前节点下的子级节点
        String[] unitUuidAry = subUuid(rootUnitUuid,set);  //只执行一次,获取初始的递归数据
//        for(int i=0;i<unitUuidAry.length;i++){
//            subUuid(unitUuidAry[i],set);//递归调用的入口
//            set.add(unitUuidAry[i]);
//        }
    }

    //递归的方法要在同一个方法体内,才能完成真正的递归调用
    public String[] subUuid(String uuid,Set<String> set) {
        //获取子一级数据的逻辑;
        String[] aaa = null ; //省略获取逻辑,暂时用null替代;
        for(int i=0;i<aaa.length;i++){
            subUuid(aaa[i],set);
            set.add(aaa[i]);
        }


        return null; //此处应为上面获取的结果
    }
}
