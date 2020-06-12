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

    public void printSelect(String query) {
        select(query);

        try {
            ResultSetMetaData resultSetMetaData = results.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        System.out.print("   |     ");
                    String columnValue = results.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getPersonById(String query, int pnr) {
        select(query+pnr);
        try {
            ResultSetMetaData resultSetMetaData = results.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        System.out.print("  ,   ");
                    String columnValue = results.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
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
            student.setSemester(Integer.parseInt(scanner.nextLine()));

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
            mitarbeiter.setRaum(Integer.parseInt(scanner.nextLine()));

            this.preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, mitarbeiter.getName());
            preparedStatement.setString(2, mitarbeiter.getGebDatum());
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setInt(4, mitarbeiter.getRaum());
            preparedStatement.setNull(5, Types.VARCHAR);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            insertErsteAnwesenheit(query);

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
            professor.setRaum(Integer.parseInt(scanner.nextLine()));
            System.out.println("Bitte Rang eingeben (W2 oder W3): ");
            professor.setRang(scanner.nextLine());

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

    public void insertErsteAnwesenheit(String query) {
        int pnr;
        int vorlnr;

        try {
            select("select max(pnr) from person");
            if (results.next()) {
                pnr = results.getInt(1);
                System.out.println("Bitte gebe dem Prof noch eine Anwesenheit(VorlesungsNr)[1-3], diese ist " +
                        "Pflicht: ");

                while (!scanner.hasNextInt()) scanner.next();
                vorlnr = scanner.nextInt();

                this.preparedStatement = this.conn.prepareStatement(query);
                preparedStatement.setInt(1, pnr);
                preparedStatement.setInt(2, vorlnr);
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } else {
                System.out.println("Es Gab ein Problem mit der PNR, bitte kontaktieren Sie den Support");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getVorlesungByTitel(String search){
        search = search +"%";
        try {
            preparedStatement = this.conn.prepareStatement("select * from vorlesung where titel like ?");
            preparedStatement.setString(1, search);
            this.results = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = results.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        System.out.print("  ,   ");
                    String columnValue = results.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getFakByName(String search){
        search = search +"%";
        try {
            preparedStatement = this.conn.prepareStatement("select * from fakultät where name like ?");
            preparedStatement.setString(1, search);
            this.results = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = results.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        System.out.print("  ,   ");
                    String columnValue = results.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAvgNoten(){
        try {
            select("select * from get_avg_note()");

            ResultSetMetaData resultSetMetaData = results.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        System.out.print("  ,   ");
                    String columnValue = results.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void intAll(String query){

        select(query);

        try {
            ResultSetMetaData resultSetMetaData = results.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (results.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1)
                        System.out.print("  ,   ");
                    String columnValue = results.getString(i);
                    System.out.print(resultSetMetaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void int1(){
        intAll("select student.gebdatum, get_avg_note.avg_Note from get_avg_note() " +
                "inner join student on " +
                "student.matnr = get_avg_note.matnr;");
    }

    public void int2(){
        intAll("select professor.rang, count(*) as AnzahlVorlesungen from anwesenheit " +
                "inner join professor on " +
                "professor.personalnr = anwesenheit.pnr " +
                "group by professor.rang;");
    }

    public void int3(){
        intAll("select fakultät.name, count(*) as AnzahlStudenten from studium " +
                "inner join fakultät " +
                "on fakultät.faknr = studium.faknr " +
                "group by fakultät.\"name\";");
    }
    public void int4(){
        intAll("select vorlesung.titel, count(*) as teilnehmer from anwesenheit " +
                "inner join vorlesung on " +
                "vorlesung.vorlnr = anwesenheit.vorlnr " +
                "where anwesenheit.pnr in (select matnr from student) " +
                "group by vorlesung.titel;");
    }
    public void int5(){
        intAll("select fakultät.name, avg(person.semester) as avgAnzahlSemester from anwesenheit " +
                "inner join studium on " +
                "studium.pnr = anwesenheit.pnr " +
                "inner join fakultät on " +
                "fakultät.faknr = studium.faknr " +
                "inner join person on " +
                "person.pnr = studium.pnr " +
                "where anwesenheit.pnr in (select matnr from student) " +
                "group by " +
                "fakultät.name " +
                "order by " +
                "fakultät.name");
    }

    public void insertStudium(String query){
        int pnr;
        int fak;
        String sgang;

        try {
            select("select max(pnr) from person");
            if (results.next()) {
                pnr = results.getInt(1);
                System.out.println("[PFLICHT] Bitte dem Studierenden einen Studiengang und Fakultät zuweisen!");
                printSelect("select * from fakultät");

                while (!scanner.hasNextInt()) scanner.next();
                fak = Integer.parseInt(scanner.nextLine());
                System.out.println("Studiengang frei wählbar: ");
                sgang = scanner.nextLine();

                //sgang, pnr, fak
                this.preparedStatement = this.conn.prepareStatement(query);
                preparedStatement.setString(1, sgang);
                preparedStatement.setInt(2, pnr);
                preparedStatement.setInt(3, fak);
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } else {
                System.out.println("Es Gab ein Problem mit der PNR, bitte kontaktieren Sie den Support");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}