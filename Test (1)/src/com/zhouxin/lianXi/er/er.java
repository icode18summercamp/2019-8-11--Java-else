package com.zhouxin.lianXi.er;

public class er {
	@Colum(value="lianxi.er.Personl")
	private static Personl person1; 
	
    @Colum(value="name")
	private String name;
    
	@Colum(value="age")
	private int age;
	
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
	
	public static Personl getPerson1() {
		return person1;
	}
	
	public static void setPerson1(Personl person1) {
		er.person1 = person1;
	}
	
}