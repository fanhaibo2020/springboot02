package com.example.rerflect;

/**
 * @ClassName 测试枚举类的方法
 * @Author fhb
 * @Date 2020/5/30 22:05
 * @Version 1.0
 **/
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;  //Season1.SUMMER相当于是Season1类中的对象SUMMER
        //toString():返回枚举类对象的名称
        System.out.println(summer.toString());  //打印结果：SUMMER

//        System.out.println(Season1.class.getSuperclass());
        System.out.println("****************");
        //values():返回所有的枚举类对象构成的数组
        Season1[] values = Season1.values();
        System.out.println("values="+values);  //打印结果：values=[Lcom.example.rerflect.Season1;@77f03bb1   这是一个内存地址
        for(int i = 0;i < values.length;i++){
            System.out.println(values[i]);
            values[i].show();
            //此处打印的结果
//            SPRING
//            春天在哪里？
//            SUMMER
//            宁夏
//            AUTUMN
//            秋天不回来
//            WINTER
//            大约在冬季
        }
        System.out.println("****************");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }

        //valueOf(String objName):返回枚举类中对象名是objName的对象。
        Season1 winter = Season1.valueOf("WINTER");  //winter会报错
        //如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
//        Season1 winter = Season1.valueOf("WINTER1");
        System.out.println(winter);  //打印结果：WINTER
        winter.show(); //打印结果：大约在冬季
        System.out.println(winter.getSeasonDesc()); //打印结果：冰天雪地
        System.out.println(winter.getSeasonName()); //打印结果：冬天
    }
}

interface Info{
    void show();
}

//使用enum关键字枚举类
enum Season1 implements Info{
    //1.提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    SPRING("春天","春暖花开"){   //Season1可以理解为一个类，SPRING是Season1 new出来的一个对象
        @Override
        public void show() {
            System.out.println("春天在哪里？");  //重写Info中的show方法
        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    AUTUMN("秋天","秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天","冰天雪地"){
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };

    //2.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器,并给对象属性赋值

    private Season1(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
//    //4.其他诉求1：提供toString()
//
//    @Override
//    public String toString() {
//        return "Season1{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }
}
