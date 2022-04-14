package entity;

import core.Entity;
import core.Id;

@Entity(name = "order")
public class Order {
    @Id
    private Long id;

    public Order() {}
}
