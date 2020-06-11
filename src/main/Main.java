package main;

import connection.Connect;
import sql.Sql;

import java.sql.Connection;
import java.sql.SQLException;
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

            System.out.println("--- Bitte wählen ---");
            System.out.println("(1) - insert Person");
            System.out.println("(2) - select Person via Nr");
            System.out.println("(3) - ");
            System.out.println("(0) - beenden");

            while (!scanner.hasNextInt()) scanner.next();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {

                case 1:
                    String insertPerson = "INSERT INTO person " +
                            "(name, gebdatum, semester, raum, rang) " +
                            "VALUES(?, ?, ?, ?, ?)";
                    String insertAnwesenheit = "INSERT INTO anwesenheit " +
                            "(pnr, vorlnr) " +
                            "VALUES(?, ?)";

                    System.out.println("(1) einfache Person einfügen");
                    System.out.println("(2) einen Student einfügen");
                    System.out.println("(3) einen wissenschaftlichen Mitarbeiter einfügen");
                    System.out.println("(4) einen Professor anlegen");
                    System.out.println("(0) zurück");

                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            sql.insertPerson(insertPerson);
                            break;
                        case 2:
                            sql.insertStudent(insertPerson);
                            break;
                        case 3:
                            sql.insertMitarbeiter(insertPerson);
                            sql.insertErsteAnwesenheit(insertAnwesenheit);
                            break;
                        case 4:
                            sql.insertProf(insertPerson);
                            sql.insertErsteAnwesenheit(insertAnwesenheit);
                            break;
                        default:
                            break;
                    }
                    break;

                case 2:
                    System.out.println("(1) wähle aus Studenten");
                    System.out.println("(2) wähle aus Mitarbeiter");
                    System.out.println("(3) wähle aus Professor");
                    System.out.println("(4) wähle aus allen Angestellten");
                    System.out.println("(5) wähle aus allen Personen in der DB");

                    System.out.println("(0) zurück");

                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("MatrikelNr , Name");
                            sql.printPersonen("select student.matnr, student.name from student");
                            System.out.println("Wählen Sie einen Studenten per MatrikelNr: ");
                            int matnr = Integer.parseInt(scanner.nextLine());
                            sql.getPersonById("select * from student where matnr = ", matnr);
                            break;

                        case 2:
                            System.out.println("PersonalNr , Name");
                            sql.printPersonen("select mitarbeiter.personalnr, mitarbeiter.name from mitarbeiter");
                            System.out.println("Wählen Sie einen Mitarbeiter per PersonalNr: ");
                            int pnr = Integer.parseInt(scanner.nextLine());
                            sql.getPersonById("select * from mitarbeiter where personalnr = ", pnr);
                            break;
                        case 3:
                            System.out.println("PersonalNr , Name");
                            sql.printPersonen("select professor.personalnr, professor.name from professor");
                            System.out.println("Wählen Sie einen Professor per PersonalNr: ");
                            int pnr2 = Integer.parseInt(scanner.nextLine());
                            sql.getPersonById("select * from professor where personalnr = ", pnr2);
                            break;
                        case 4:
                            System.out.println("PersonalNr , Name");
                            sql.printPersonen("select angestellter.personalnr, angestellter.name from angestellter");
                            System.out.println("Wählen Sie einen Angestellten per PersonalNr: ");
                            int pnr3 = Integer.parseInt(scanner.nextLine());
                            sql.getPersonById("select * from angestellter where personalnr = ", pnr3);
                            break;
                        case 5:
                            System.out.println("pnr , Name");
                            sql.printPersonen("select person.pnr, person.name from person");
                            System.out.println("Wählen Sie eine Person per pnr: ");
                            int pnr4 = Integer.parseInt(scanner.nextLine());
                            sql.getPersonById("select * from student where matnr = ", pnr4);
                            sql.getPersonById("select * from mitarbeiter where personalnr = ", pnr4);
                            sql.getPersonById("select * from professor where personalnr = ", pnr4);
                            break;
                    }
                    break;

                case 0:
                    running = false;
                    c.closeConnection();
                    System.out.println("Auf Wiedersehen!");
                    break;

                default:
                    System.out.println("!!! keine gültige Eingabe !!!");
                    break;
            }
        }
    }
}

