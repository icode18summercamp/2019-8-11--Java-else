package com.zhouxin.lianXi.er;

public class Personl {
	    String a;
	    String b;
	    private int age=10;
	    
	    public Personl()
	    {
	    	
	    }
	    public Personl(String name)
	    {
	    	a=name;
	    }
	    public Personl(String name,String sex)
	    {
	    	a=name;
	    	b=sex;
	    }
	    public String getname()
	    {
	    	return a;
	    }
	    public void setname(String c)
	    {
	    	a=c;
	    }
	    public String getsex()
	    {
	    	return b;
	    }
	    public int getage()
	    {
	    	return age;
	    }
	    public void speak(String d)
	    {
	    	System.out.println("123456"+d);
	    }
}
