package entity;

import core.Entity;
import core.Id;

@Entity(name = "member")
public class Member {
    @Id
    private String id;
    private String name;

    public Member() {}

    public String getId() {
        return id;
    }
}
