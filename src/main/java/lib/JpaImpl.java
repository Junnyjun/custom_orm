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

public class JpaImpl<ENTITY,ID,T extends Object> implements JpaRepository<ENTITY, ID> {
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

            List data = new LinkedList();
            Constructor<?> constructor = clazz.getDeclaredConstructor();

            constructor.setAccessible(true);

            while (rs.next()) { // 1,kim,2,Lee
                T entity = <T> clazz.getClass().cast(constructor.newInstance());

                for (String field : queryFieldList) {
                    Field idField = entity.getClass().getDeclaredField(field);
                    idField.setAccessible(true);

                    if(idField.getType().equals(Long.class)){
                        idField.set(entity, rs.getLong(field));
                    }else if(idField.getType().equals(String.class)){
                        idField.set(entity, rs.getString(field));
                    }
                    idField.setAccessible(false);
                }
                data.add(entity);
            }
            return data;

        }catch (Exception e){
            throw new IllegalArgumentException("WRONG");
        }
    }

    @Override
    public void save(Entity entity) {

    }
}
