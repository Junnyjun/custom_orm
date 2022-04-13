package utils;

import core.Id;

import java.lang.reflect.Field;

/**
 * Entity Annotaion Validatior use reflection api
 */
public class EntityValidator {

    public static void checkRequired(String classNameWithPackage) throws ClassNotFoundException {
        Class<?> c = Class.forName(classNameWithPackage);
        idFieldValidation(c);
    }

    private static void idFieldValidation(Class<?> c) throws ClassNotFoundException {
        Field[] fields = c.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Id.class)) {
                throw new RuntimeException("Id Annotaion Required!");
            }
        }
    }

}
