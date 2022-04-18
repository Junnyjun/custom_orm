package entity;

import core.Entity;
import core.Id;

@Entity(name = "member")
public class Member {
    @Id
    private Long id;
    private String name;

    public Member() {}

//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
