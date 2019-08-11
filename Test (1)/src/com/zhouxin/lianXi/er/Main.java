package com.zhouxin.lianXi.er;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		Class teacher=er.class;
		//��ȡȫ������
        Annotation annotations=teacher.getAnnotation(Colum.class);
        Field[] fields=teacher.getDeclaredFields();
        //������������
        for(Field field:fields)
        {
        	//�õ����������ע�����������(Colum)
        	Annotation annotation=field.getAnnotation(Colum.class);
        	//annotation==null˵��������û��Columע��
        	if(annotation==null)
        	{
        		continue;
        	}//�ж������Ƿ�����Ҫע�������
        	if(field.getName().equals("person1"))
        	{
        	  System.out.println(field.getName()+"��ע���value��ֵ"+((Colum)annotation).value());
        	  Class<?> person=Class.forName(((Colum)annotation).value());
        	  field.setAccessible(true);
        	  //��ʼע��personl����
        	  field.set(teacher.newInstance(),(Personl)person.newInstance());
        	  String methodName="get"+field.getName().toUpperCase().charAt(0)+field.getName().toLowerCase().substring(1);
        	  Method method = null;
			try {
				method = teacher.getMethod(methodName);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Personl person1=null;
			try {
				person1 = (Personl)method.invoke(teacher.newInstance());
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	  person1.speak("�ҳɹ�ע����");
        	}     
   
        }
	}

}
