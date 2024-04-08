package TestParfumerie;


import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.PerfumeShop;
import com.example.parfumeria2.Model.Persistence.CreateDBTables;
import com.example.parfumeria2.Model.Persistence.Persistence;
import com.example.parfumeria2.Model.Person;
import com.example.parfumeria2.Model.Reflection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestCreateDBTables {


    @Test
    public void testCreateDatabase() throws SQLException {
        Connection conn = Persistence.createConnection();

        CreateDBTables.createDatabase();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW TABLES");
        Assertions.assertTrue(rs.next());
        Persistence.close(rs);
        Persistence.close(stmt);
        Persistence.close(conn);
    }

    @Test
    public void testCreatePerfumeTable() throws SQLException {
        Connection c = Persistence.createConnection();
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        DatabaseMetaData meta = (DatabaseMetaData) c.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, "Parfumeria.Perfume", null);
        Assertions.assertTrue(resultSet.next(), "Table exists");

        resultSet = meta.getTables(null, null, "Parfumeria.Perfumes", null);
        Assertions.assertFalse(resultSet.next(), "Table exists");

        String[] expectedColumnNames = { "code ", "name ", "manufacturer ", "price ", "discount ", "description ","gender " };
        Perfume p = new Perfume();
        String[] column = Reflection.retrieveColumn(p);
        for(int i = 0; i<column.length;i++)
            Assertions.assertEquals(column[i], expectedColumnNames[i]);

        Persistence.close(c);
    }
    @Test
    public void testCreatePerfumeShopTable() throws SQLException {
        Connection c = Persistence.createConnection();
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeShopTable();
        DatabaseMetaData meta = (DatabaseMetaData) c.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, "Parfumeria.PerfumeShop", null);
        Assertions.assertTrue(resultSet.next(), "Table exists");

        resultSet = meta.getTables(null, null, "Parfumeria.PerfumeShops", null);
        Assertions.assertFalse(resultSet.next(), "Table exists");

        String[] expectedColumnNames = { "code ", "location " };
        PerfumeShop ps = new PerfumeShop("","");
        String[] column = Reflection.retrieveColumn(ps);
        for(int i = 0; i<column.length;i++)
            Assertions.assertEquals(column[i], expectedColumnNames[i]);

        Persistence.close(c);
    }
    @Test
    public void testCreatePersonTable() throws SQLException {
        Connection c = Persistence.createConnection();
        CreateDBTables.createDatabase();
        CreateDBTables.createPersonTable();
        DatabaseMetaData meta = (DatabaseMetaData) c.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, "Parfumeria.Person", null);
        Assertions.assertTrue(resultSet.next(), "Table exists");

        resultSet = meta.getTables(null, null, "Parfumeria.Persons", null);
        Assertions.assertFalse(resultSet.next(), "Table exists");

        String[] expectedColumnNames = { "id ", "name ", "surname ", "email ", "password ", "job " };
        Person p = new Person();
        String[] column = Reflection.retrieveColumn(p);
        for(int i = 0; i<column.length;i++)
            Assertions.assertEquals(column[i], expectedColumnNames[i]);

        Persistence.close(c);
    }

}
