package com.example.parfumeria2.Model.Persistence;

import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.PerfumeInStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerfumePersistence {
    private static final Logger LOGGER = Logger.getLogger(PerfumePersistence.class.getName());

    public PerfumePersistence() {
    }

    public void populatePerfumeTable() {
        String[] codes = {"p01", "p02", "p03", "p04", "p05", "p06", "p07", "p08", "p09", "p10"};
        String[] names = {"Black Opium", "Stronger with you", "Armani Code", "Joy", "Scandal", "Sauvage", "Miss Dior", "L>homme", "Eros", "Daisy"};
        String[] manufacturer = {"YSL", "Armani", "Armani", "Dior", "Jean Paul Gaultier", "Dior", "Dior", "Prada", "Versace", "Marc Jacobs"};
        String[] description = {"YSL - good", "Armani- good", "Armani- good", "Dior - good ", "JPGaultier - nice", "Dior - good", "Dior- amazing", "Prada- perfect", "Versace - ok", "MJ- nice"};
        Float[] price = {500.0f, 350.0f, 480.0f, 420.0f, 550.0f, 570.0f, 600.0f, 580f, 320f, 300f};
        Float[] discount = {0f, 15f, 20f, 5f, 5f, 10f, 20f, 0f, 15f, 10f};
        Perfume.Gender[] gender = {Perfume.Gender.F, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.F, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.M, Perfume.Gender.F};
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        for (int i = 0; i < names.length; i++) {
            Perfume p = new Perfume(codes[i], names[i], manufacturer[i], price[i], discount[i], description[i], gender[i]);
            perfumePersistence.createPerfume(p);
        }
    }

    public boolean createPerfume(Perfume perfume) {
        String sql = String.format("INSERT INTO Parfumeria.Perfume (code, name, manufacturer, description, price, discount, gender) VALUES ('%s', '%s', '%s', '%s', '%f', '%f', '%s')",
                perfume.getCode(), perfume.getName(), perfume.getManufacturer(), perfume.getDescription(), perfume.getPrice(), perfume.getDiscount(), perfume.getGender().toString());

        return (Persistence.executeUpdate(sql) == 1);
    }

    public boolean updatePerfumeField(String code, String fieldName, String fieldValue) {
        String sql = "UPDATE Parfumeria.Perfume SET " + fieldName + "='" + fieldValue + "' WHERE code = '" + code + "';";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return false;
        }
    }

    public boolean deletePerfume(String code) {
        String sql = "DELETE FROM Parfumeria.Perfume WHERE code = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return false;
        }
    }

    public Perfume readPerfume(String code) {
        String sql = "SELECT * FROM Parfumeria.Perfume WHERE code = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Perfume perfume = new Perfume(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("manufacturer"),
                        rs.getFloat("price"),
                        rs.getFloat("discount"),
                        rs.getString("description"),
                        Perfume.Gender.valueOf(rs.getString("gender")));
                return perfume;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }

    public ArrayList<Perfume> getAllPerfumes() {
        ArrayList<Perfume> perfumes = new ArrayList<>();

        String sql = "SELECT * FROM Parfumeria.Perfume";

        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Perfume perfume = new Perfume(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("manufacturer"),
                        rs.getFloat("price"),
                        rs.getFloat("discount"),
                        rs.getString("description"),
                        Perfume.Gender.valueOf(rs.getString("gender")));
                perfumes.add(perfume);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }

        return perfumes;
    }

    public ArrayList<Perfume> filterPerfumesByAttribute(String attribute, String value) {
        ArrayList<Perfume> filteredPerfumes = new ArrayList<>();

        String sql = "SELECT * FROM Parfumeria.Perfume WHERE " + attribute + " = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String brand = rs.getString("manufacturer");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String description = rs.getString("description");
                Perfume.Gender gender = Perfume.Gender.valueOf(rs.getString("gender"));


                Perfume perfume = new Perfume(code, name, brand, price, discount, description, gender);
                filteredPerfumes.add(perfume);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }

        return filteredPerfumes;
    }

    public ArrayList<Perfume> getAllPerfumesOrderedByName() {
        ArrayList<Perfume> perfumes = new ArrayList<>();
        String sql = "SELECT * FROM Parfumeria.Perfume ORDER BY name";

        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String manufacturer = resultSet.getString("manufacturer");
                String description = resultSet.getString("description");
                float price = resultSet.getFloat("price");
                float discount = resultSet.getFloat("discount");
                Perfume.Gender gender = Perfume.Gender.valueOf(resultSet.getString("gender"));

                Perfume perfume = new Perfume(code, name, manufacturer, price, discount, description, gender);
                perfumes.add(perfume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perfumes;
    }

    public ArrayList<Perfume> getAllPerfumesOrderedByPrice() {
        ArrayList<Perfume> perfumes = new ArrayList<>();
        String sql = "SELECT * FROM Parfumeria.Perfume ORDER BY price";

        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String manufacturer = resultSet.getString("manufacturer");
                String description = resultSet.getString("description");
                float price = resultSet.getFloat("price");
                float discount = resultSet.getFloat("discount");
                Perfume.Gender gender = Perfume.Gender.valueOf(resultSet.getString("gender"));

                Perfume perfume = new Perfume(code, name, manufacturer, price, discount, description, gender);
                perfumes.add(perfume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perfumes;
    }

    public Perfume searchPerfumeByName(String namee) {
        String sql = "SELECT * FROM Parfumeria.Perfume WHERE name = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + namee + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String brand = rs.getString("manufacturer");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String description = rs.getString("description");
                Perfume.Gender gender = Perfume.Gender.valueOf(rs.getString("gender"));
                Perfume perfume = new Perfume(code, name, brand, price, discount, description, gender);
                return perfume;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }

    public Perfume searchPerfumeByID(String id) {
        String sql = "SELECT * FROM Parfumeria.Perfume WHERE code = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + id + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String brand = rs.getString("manufacturer");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String description = rs.getString("description");
                Perfume.Gender gender = Perfume.Gender.valueOf(rs.getString("gender"));
                Perfume perfume = new Perfume(code, name, brand, price, discount, description, gender);
                return perfume;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            return null;
        }
    }

    public ArrayList<Perfume> calcTotalStock(String perfumeStock) {
        ArrayList<Perfume> perfumes = new ArrayList<>();
        String sql = "SELECT *\n" +
                "                 FROM Parfumeria.Perfume p \n" +
                "                 JOIN Parfumeria.PerfumeInShop pis ON p.code = pis.codePerfume \n" +
                "                 GROUP BY p.code\n" +
                "                 HAVING SUM(pis.stock) = '" + perfumeStock + "';";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            //stmt.setString(1, perfumeStock);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String brand = rs.getString("manufacturer");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String description = rs.getString("description");
                Perfume.Gender gender = Perfume.Gender.valueOf(rs.getString("gender"));


                Perfume perfume = new Perfume(code, name, brand, price, discount, description, gender);
                perfumes.add(perfume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perfumes;
    }

    public ArrayList<PerfumeInStock> filterAfterShop(String input) {
        ArrayList<PerfumeInStock> perfumesInStocks = new ArrayList<>();
        String sql = String.format("SELECT parfumeria.Perfume.*, parfumeria.PerfumeInShop.stock\n" +
                "FROM parfumeria.Perfume\n" +
                "INNER JOIN parfumeria.PerfumeInShop ON parfumeria.Perfume.code = parfumeria.PerfumeInShop.codePerfume\n" +
                "INNER JOIN parfumeria.PerfumeShop ON parfumeria.PerfumeShop.Code = parfumeria.PerfumeInShop.codeShop\n" +
                "WHERE parfumeria.PerfumeShop.code = '%s';", input);
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                String brand = rs.getString("manufacturer");
                float price = rs.getFloat("price");
                float discount = rs.getFloat("discount");
                String description = rs.getString("description");
                Perfume.Gender gen = Perfume.Gender.valueOf(rs.getString("gender"));
                int stock = rs.getInt("stock");

                Perfume p = new Perfume(code, name, brand, price, discount, description, gen);
                PerfumeInStock ps = new PerfumeInStock(p, stock);
                perfumesInStocks.add(ps);
            }
            return perfumesInStocks;
        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }
        return null;
    }

}
