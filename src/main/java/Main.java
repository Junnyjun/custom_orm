import core.Id;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        JdbcConnector jdbcConnector = new JdbcConnector();
        idFieldValidation("entity.Member");
    }

    private static void idFieldValidation(String classNameWithPackage) throws ClassNotFoundException {
        Class c = Class.forName(classNameWithPackage);
        Field[] fields = c.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Id.class)) {
                throw new RuntimeException("Id Annotaion Required!");
            }
        }
    }
}
