import jdbc.JdbcConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

class JdbcConnectorTest {
    JdbcConnector jdbcConnector = null;

    @BeforeEach
    void setup() throws FileNotFoundException, SQLException {
        this.jdbcConnector = new JdbcConnector();
    }


}