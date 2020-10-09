package com.example.rerflect;

import java.lang.annotation.*;

/**
 * 联系反射操作注解(利用注解实和反射完成类和表结构的映射关系)
 **/
public class ReflectDemo {
    public static void main(String[] args) throws Exception{
      Class c1 = Class.forName("com.example.rerflect.Student");
      //通过反射获取注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation:annotations) {
            System.out.println("annotation="+annotation);
            //上面打印的结果：annotation=@com.example.rerflect.Table(value=db_Student)
        }
        //获取注解value的值
        Table table = (Table)c1.getAnnotation(Table.class);
        String value =table.value();
        System.out.println(value); //打印结果：db_Student

        //获取类指定的注解
        java.lang.reflect.Field f=c1.getDeclaredField("name");
        Field annonation = f.getAnnotation(Field.class); //这里的Field类型是表示注解Field
        System.out.println(annonation.columnName()); //打印结果：db_name
        System.out.println(annonation.type()); //打印结果：varchar
        System.out.println(annonation.length()); //打印结果：10
    }
}

/**
 *  实体类(假设对应数据库的学生表)
 */
@Table("db_Student")
class Student{
    @Field(columnName = "db_id",type="int",length=10)
    private int id;
    @Field(columnName = "db_name",type="varchar",length=10)
    private String name;
    @Field(columnName = "db_age",type="int",length=3)
    private int age;

    public Student() {}

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//类名的注解
@Target(ElementType.TYPE) //表示该注解作用在类上
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

//属性的注解
@Target(ElementType.FIELD) //表示该注解作用在字段上
@Retention(RetentionPolicy.RUNTIME)
@interface Field{
    String columnName(); //列名
    String type(); //类型
    int length(); //字段长度
}