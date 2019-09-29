
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class SightingsTest {
    Sightings testSightings;

    @Rule
    DatabaseRule database = new DatabaseRule();



    @Test
    public void Sightings_instatiatedCorrectly_true() {
        Sightings testSightings = new Sightings("zone A", "David");
        assertTrue(String.valueOf(true), testSightings instanceof Sightings);
    }

    @Test
    public void location_animalLocalized_true() {
        Sightings testSightings = new Sightings("kangourou", "David");
        assertEquals("zone A", testSightings.getLocation());
    }

    @Test
    public void rangerName_instatiatedCorrectly_true() {
        Sightings testSightings = new Sightings("zone A", "David");
        assertEquals("David", testSightings.getRangeName());
    }

    @Test
    public void equals_returnTrueIfPropertiesAreSame_true() {
        Sightings testSightings1 = new Sightings("zone A", "David");
        assertEquals(testSightings, testSightings1);
    }

    @Test
    public void saveIntoDataBase_Sightings() {
        testSightings.save();
        Sightings testSightings1 = null;
        try (Connection con = (Connection) DB.sql2o.open()) {
            testSightings1 = ((org.sql2o.Connection) con).createQuery("SELECT *FROM sightings WHERE location='zone A'")
                    .executeAndFetchFirst(Sightings.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(testSightings1, testSightings);
    }

    @Test
    public void all_returnAllInstancesOfNormalAnimal_true() {
        testSightings.save();
        Sightings testSightings1 = new Sightings("NE Quadrant", "Beline");
        testSightings1.save();
        assertTrue(Sightings.all().get(0).equals(testSightings));
        assertTrue(Sightings.all().get(1).equals(testSightings1));
    }

    @Test
    public void save_assignedIdToSightings() {
        testSightings.save();
        Sightings sighting1 = Sightings.all().get(0);
        assertEquals(testSightings.getId(), sighting1.getId());
    }

    @Test
    public void find_filterSightinsWithSameId_sighting1() {
        testSightings.save();
        Sightings sighting1 = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        assertEquals(Sightings.findById(sighting1.getId()), sighting1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void find_throwsExceptionIfSightinNotFound() {
        Sightings.findById(1);
    }

    @Test
    public void save_updateDateInDatabase_Sighting() {
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        Timestamp savedDate = Sightings.findById(testSightings.getId()).getWhichtime();
        Timestamp rightNow = new Timestamp(new().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedDate));
    }

    @Test
    public void formattedDate_getFormattedDate_Sighting() {
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        Sightings savedSighting = Sightings.findById(testSightings.getId());
        Timestamp rigthNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rigthNow), savedSighting.getFormattedDate());
    }

    @Test
    public void delete_deletesEntryInDatabase_0() {
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        testSightings.delete();
        assertEquals(0, Sightings.all().size());

    }

    @Test
    public void delete_deletesSightingGroup() {
        testSightings.save();
        Animal testNormalAnimal = new Animal("tiger", "carnivore");
        testNormalAnimal.save();
        testSightings.addAnimal(testNormalAnimal);
        testSightings.delete();
        assertEquals(0, testNormalAnimal.getSightings().size());
    }

    @Test
    public void update_updateSightingsGroup() {
        testSightings.save();
        Animal testAnimal = new Animal("cat", "carnivor");
        testAnimal.save();
        testSightings.addAnimal();
        Animal savedAnimal = (Animal) testSightings.addAnimal();
        assertTrue(testAnimal.equals(savedAnimal));
    }

    @Test
    public void filterNormalAnimalFromSightingdOnes() {
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        Animal testAnimal = new Animal("cat", "carnivor");
        testAnimal.save();
        testSightings.addAnimal(testAnimal);
        List savedAnimals = testSightings.addAnimal();
        assertTrue(savedAnimals.contains(testAnimal));
    }

    @Test
    public void filterEndengeredAnimalsFromSightingOnes(){
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        Endangered testEndangered=new Endangered("tiger","ill","young");
        testEndangered.save();
        testSightings.addAnimal(testEndangered);
        Endangered savedEndangered= (Endangered) testSightings.getEndangered().get(0);
        assertTrue(testEndangered.equals(savedEndangered));
    }

    @Test
    public void sortSightingsByDate(){
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        Sightings testSightings1= new Sightings("Zone A","David");
        testSightings1.save();
        assertEquals(testSightings1,Sightings.allByDate().get(0));
    }

    @Test
    public void recent_filterTheMostRecent_Sightings(){
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        Sightings testSightings1= new Sightings("Zone A","David");
        testSightings1.save();
        Sightings testSightings2= new Sightings("Near River","Dany");
        testSightings1.save();
        Sightings testSightings3= new Sightings("Zone A","Lyse");
        testSightings1.save();
        Sightings testSightings4= new Sightings("NE Quadrant","David");
        testSightings1.save();
        assertEquals(testSightings4,Sightings.mostRecent().get(0));
        assertFalse(Sightings.mostRecent().contains(testSightings));
    }
}

