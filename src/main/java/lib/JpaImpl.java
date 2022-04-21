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

            // 엔티티는 여기다가
            List data = new LinkedList();
            while (rs.next()) { // 1,kim,2,Lee
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                Member entity = (Member) constructor.newInstance();

                for (String field : queryFieldList) {
                    Field idField = entity.getClass().getDeclaredField(field);
                    idField.setAccessible(true);
                    idField.set(String.class, rs.getString(field));
                    idField.setAccessible(false);
                }
                data.add(entity); // size -> 4 1,null null,kim 2,null ...
            }
            System.out.println(data.toString());

            // 여기서 리스트로 뱉음
            // Abdser1123 -> 객체 -> 리스트에 박음




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
