package main;

import connection.Connect;
import sql.Sql;

import java.sql.Connection;

public class Main {

    public static void main(String args[]){
        Connect c = new Connect();
        c.setConnection("postgres", "dbprak26", "kranhaus26");
        Connection conn = c.getConnection();
        Sql sql = new Sql(conn);
        //String query = <Some SQL statement>;
        //sql.update(query);
        //sql.select(<Some SQL statement>);
        c.closeConnection();
    }
}