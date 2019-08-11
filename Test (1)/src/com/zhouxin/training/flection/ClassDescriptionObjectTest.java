package com.zhouxin.training.flection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zhouxin.training.flection.model.Man;
import com.zhouxin.training.flection.model.Person;

/**
 * Class类的对象用来表示运行时类或接口的信息。 下面是对Class类的一些常用方法的介绍及使用
 * 
 * @author 周鑫 create on 2018/07/13
 */
public class ClassDescriptionObjectTest
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException,
			InstantiationException, NoSuchFieldException,
			InvocationTargetException
	{
		// 通过获取Class对象可以获取到这个类的所有信息，包括属性，行为（方法），私有的，公有的
		// 获取描述对象Class的两种方法
		Class<?> clazz1 = Person.class;
		// 通过对象的getClass()方法获取Class描述对象
		Class<?> clazz2 = new Person().getClass();
		// 第三种获取Clss对象的方式，通过forName()方法，但是forName()里面的参数是要创建类的全限定名称
		Class<?> clazz3 = Class
				.forName("com.zhouxin.training.flection.model.Person");

		// getCanonicalName()获取的这个基础类的规格化名称,如果这个基础类没有规范化名称，则返回null
		System.out.println("通过.class获取Class对象：获取的是" + clazz1.getCanonicalName()
				+ "的描述对象,类名：" + clazz1.getSimpleName());
		System.out.println("通过getClass()获取Class对象：获取的是"
				+ clazz2.getCanonicalName() + "的描述对象,类名："
				+ clazz2.getSimpleName());
		System.out.println("通过Class.forName()获取Class对象：获取的是"
				+ clazz3.getCanonicalName() + "的描述对象,类名："
				+ clazz3.getSimpleName());

		System.out.println();


		// 获取类里面的构造函数
		// 构造函数是一种特殊的方法，主要用来在创建对象时初始化对象，
		// Java中有一个Contructor类专门用来描述构造函数，该类用于描述类的单个构造方法，可以用来获取类的构造方法名称，访问权限等，以及通过Constructor来创建类的实例
		// 使用getConstructor()方法来获取某个构造函数，方法里面的参数是你构造函数的参数类别的Class对象，有就放，没有就不放
		Constructor<?> constructor = clazz1.getConstructor();
		System.out.println(constructor.getName() + " 的全限定名称是 "
				+ constructor.toString());
		Constructor<?> constructor1 = clazz1.getConstructor(String.class);
		System.out.println(constructor1.getName() + " 的全限定名称是 "
				+ constructor1.toString());
		// 错误的调用，因为getConstructor()方法只能获取public的方法,这样调用会报错
		// Constructor<?> constructor2 = clazz1.getConstructor(String.class,
		// String.class, int.class);
		// System.out.println(constructor2.getName());
		// 要获取类的所有构造函数就用Constructor类中的getDeclaredConstructor()或getDeclaredConstructors()方法，前者是获取单个构造方法，后者是获取所有的构造函数，返回一个Constructor[]数组
		Constructor<?> constructor3 = clazz1.getDeclaredConstructor(
				String.class, String.class, int.class);
		System.out.println(constructor3.getName() + " 的全限定名称是 "
				+ constructor3.toString());
		Constructor[] constructors = clazz1.getDeclaredConstructors();
		System.out.println();
		for (Constructor temp : constructors)
		{
			System.out.println(temp.getName() + " 的全限定名称是 " + temp.toString());
		}

		System.out.println();
		// 获取类里面的方法
		// Java中有一个Method类专门用来描述函数方法的，Method类的对象用于描述类的单个方法（不包括构造函数）
		// 可以通过Method类来获取方法的访问权限，参数类型，返回值类型，并且可以通过Method对象来动态执行方法
		// getMethod(String name, Class...parameterTypes)方法
		// 此方法用于获取指定名称和参数类型的公有方法描述对象；可以获取除了本身定义的方法外，还包含继承自父类的方法。
		Method methods[] = clazz1.getMethods();
		for (Method method : methods)
		{
			System.out.println("获取到的方法名是：" + method.getName());
		}
		System.out.println();
		// 类似的还有getMethods()方法获取所有的共有方法的描述对象，getDeclaredMethod()以及getDeclaredMethods()方法获取单个方法或者所有的方法，不区分权限
		Method privateMethod[] = clazz1.getDeclaredMethods();
		for (Method method : privateMethod)
		{
			System.out.println("获取到的方法名是" + method.getName());
		}

		System.out.println();
		// 获取类里面的属性值，可以通过以下几个方法
		// 通过getFields()获取所有的共有的属性
		Field[] fields = clazz1.getFields();
		for (Field field : fields)
		{
			System.out.println("获取到的属性名是: " + field.getName());
		}
		// getDeclaredField()或getDeclaredFields()获取所有的属性，包括私有的，受保护的，以及公共的。
		Field[] allField = clazz1.getDeclaredFields();
		for (Field field : allField)
		{
			System.out.println("获取到的属性名：" + field.getName());
		}

		System.out.println();
		// 通过反射可以实现创建对象，也可以执行某个方法，也可以给某个属性赋值
		// 第一个，通过反射来创建对象，有两种方式
		// 第一种，通过类的描述对象来进行创建对象,Class类有个newInstance()方法，通过这个方法可以实现我们想要的功能
		try
		{
			// newInstance()方法调用的是实体类里面的无参构造方法，所以如果实体类里面没有无参构造方法，就会catch
			// Exception
			Object oject = clazz1.newInstance();
			Person person = (Person) oject;
//			person.speak();
			person.think();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

		System.out.println();
		// 第二种方法就是先通过getConstructor()方法获取到无参构造方法的描述对象，然后再通过newInstance()方法创建对象
		try
		{
			Object object = Man.class.getConstructor().newInstance();
			Man man = (Man) object;
			man.speak();
			man.think();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}

		System.out.println();
		// 通过反射来给属性赋值，或者修改属性的值
		// 要实现上述功能，则需要用到invoke(Object object, Object value)，该方法用于动态的给属性赋值
		// 第一个参数是指定的对象，表示要修改属性的对象，另一个参数，表示修改后的值
		Class clazz = Person.class;
		Object person = clazz.getConstructor().newInstance();
		Field field = clazz.getField("age");
		System.out.println(person.toString());
		field.set(person, 20);
		System.out.println(person.toString());

		System.out.println();
		// 如果要给私有属性赋值，就必须要让虚拟机知道私有属性是可以修改的
		// 通过setAccessible(boolean flg)方法来设置访问对象的accessible标志
		// 当该标志为true时，反射的对象在使用时取消Java语言访问检查，此时就可以顺利的使用反射对象了
		Field nameField = clazz.getDeclaredField("name");
		System.out.println(person.toString());
		nameField.setAccessible(true);
		nameField.set(person, "周鑫");
		System.out.println(person.toString());

		System.out.println();
		// 通过反射来执行方法
		// 要动态的执行类里面的方法，首先要先获取描述该方法的Method对象，获取了Method对象之后，通过Method类中的invoke(Object
		// object, Object...args)
		// 通过invoke()方法来执行指定对象的方法。该方法有两个参数，第一个参数是指定的对象，第二个参数是执行方法需要传入的参数
		Method method = clazz.getMethod("speak");
		method.invoke(person);
		clazz.getMethod("setName", String.class).invoke(person, "沿途不枉为少年");
		Method method1 = clazz.getMethod("getName");
		System.out.println("这个人的姓名：" + method1.invoke(person));

	}
}