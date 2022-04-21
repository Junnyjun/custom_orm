package jdbc;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

public class JdbcConnector {

    private Connection conn  = null;

    public JdbcConnector() throws ClassNotFoundException, FileNotFoundException, SQLException {
        try{
            DataSourceValue value = Reader.getYaml();
           this.conn = DriverManager.getConnection(value.getUrl(),value.getId(),value.getPassword());
        }catch (SQLException e){
            throw new SQLException("DATASOURCE WRONG");
        }
    }
    public void checkConnection() throws SQLException {
        Statement stmt = conn.createStatement();
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement("select 1");
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            String result = rs.getString("1");
            System.out.println("result allways 1 : "+ result);
        }
    }

    public ResultSet selectAll(String query) throws SQLException {
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(query);
        return pstmt.executeQuery();
    }

}
