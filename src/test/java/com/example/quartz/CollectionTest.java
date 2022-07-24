package com.example.quartz;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName CollectionTest
 * @Author fhb
 * @Date 2021/8/29 22:35
 * @Version 1.0
 **/
public class CollectionTest {

    @Test
    public void collection(){
        List<String> list = new ArrayList<>();
        System.out.println("list="+list); // []
        list.add("a");
        list.add("b");
        list.add("a");
        Set<String> set = new HashSet<>();
        set.addAll(list);
        System.out.println("list="+list); //list=[a, b, a]
        System.out.println("set="+set); //set=[a, b]
        test(set);
        System.out.println("set22="+set); //set22=[a, b, d, f]
    }

    public void test(Set<String> set){
        set.add("d");
        set.add("f");
        System.out.println("set11="+set);  //set11=[a, b, d, f]
    }

}
