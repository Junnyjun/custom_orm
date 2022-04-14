package lib;

import java.util.List;
import java.util.Optional;

public interface JpaRepository<T, Id extends Number> {
    Optional<T> findById(Id id);
    List<T> findAll();
    void save(T entity);
}
