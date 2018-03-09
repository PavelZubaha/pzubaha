package pzubaha.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Chapter_005. Collection. Pro.
 * <p>
 * Class how we can get parametrized type.
 * Created 03.01.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public final class A {
    public Class<?> getGeneric(final List<?> genericList) {
        Class<?> c = genericList.getClass();
        Type t = c.getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        c = (Class<?>) pt.getActualTypeArguments()[0];
        return c;
    }
    public static void main(String... args) {
        A a = new A();
        System.out.println(a.getGeneric(new ArrayList<B>() {

        }).getSimpleName()); //OUT: class com.learnjava.polymorphizm.B
    }
}
class B {
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.getClass());
    }
}
