package sql;

import objects.Mitarbeiter;
import objects.Person;
import objects.Professor;
import objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Sql {

    private Connection conn;
    private ResultSet results;
    private PreparedStatement preparedStatement;

    Scanner scanner = new Scanner(System.in);

    public Sql(Connection conn) {
        this.conn = conn;
    }

    public void update(String query) {
        try {
            this.conn.prepareStatement(query).executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void select(String query) {
        try {
            this.results = this.conn.prepareStatement(query).executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPerson(String query) {
        try {

            Person person = new Person();
            System.out.println("Bitte den Namen eingeben: ");
            person.setName(scanner.nextLine());
            System.out.println("Bitte das Geburtsdatum eingeben (Format YYYY-MM-DD): ");
            person.setGebDatum(scanner.nextLine());

            this.preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getGebDatum());
            preparedStatement.setString(3, null);
            preparedStatement.setObject(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertStudent(String query) {
        try {

            Student student = new Student();
            System.out.println("Bitte den Namen eingeben: ");
            student.setName(scanner.nextLine());
            System.out.println("Bitte das Geburtsdatum eingeben (Format YYYY-MM-DD): ");
            student.setGebDatum(scanner.nextLine());
            System.out.println("Bitte Semester eingeben: ");
            student.setSemester(scanner.nextInt());

            this.preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getGebDatum());
            preparedStatement.setInt(3, student.getSemester());
            preparedStatement.setObject(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertMitarbeiter(String query) {
        try {

            Mitarbeiter mitarbeiter = new Mitarbeiter();
            System.out.println("Bitte den Namen eingeben: ");
            mitarbeiter.setName(scanner.nextLine());
            System.out.println("Bitte das Geburtsdatum eingeben (Format YYYY-MM-DD): ");
            mitarbeiter.setGebDatum(scanner.nextLine());
            System.out.println("Bitte Raum eingeben: ");
            mitarbeiter.setRaum(scanner.nextLine());

            this.preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, mitarbeiter.getName());
            preparedStatement.setString(2, mitarbeiter.getGebDatum());
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, mitarbeiter.getRaum());
            preparedStatement.setString(5, null);
            preparedStatement.executeQuery(query);
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertProf(String query) {
        try {

            Professor professor = new Professor();
            System.out.println("Bitte den Namen eingeben: ");
            professor.setName(scanner.nextLine());
            System.out.println("Bitte das Geburtsdatum eingeben (Format YYYY-MM-DD): ");
            professor.setGebDatum(scanner.nextLine());
            System.out.println("Bitte Raum eingeben: ");
            professor.setRaum(scanner.nextLine());
            System.out.println("Bitte Rang eingeben (W2 oder W3): ");

            this.preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getGebDatum());
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, professor.getRaum());
            preparedStatement.setString(5, professor.getRang());
            preparedStatement.executeQuery(query);
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}