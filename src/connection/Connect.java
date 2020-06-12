package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Connect {

    private Connection conn;

    public Connect(){
    }

    public void setConnection(){
        int choice;
        String url = null;
        System.out.println("Zu (1)remote oder (2)local Server connecten?");
        Scanner scan = new Scanner(System.in);
        choice = Integer.parseInt(scan.nextLine());
        switch(choice){
            case 1:
                url = "jdbc:postgresql://feuerbach.nt.fh-koeln.de/postgres?user=dbprak26&password=kranhaus26";
                break;
            case 2:
                url = "jdbc:postgresql://[::1]:5432/db2?user=postgres&password=yellow";
                break;
        }

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