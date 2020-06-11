package sql;

import objects.Mitarbeiter;
import objects.Person;
import objects.Professor;
import objects.Student;

import java.sql.*;
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
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setNull(4, Types.INTEGER);
            preparedStatement.setNull(5, Types.VARCHAR);
            preparedStatement.executeUpdate();
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
            preparedStatement.setNull(4, Types.INTEGER);
            preparedStatement.setNull(5, Types.VARCHAR);
            preparedStatement.executeUpdate();
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
            mitarbeiter.setRaum(scanner.nextInt());

            this.preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, mitarbeiter.getName());
            preparedStatement.setString(2, mitarbeiter.getGebDatum());
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setInt(4, mitarbeiter.getRaum());
            preparedStatement.setNull(5, Types.VARCHAR);
            preparedStatement.executeUpdate();
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
            professor.setRaum(scanner.nextInt());
            System.out.println("Bitte Rang eingeben (W2 oder W3): ");
            professor.setRang(scanner.nextLine());
            System.out.println(professor.getRang());

            this.preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getGebDatum());
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setInt(4, professor.getRaum());
            preparedStatement.setString(5, "W2");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}