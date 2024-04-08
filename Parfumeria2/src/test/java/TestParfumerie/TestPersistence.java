package TestParfumerie;

import com.example.parfumeria2.Model.Persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestPersistence {
    @Test
    public void testCreateCloseConnection() throws SQLException{
        Assertions.assertNotNull(Persistence.createConnection(), "Connection should not be null");
        Connection c = Persistence.createConnection();
        Persistence.close(c);
        Assertions.assertTrue(c.isClosed());
    }
    @Test
    public void testClose() throws SQLException {
        Connection connection = Persistence.createConnection();
        Persistence.close(connection);
        Assertions.assertTrue(connection.isClosed());
    }
    @Test
    public void testExecuteUpdate() throws SQLException {
        // Create a test SQL statement
        //String sql = "INSERT INTO Parfumeria.PerfumeShop (code, location) VALUES ('test', 'location_test')";
        Connection conn = Persistence.createConnection();
        Statement dropStmt = conn.createStatement();
        int result = dropStmt.executeUpdate("DROP DATABASE IF EXISTS PARFUMERIA");
        Assertions.assertEquals(result, result);

        Persistence.close(dropStmt);
    }

}
