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
            System.out.println("(1) - insert Person");
            System.out.println("(0) - beenden");

            while (!scanner.hasNextInt()) scanner.next();
            choice = scanner.nextInt();
            switch (choice) {

                case 1:


                    String insertPerson = "INSERT INTO person " +
                            "(name, gebdatum, semester, raum, rang) " +
                            "VALUES(?, ?, ?, ?, ?)";

                    System.out.println("(1) einfache Person einfügen");
                    System.out.println("(2) einen Student einfügen");
                    System.out.println("(3) einen wissenschaftlichen Mitarbeiter einfügen");
                    System.out.println("(4) einen Professor anlegen");
                    System.out.println("(0) zurück");

                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            sql.insertPerson(insertPerson);
                            break;
                        case 2:
                            sql.insertStudent(insertPerson);
                            break;
                        case 3:
                            sql.insertMitarbeiter(insertPerson);
                            break;
                        case 4:
                            sql.insertProf(insertPerson);
                            break;
                        default:
                            break;
                    }
                    break;

                case 0:
                    running = false;
                    c.closeConnection();
                    break;

                default:
                    System.out.println("!!! keine gültige Eingabe !!!");
                    break;
            }
        }
    }
}

