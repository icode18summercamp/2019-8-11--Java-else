package com.zhouxin.training.flection.model;

import java.util.Date;

public class Person
{
	private String name;
	private String sex;
	public int age;
	private Date birthday;

	public Person()
	{
		System.out.println("I am created!");
	}

	public Person(String name)
	{
		this.name = name;
	}

	public Person(String name, String sex)
	{
		this.name = name;
		this.sex = sex;
	}

	private Person(String name, String sex, int age)
	{
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	protected Person(String name, String sex, int age, Date birthday)
	{
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
	}

	public void speak(String dds)
	{
		System.out.println("I am a person " + dds);
	}

	public void think()
	{
		System.out.println("All people like thinking!");

	}

	private void privateMethod()
	{
		System.out.println("This is a private method");
	}

	@Override
	public String toString()
	{
		return "name: " + this.name + ", sex: " + this.sex + ", age: "
				+ this.age;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public int getAge()
	{
		return age;
	}

	public String getName()
	{
		return name;
	}

	public String getSex()
	{
		return sex;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}
}
