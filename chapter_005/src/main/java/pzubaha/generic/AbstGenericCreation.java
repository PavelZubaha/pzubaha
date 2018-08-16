package pzubaha.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Chapter_005. Collection. Pro.
 * <p>
 * Class how we can get parametrized type.
 * Created 03.01.2018.
 *
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public abstract class AbstGenericCreation<T> {
    @SuppressWarnings ("unchecked")
    public Class<T> getTypeParameterClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<T>) paramType.getActualTypeArguments()[0];
    }

    public static void main(String[] args) {
        class GenericCreation extends AbstGenericCreation<String> {

        }
        AbstGenericCreation agc = new GenericCreation();
        Class parameter = agc.getTypeParameterClass();
        System.out.println(parameter);
        Constructor[] c = parameter.getDeclaredConstructors();
        System.out.println(c.length);
        for (Constructor constr : c) {
            System.out.println(constr);
            System.out.println("\tParameter types: ");
            for (Class p : constr.getParameterTypes()) {
                System.out.println("      " + p);
            }
        }
    }
}

