package lib;

import core.Entity;

import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JpaRepository<ENTITY, ID> {
    Optional<Entity> findById(ID id);
    List<Entity> findAll() throws IntrospectionException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InstantiationException, NoSuchFieldException, FileNotFoundException;
    void save(Entity entity);
}
