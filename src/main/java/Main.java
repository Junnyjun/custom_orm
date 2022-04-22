import entity.Member;
import lib.JpaImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Member> data  = JpaImpl.getInstance().findAll();
    }
}
