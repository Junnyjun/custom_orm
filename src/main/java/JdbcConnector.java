import java.sql.*;

public class JdbcConnector {

    private Connection conn  = null;
    private final String url = "jdbc:mysql://localhost:3306/orm-db";
    private final String id = "codekata";
    private final String password= "12345";

    public JdbcConnector() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
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
