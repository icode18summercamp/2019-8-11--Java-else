package com.zhouxin.training.annonation;

import com.zhouxin.training.flection.model.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        // 获取TeacherEntity这个类的描述对象
        Class teacher = TeacherEntity.class;
        // 获取TeacherEntity里面的全部的属性
        Field[] fields = teacher.getDeclaredFields();

        // 遍历这个属性数组，找到我们要注入对象的属性
        for (Field field : fields)
        {
            // 得到属性上面的注解的描述对象
            Annotation annotation =
                    field.getAnnotation(Column.class);
            // 如果annotation==null，就说明该属性上面没有Column注解
            if (annotation == null)
            {
                continue;
            }
            // 判断该属性是否是需要注入对象的属性
            if (field.getName().equals("person"))
            {
                System.out.println(field.getName() + "的注解的value值:"
                        + ((Column) annotation).value());
                // 通过注解获取到Person类的全限定名称，然后获取Person类的描述对象
                Class<?> person = Class.forName(((Column) annotation).value());
                // 设置私有属性可以更改值
                field.setAccessible(true);
                // 开始注入Person的对象
                field.set(teacher.newInstance(), (Person) person.newInstance());
                // 验证我们是否注入对象成功
                String methodName = "get" + field.getName().toUpperCase().charAt(0)
                        + field.getName().toLowerCase().substring(1);
                Method method = teacher.getMethod(methodName);
                Person person1 = (Person) method.invoke(teacher.newInstance());
                person1.speak("我成功注入了");
            }

//
//            // 第一种创建对象的方法
//            Person per1 = (Person) person.getConstructor().newInstance();
//
//            // 第二种创建对象的方法
//            Object obj = person.newInstance();
//            Person per2 = (Person) obj;

        }

        // 是怎么通过反射获取到的注解
        // 怎么通过反射获取到注解里面的value
    }
}
