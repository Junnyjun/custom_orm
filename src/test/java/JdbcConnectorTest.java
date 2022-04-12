import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

class JdbcConnectorTest {
    JdbcConnector jdbcConnector = null;
    @BeforeEach
    void setup() throws ClassNotFoundException {
        this.jdbcConnector = new JdbcConnector();
    }

    @Test
    @DisplayName("커넥션 확인")
    void checkConnection() throws SQLException, ClassNotFoundException {
        jdbcConnector.checkConnection();

        Class s = Class.forName("JpaImpl");
        System.out.println(s);
    }


}