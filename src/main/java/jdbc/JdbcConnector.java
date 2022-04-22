package jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnector {
    private final DataSource dataSource;

    public JdbcConnector() throws SQLException {
        this.dataSource = new MyDataSource();
    }

    public ResultSet queryProvider(String query){
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
           return pstmt.executeQuery();
        }catch (SQLException e){
            throw new IllegalArgumentException("SQL IS WRONG");
        }
    }

}
