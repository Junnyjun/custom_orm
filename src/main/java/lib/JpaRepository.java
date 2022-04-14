package lib;

import core.Entity;

import java.util.List;
import java.util.Optional;

public interface JpaRepository<ENTITY, ID> {
    Optional<Entity> findById(ID id);
    List<Entity> findAll();
    void save(Entity entity);
}
