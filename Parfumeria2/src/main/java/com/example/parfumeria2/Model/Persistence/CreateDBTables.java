package com.example.parfumeria2.Model.Persistence;


import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.PerfumeShop;
import com.example.parfumeria2.Model.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.parfumeria2.Model.Persistence.Persistence.close;

public class CreateDBTables {
    public CreateDBTables() {
    }

    private static final Logger LOGGER = Logger.getLogger(CreateDBTables.class.getName());

    public static void createDatabase() {
        Connection connection = Persistence.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "DROP DATABASE IF EXISTS PARFUMERIA";
            statement.executeUpdate(sql);
            LOGGER.info("Database reinitialized successfully!");

            sql = "CREATE DATABASE IF NOT EXISTS PARFUMERIA";
            statement.executeUpdate(sql);
            LOGGER.info("Database created successfully!");

            String sql2 = "Use PARFUMERIA;";
            statement.executeUpdate(sql2);
            LOGGER.info("Database used successfully!");
            close(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occurred while trying to create the database");
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }
    public static void createPerfumeTable() {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Parfumeria.Perfume (\n" +
                "code varchar(10) NOT NULL PRIMARY KEY," +
                "name varchar(20) NOT NULL,\n" +
                "manufacturer varchar(20) NOT NULL,\n" +
                "price FLOAT NOT NULL,\n" +
                "discount FLOAT NOT NULL,\n" +
                "description varchar(50) NOT NULL,\n" +
                "gender ENUM('M', 'F') NOT NULL\n" +
                ");";
        try (
                Connection conn = Persistence.getConnection();
                Statement statement = conn.createStatement()){
            statement.execute(createTableSQL);
            LOGGER.info("Perfume table created successfully.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating Perfume table: " + e.getMessage());
        }
    }

    public static void createPerfumeShopTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Parfumeria.PerfumeShop (\n" +
                "code CHAR(10) NOT NULL PRIMARY KEY,\n" +
                "location CHAR(20) NOT NULL\n" +
                ");";
        try (
                Connection conn = Persistence.getConnection();
                Statement statement = conn.createStatement()) {
            statement.execute(createTableSQL);
            LOGGER.info("PerfumeShop table created successfully.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating PerfumeShop table: " + e.getMessage());
        }
    }
    public static void createPerfumeInShopTable() {
        try (
            Connection conn = Persistence.getConnection();
            Statement statement = conn.createStatement()){
            String query = "CREATE TABLE IF NOT EXISTS Parfumeria.PerfumeInShop("
                    + "code CHAR(10) NOT NULL,"
                    + "codePerfume CHAR(10),"
                    + "codeShop CHAR(20),"
                    + "stock INT,"
                    + "PRIMARY KEY (code),"
                    + "FOREIGN KEY (codePerfume) REFERENCES Parfumeria.Perfume(code),"
                    + "FOREIGN KEY (codeShop) REFERENCES Parfumeria.PerfumeShop(code))";
            statement.execute(query);
            System.out.println("PerfumeInShop table created successfully.");
            close(statement);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to create PerfumeInShop table");
            e.printStackTrace();
        }
    }
    public static void createPersonTable() {
        try (Connection conn = Persistence.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Parfumeria.Person " +
                    "(id varchar(13) PRIMARY KEY NOT NULL, " +
                    " name varchar(10) NOT NULL, " +
                    " surname varchar(20) NOT NULL, " +
                    " email varchar(40) NOT NULL, " +
                    " password varchar(15) NOT NULL, " +
                    " job ENUM('Employee', 'Admin','Manager') NOT NULL);";
            stmt.executeUpdate(sql);
            LOGGER.info("Person table created successfully!");
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
    public void populatePerfumeTable() {
        String[] codes = {"p01", "p02", "p03", "p04","p05","p06","p07","p08","p09","p10"};
        String[] names = {"Black Opium", "Stronger with you", "Armani Code", "Joy","Scandal","Sauvage","Miss Dior","L>homme","Eros","Daisy"};
        String[] manufacturer = {"YSL", "Armani", "Armani", "Dior", "Jean Paul Gaultier", "Dior", "Dior", "Prada", "Versace","Marc Jacobs"};
        String[] description = {"YSL - good", "Armani- good", "Armani- good", "Dior - good ", "JPGaultier - nice", "Dior - good", "Dior- amazing", "Prada- perfect", "Versace - ok","MJ- nice"};
        Float[] price = {500.0f,320.0f,420.0f,420.0f,500.0f,600.0f,600.0f,6000f,320f,320f};
        Float[] discount = {0f, 15f, 20f, 5f, 5f, 10f, 20f, 0f, 15f, 10f};
        Perfume.Gender[] gender = {Perfume.Gender.F, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.F, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.M, Perfume.Gender.F};
        PerfumePersistence perfumePersistence = new PerfumePersistence();
            for (int i = 0; i < names.length; i++) {
                Perfume p = new Perfume(codes[i], names[i],manufacturer[i], price[i], discount[i], description[i], gender[i]);
                perfumePersistence.createPerfume(p);
            }
    }
    public void populatePerfumeShopTable() {
        String[] codes = {"s01", "s02", "s03"};
        String[] locations = {"Brasov", "Iasi", "Bucuresti"};
        PerfumeShopPersistence perfumeShopPersistence = new PerfumeShopPersistence();
        for(int i=0;i<codes.length;i++)
        {
            PerfumeShop ps = new PerfumeShop(codes[i], locations[i]);
            perfumeShopPersistence.createPerfumeShop(ps);
        }
    }
    public int populatePersonTable() {
        PersonPersistence personPersistence = new PersonPersistence();
        String[] id = {"u01", "u02", "u03", "u04"};
        String[] names = {"John", "Jane", "Bob", "Alice"};
        String[] surnames = {"Smith", "Joe", "Dylan", "Gold"};
        String[] emails = {"johnsmith@gmail.com", "janejoe@hotmail.uk.co", "bobdylan@gmail.com", "alicegold@gmail.com"};
        String[] passwords = {"12345", "24680", "abcde", "fghij"};
        Person.Job[] jobs = {Person.Job.Employee, Person.Job.Employee, Person.Job.Manager, Person.Job.Admin};
        for (int i = 0; i < names.length; i++) {
            Person u = new Person(id[i], names[i], surnames[i],emails[i],passwords[i],jobs[i]);
            personPersistence.createPerson(u);
        }
        return 4; //persoane adaugate

    }
    public void populatePerfumeINShopTable() {
        String[] codes = {"ps01", "ps02", "ps03","ps04", "ps05", "ps06","ps07", "ps08", "ps09","ps10", "ps11", "ps12"};
        String[] codesperfumes = {"p01", "p02", "p03", "p04","p05","p06","p07","p08","p09","p10"};
        String[] codesshops = {"s01", "s02", "s03"};
        Integer[] stocks = {150,200,250,300,350,400,500};
        String sql = null;
        for(int i=0; i<codes.length;i++) {
            int rand1 = (int) ((Math.random() * (codesperfumes.length)));
            int rand2 = (int) (Math.random() * (codesshops.length));
            int rand3 = (int) (Math.random() * (stocks.length));
            sql = String.format("INSERT INTO Parfumeria.PerfumeInShop (code, codePerfume, codeShop, stock) VALUES ('%s','%s', '%s', '%d')",
                    codes[i], codesperfumes[rand1], codesshops[rand2],stocks[rand3]);
            Persistence.executeUpdate(sql);
        }
    }
}

