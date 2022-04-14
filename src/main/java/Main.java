import core.Entity;
import jdbc.JdbcConnector;
import lib.JpaImpl;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
//        JdbcConnector jdbcConnector = new JdbcConnector();
        JpaImpl.getInstance().findAll();
//        idFieldValidation("entity.Member");

//        MemberRepositoryImpl.getInstance().findAll();
    }

    private static void idFieldValidation(String classNameWithPackage) throws ClassNotFoundException {
        Class c = Class.forName(classNameWithPackage);
        String simpleName = c.getSimpleName();
        Method[] declaredMethods = c.getDeclaredMethods();
        Class[] declaredClasses = c.getDeclaredClasses();
        Method[] methods = c.getMethods();
        Class<? extends Class> aClass = c.getClass();

        Entity entity = (Entity) c.getAnnotation(Entity.class);

        Arrays.stream(declaredMethods).forEach(e ->{
            System.out.println("e = " + e);
            Entity annotation = e.getAnnotation(Entity.class);
        });

    }
}
