package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private Connection conn;

    public Connect(){
    }

    public void setConnection(String user, String password){
        String server = "feuerbach";
        String url = "jdbc:postgresql://"+server+".nt.fh-koeln.de/postgres?user="+user+"&password="+password;
        try {
            this.conn = DriverManager.getConnection(url);
            this.conn.setAutoCommit(true);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        return this.conn;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}