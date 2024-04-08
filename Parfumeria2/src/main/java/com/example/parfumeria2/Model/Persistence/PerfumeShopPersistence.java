package com.example.parfumeria2.Model.Persistence;


import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.PerfumeShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerfumeShopPersistence {

    public boolean createPerfumeShop(PerfumeShop perfumeshop) {
        String sql = String.format("INSERT INTO Parfumeria.PerfumeShop (code, location) VALUES ('%s', '%s')",
                perfumeshop.getCode(), perfumeshop.getLocation());
        return Persistence.executeUpdate(sql) == 1;
    }
    public ArrayList<Perfume> filterAfterStock(String input) {
        ArrayList<Perfume> filteredPerfumes = new ArrayList<>();
        String sql = "SELECT * FROM Parfumeria.Perfume WHERE stock = ?";
        try (Connection conn = Persistence.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, input);
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
            //LOGGER.log(Level.WARNING, ex.getMessage(), ex);
        }

        return filteredPerfumes;
    }
}
