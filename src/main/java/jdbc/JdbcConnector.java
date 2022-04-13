package jdbc;

import java.sql.*;

public class JdbcConnector {

    private Connection conn  = null;
    private final String url = "jdbc:mysql://localhost:3306/junny";
    private final String id = "junny";
    private final String password= "1234";

    public JdbcConnector() throws ClassNotFoundException {
        try{
            this.conn = DriverManager.getConnection(url,id,password);
        }catch (SQLException e){
            e.printStackTrace();
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
}
