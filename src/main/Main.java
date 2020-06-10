package main;

import connection.Connect;
import sql.Sql;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Connect c = new Connect();
        c.setConnection("postgres", "dbprak26", "kranhaus26");
        Connection conn = c.getConnection();
        Sql sql = new Sql(conn);

        int choice = 0;
        boolean running = true;

        Scanner scanner = new Scanner(System.in);

        while (running) {

            System.out.println("\nBitte wählen:\n");
            System.out.println("(1) - pipi kaka");
            System.out.println("(0) - beenden");

            while (!scanner.hasNextInt()) scanner.next();
            choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    System.out.println("echt lol ey");
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("!!! keine gültige Eingabe !!!");
                    break;
            }


            //String query = <Some SQL statement>;
            //sql.update(query);
            //sql.select(<Some SQL statement>);
            c.closeConnection();
        }
    }
}