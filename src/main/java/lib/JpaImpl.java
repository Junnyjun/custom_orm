package lib;
import core.Entity;
import entity.Member;
import jdbc.JdbcConnector;
import utils.EntityExtractor;

import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class JpaImpl<ENTITY,ID> implements JpaRepository<ENTITY, ID> {
    private final ConcurrentHashMap<ENTITY,ID> data = new ConcurrentHashMap<>();
    private final Class<?> clazz;
    private static JpaImpl instance;

    static {
        try { instance = new JpaImpl(Member.class);}
        catch (Exception e) {throw new RuntimeException("JPA LOAD FAILED");}
    }

    public JpaImpl(Class<?> clazz) {
        this.clazz = clazz;
    }
    public static JpaImpl getInstance(){
        return JpaImpl.instance;
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public List<Entity> findAll() throws IntrospectionException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InstantiationException, NoSuchFieldException, FileNotFoundException {
        // 쿼리 생성기로 쪼개야됨
        String tableName = EntityExtractor.getTableNameFromEntity(clazz);
        Field[] fields = Member.class.getDeclaredFields();

        StringBuilder queryBuilder = new StringBuilder("select").append(" ");
        List<String> queryFieldList = Collections.synchronizedList(new ArrayList<>());
        queryBuilder.append(
                Arrays.stream(fields).map(field -> {
                    queryFieldList.add(field.getName());
                    return field.getName();
                }).collect(Collectors.joining(", "))
        );
        queryBuilder.append(" from ").append(tableName);
        System.out.println(queryBuilder.toString());

        try {
            JdbcConnector jdbcConnector = new JdbcConnector();
            ResultSet rs = jdbcConnector.selectAll(queryBuilder.toString());

            while (rs.next()) {
                for (String field : queryFieldList) {
                    System.out.println(rs.getString(field));
                }
            }

            Field idField = clazz.getClass().getDeclaredField();
            idField.setAccessible(true);
            idField.trySetAccessible();
//            idField.set(member12,1L);
            idField.set(Long.class,1L);


            // 실험중
            Class[] type = {String.class};
            Class classDefinition = Class.forName("entity.Member");

            Class[] type1 = new Class[fields.length];
            type1[0] = Long.class;
            type1[1] = String.class;

            // 1. 최적화 해야한다
            // 2. 필드수에  따라 알맞은 값을 넣는다.


//            pd = new PropertyDescriptor(/, Object.class);
//            pd.getWriteMethod().invoke(member12, "id", "1");
//            pd = new PropertyDescriptor("name", Member.class);
//            Member result =  (Member) pd.getWriteMethod().invoke(member12, "name", "test");
//            Method setter = pd.getWriteMethod();

//            Field field = pd.getPropertyEditorClass().getField("name");
//            setter.setAccessible(true);

            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Member o = (Member) constructor.newInstance(1, "test");
            System.out.println("test!");

            return null;
        } finally {

        }
    }

    @Override
    public void save(Entity entity) {

    }
}
