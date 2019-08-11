package com.zhouxin.training.annonation;

import com.zhouxin.training.flection.model.Person;

public class TeacherEntity
{
    @Column(value = "com.zhouxin.training.flection.model.Person")
    private static Person person;
    @Column(value = "name")
    private String name;
    @Test(value = "age")
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public static Person getPerson()
    {
        return person;
    }

    public static void setPerson(Person person)
    {
        TeacherEntity.person = person;
    }
}
