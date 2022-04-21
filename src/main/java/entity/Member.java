package entity;

import core.Entity;
import core.Id;

@Entity(name = "member")
public class Member {
    @Id
    private Long id;
    private String name;

    public Member() {}

    public long getId() {
        return id;
    }
}
