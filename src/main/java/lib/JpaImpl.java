package lib;
import core.Entity;
import entity.Member;
import jdbc.JdbcConnector;
import utils.EntityExtractor;

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

    public JpaImpl(Class<Entity> clazz) {
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
    public List<Entity> findAll(){
        String tableName = EntityExtractor.getTableNameFromEntity(clazz);
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder queryBuilder = new StringBuilder("select").append(" ");
        List<String> queryFieldList = Collections.synchronizedList(new ArrayList<>());
        queryBuilder.append(
                Arrays.stream(fields).map(field -> {
                    queryFieldList.add(field.getName());
                    return field.getName();
                }).collect(Collectors.joining(", "))
        );
        queryBuilder.append(" from ").append(tableName);


        try {
            List data = new ArrayList<ENTITY>();
            JdbcConnector jdbcConnector = new JdbcConnector();
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            ResultSet rs = jdbcConnector.queryProvider(queryBuilder.toString());

            while (rs.next()) {
                Object entity =  constructor.newInstance();

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

        }catch (SQLException e){
            throw new IllegalArgumentException("SQL EXCEPTION ERROR");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("ERROR");
    }

    @Override
    public Optional<ENTITY> save(Entity entity) {

        return null;
    }
}
