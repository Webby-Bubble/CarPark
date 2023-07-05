package carpark;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class personClass = Person.class;
        Constructor constructor = personClass.getConstructor(String.class,int.class);
        Object person = constructor.newInstance("张三",18);
        System.out.println(person);
        
    }
}
