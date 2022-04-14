package lib;

import entity.Member;
import utils.EntityExtractor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepositoryImpl<DTO> implements JpaRepository<Member, Number> {
    private final ConcurrentHashMap<DTO, Long> data = new ConcurrentHashMap<>();
    private final Class<?> c;
    private static MemberRepositoryImpl instance;

    static {
        try { instance = new MemberRepositoryImpl<Member>(Member.class);}
        catch (Exception e) {throw new RuntimeException("JPA LOAD FAILED");}
    }

    public MemberRepositoryImpl(Class<?> c) {
        this.c = c;
    }

    public static MemberRepositoryImpl getInstance() {
        return MemberRepositoryImpl.instance;
    }

    @Override
    public Optional<Member> findById(Number number) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        String tableName = EntityExtractor.getTableNameFromEntity(c);
        Field[] fields = Member.class.getDeclaredFields();

        StringBuilder queryBuilder = new StringBuilder("select").append(" ");
        Arrays.stream(fields).forEach(field -> {
            queryBuilder.append(field.getName());
        });
        return null;
    }

    @Override
    public void save(Member entity) {
    }

}
