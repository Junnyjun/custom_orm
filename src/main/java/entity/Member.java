package entity;

import core.Entity;
import core.Id;

@Entity(name = "member")
public class Member {
    @Id
    private long id;
    private String name;

    protected Member() {}

    public long getId() {
        return id;
    }
}
