package lib;

import java.util.List;
import java.util.Optional;

public interface JpaRepository<Entity, Id extends Number> {
    Optional<Entity> findById(Id id);
    List<Entity> findAll();
    void save(Entity entity);
}
