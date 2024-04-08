package TestParfumerie;


import com.example.parfumeria2.Model.Perfume;
import com.example.parfumeria2.Model.Persistence.CreateDBTables;
import com.example.parfumeria2.Model.Persistence.PerfumePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPerfumePersistence {

    public int populatePerfumeTable() {
        String[] codes = {"p01", "p02", "p03", "p04","p05","p06","p07","p08","p09","p10"};
        String[] names = {"Black Opium", "Stronger with you", "Armani Code", "Joy","Scandal","Sauvage","Miss Dior","L>homme","Eros","Daisy"};
        String[] manufacturer = {"YSL", "Armani", "Armani", "Dior", "Jean Paul Gaultier", "Dior", "Dior", "Prada", "Versace","Marc Jacobs"};
        String[] description = {"YSL - good", "Armani- good", "Armani- good", "Dior - good ", "JPGaultier - nice", "Dior - good", "Dior- amazing", "Prada- perfect", "Versace - ok","MJ- nice"};
        Float[] price = {500.0f,350.0f,480.0f,420.0f,550.0f,570.0f,600.0f,580f,320f,300f};
        Float[] discount = {0f, 15f, 20f, 5f, 5f, 10f, 20f, 0f, 15f, 10f};
        Perfume.Gender[] gender = {Perfume.Gender.F, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.F, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.F, Perfume.Gender.M, Perfume.Gender.M, Perfume.Gender.F};
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        for (int i = 0; i < names.length; i++) {
            Perfume p = new Perfume(codes[i], names[i],manufacturer[i], price[i], discount[i], description[i], gender[i]);
            perfumePersistence.createPerfume(p);
        }
        return 10;
    }

    @Test
    public void testCreatePerfume() {
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        perfumePersistence.deletePerfume("p0test");
        Perfume p = new Perfume("p0test","Test parfume", "Test producator", 100f,10f,"Test descriere", Perfume.Gender.F);
        //Act
        boolean result1 = perfumePersistence.createPerfume(p);
        //Assert
        assertTrue(result1);

    }
    @Test
    public void testReadPerfume() {
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        //Act
        Perfume p = new Perfume("p0test","Test parfume", "Test producator", 100f,10f,"Test descriere", Perfume.Gender.F);
        perfumePersistence.createPerfume(p);
        Perfume pp = perfumePersistence.readPerfume("p0test");
        //Assert
        int f = 0;
        if(pp.getCode().equals(p.getCode()) && pp.getName().equals(p.getName()) && pp.getManufacturer().equals(p.getManufacturer())
                && pp.getPrice()==(p.getPrice()) && pp.getDiscount()==(p.getDiscount()) && pp.getDescription().equals(p.getDescription())
                && pp.getGender().equals(p.getGender()))
            f = 1;
        Assertions.assertEquals(f, 1);

        perfumePersistence.deletePerfume("p0test");

    }
    @Test
    public void testUpdatePerfume() {
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        //Act
        Perfume p = new Perfume("p0test", "name1", "brand", 100f,10f, "Description", Perfume.Gender.F);
        perfumePersistence.createPerfume(p);
        boolean result1 = perfumePersistence.updatePerfumeField("p0test","name","Test parfum2");
        //Assert
        assertTrue(result1);
    }
    @Test
    public void testSearchPerfume(){
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        Perfume p = new Perfume("p0test","Test parfum", "Test producator", 100f,10f,"Test descriere", Perfume.Gender.F);
        perfumePersistence.createPerfume(p);
        Perfume pp = perfumePersistence.searchPerfumeByName("Test parfum");
        if(pp!= null)
            Assertions.assertEquals(pp,p);
        else Assertions.assertNull(pp);

        perfumePersistence.deletePerfume("p0test");
    }
    @Test
    public void testDeletePerfume(){
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        Perfume p = new Perfume("p0test","Test parfum", "Test producator", 100f,10f,"Test descriere", Perfume.Gender.F);
        perfumePersistence.createPerfume(p);
        boolean result = perfumePersistence.deletePerfume("p0test");
        assertTrue(result);
    }
    @Test
    public void testgetAllPerfumes(){
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        int nr = populatePerfumeTable();
        ArrayList<Perfume> perfumes = perfumePersistence.getAllPerfumes();
        Assertions.assertEquals(perfumes.size(),nr);
    }
    @Test
    public void testFilterPerfumesByAttribute(){
        CreateDBTables.createDatabase();
        CreateDBTables.createPerfumeTable();
        PerfumePersistence perfumePersistence = new PerfumePersistence();
        ArrayList<Perfume> perfumes = perfumePersistence.filterPerfumesByAttribute("manufacturer","Dior");
        Assertions.assertNotNull(perfumes);

        perfumes = perfumePersistence.filterPerfumesByAttribute("price","300");
        Assertions.assertNotNull(perfumes);
    }
}
