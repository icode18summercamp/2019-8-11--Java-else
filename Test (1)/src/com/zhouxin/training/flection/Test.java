package com.zhouxin.training.flection;

import com.zhouxin.training.flection.model.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test
{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException
    {
        Class person = Person.class;
        Class per2 = new Person().getClass();
        try
        {
            Class per3 = Class.forName("com.zhouxin.training.flection.model.Person");
        } catch (ClassNotFoundException e)
        {
            System.out.println("在指定路径下，找不到你说的类");
            e.printStackTrace();
        }


        System.out.println(person.getSimpleName());
        System.out.println(person.getCanonicalName());

        Constructor constructor1 = person.getConstructor(String.class, String.class);
        constructor1.setAccessible(true);// 告诉jvm私有属性或者私有方法是可变值的，如果不设置的话，就会报错
        Person createPerson = (Person) constructor1.newInstance("dd", "男");
        createPerson.think();
        System.out.println("姓名：" + createPerson.getName() + " 性别：" + createPerson.getSex());

        Method method1 = person.getDeclaredMethod("speak", String.class);
        method1.invoke(createPerson, "我很帅");

        Field field = person.getDeclaredField("name");
        field.setAccessible(true);
        field.set(createPerson, "周鑫");
        System.out.println(createPerson.getName());


        

    }
}
