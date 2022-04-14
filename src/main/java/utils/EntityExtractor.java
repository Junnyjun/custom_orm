package utils;

import core.Entity;

import java.util.Optional;

public class EntityExtractor {

    public static String getTableNameFromEntity(Class<?> c) {
        Entity entity = Optional.ofNullable(c.getAnnotation(Entity.class))
                .orElseThrow(() -> new NullPointerException("Entity 클래스가 아닙니다."));
        return entity.tableName().equals("") ? c.getSimpleName().toLowerCase() : entity.tableName();
    }

}
