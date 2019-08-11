package com.zhouxin.training.annonation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ColumnTest
{
    public static void main(String[] args)
    {
        Class<?> clazz = TeacherEntity.class;
        Field fields[] = clazz.getDeclaredFields();

        for (Field field : fields)
        {
            Annotation annotation = field.getAnnotation(Column.class);
            if (annotation != null) {
                String value = ((Column) annotation).value();
                System.out.println("获取到的注解的值是：" + value);
            }

        }
    }
}
