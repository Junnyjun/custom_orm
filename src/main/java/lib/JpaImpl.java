package lib;

import java.util.concurrent.ConcurrentHashMap;

public class JpaImpl<ENTITY,ID> implements JpaRepository<ENTITY, ID> {
    private final ConcurrentHashMap<ENTITY,ID> data = new ConcurrentHashMap<>();
    private static JpaImpl instance = new JpaImpl();


    static {
        try{ instance = new JpaImpl();}
        catch (Exception e) {throw new RuntimeException("JPA LOAD FAILED");}
    }

    public JpaImpl() {}
    public static JpaImpl getInstance(){
        return JpaImpl.instance;
    }

    public void findById(Long id){

    }
}
