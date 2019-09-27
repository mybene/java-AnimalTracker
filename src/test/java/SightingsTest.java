
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
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

//    @Before
//    public void initialized(){
//        Sightings testSightings=new Sightings("zone A","David");
//    }

    @Test
    public void Sightings_instatiatedCorrectly_true() {
        Sightings testSightings = new Sightings("zone A", "David");
        assertEquals(true, testSightings instanceof Sightings);
    }

//    @Test
//    public void identity_animalIdentified_true(){
//        Sightings testSightings=new Sightings("kangourou","zone A","David");
//        assertEquals("kangourou",testSightings.getSpecies());
//    }

    @Test
    public void location_animalLocalized_true() {
        Sightings testSightings = new Sightings("kangourou", "David");
        assertEquals("zone A", testSightings.getLocation());
    }

    @Test
    public void rangerName_instatiatedCorrectly_true() {
        Sightings testSightings = new Sightings("zone A", "David");
        assertEquals("David", testSightings.getRangename());
    }

    @Test
    public void equals_returnTrueIfPropertiesAreSame_true() {
        Sightings testSightings1 = new Sightings("zone A", "David");
        assertTrue(testSightings.equals(testSightings1));
    }

    @Test
    public void saveIntoDataBase_Sightings() {
        testSightings.save();
        Sightings testSightings1 = null;
        try (Connection con = DB.sql2o.open()) {
            testSightings1 = con.createQuery("SELECT *FROM sightings WHERE location='zone A'")
                    .executeAndFetchFirst(Sightings.class);
        }
        assertTrue(testSightings1.equals(testSightings));
    }

    @Test
    public void all_returnAllInstancesOfNormalAnimal_true() {
        testSightings.save();
        Sightings testSightings1 = new Sightings("NE Quadrant", "Beline");
        testSightings1.save();
        assertEquals(true, Sightings.all().get(0).equals(testSightings));
        assertEquals(true, Sightings.all().get(1).equals(testSightings1));
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
        Timestamp rigthNow = new Timestamp(new Date().getTime());
//        assertEquals(rigthNow.getDate(),savedDate.getDate());
        assertEquals(DateFormat.getDateTimeInstance().format(rigthNow), savedDate.getFormattedDate());
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
        NormalAnimal testNormalAnimal = new NormalAnimal("tiger", "carnivore");
        testNormalAnimal.save();
        testSightings.addAnimal(testNormalAnimal);
        testSightings.delete();
        assertEquals(0, testNormalAnimal.getSighting().size());
    }

    @Test
    public void update_updateSightingsGroup() {
        testSightings.save();
        NormalAnimal testNormalAnimal = new NormalAnimal("cat", "carnivor");
        testNormalAnimal.save();
        testSightings.addAnimal(testNormalAnimal);
        NormalAnimal savedNormalAnimal = (NormalAnimal) testSightings.getNormalAnimal().get(0);
        assertTrue(testNormalAnimal.equals(savedNormalAnimal));
    }

    @Test
    public void filterNormalAnimalFromSightingdOnes() {
        Sightings testSightings = new Sightings("NE Quadrant", "Beline");
        testSightings.save();
        NormalAnimal testNormalAnimal = new NormalAnimal("cat", "carnivor");
        testNormalAnimal.save();
        testSightings.addAnimal(testNormalAnimal);
        List savedAnimals = testSightings.getNoramalAnimal();
        assertTrue(savedAnimals.contains(testNormalAnimal));
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

