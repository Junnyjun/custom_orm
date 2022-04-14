package entity;

import core.Entity;
import core.Id;

@Entity(tableName = "tb_member")
public class Member {
    @Id
    private Long id;

    protected Member() {}
}
