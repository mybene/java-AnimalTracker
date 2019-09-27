import org.h2.engine.Database;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

import java.util.List;


public class EndangeredTest {
    Endangered testEndangered;
    @Rule
    DatabaseRule database= new DatabaseRule();

    @Test
    public void endangered_instantiatedCorrectly_true(){
        Endangered testEndangered= new Endangered("tiger","ill","young");
        assertEquals(true,testEndangered instanceof Endangered);
    }

    @Test
    public void healthState_checkHealth_String(){
        Endangered testEndangered= new Endangered("tiger","ill","young");
        assertEquals("ill",testEndangered.getHealth());

    }
//    @Test
//    public void name_nameGivencorrectly_String(){
//        Endangered testEndangered= new Endangered("tiger","ill","young");
//        assertEquals("tiger",testEndangered.getName());
//    }
    @Test
    public void age_guessAgeCorrectly_int(){
        Endangered testEndangered= new Endangered("tiger","ill","young");
        assertEquals("young",testEndangered.getAge());
    }
    @Test
    public void save_assignIdToEndangered(){
       Endangered testEndangered=new Endangered("tiger","ill","young");
        testEndangered.save();
        Endangered testEndangered1= Endangered.all().get(0);
       assertEquals(testEndangered.getId(),testEndangered1.getId());
    }
    @Test
    public void findName_endangeredAnimalHaveTheSameName_Endangered2(){
        testEndangered.save();
        Endangered testEndangered1=new Endangered("tiger","ill","young");
        testEndangered1.save();
        assertEquals(testEndangered.findByName(testEndangered1.getName()),testEndangered1);
    }

    @Test
    public void findById_endangeredsWithSameid_Endangered2(){
        testEndangered.save();
        Endangered testEndangered1=new Endangered("tiger","ill","young");
        testEndangered1.save();
        assertEquals(testEndangered.findById(testEndangered1.getId),testEndangered1);
    }

    @Test
    public void all_returnAllInstancesOfAnimal_true(){
        Endangered  testEndangered1= new Endangered("tiger","ill","young");
        testEndangered1.save();
        Endangered testEndangered2= new Endangered("gazel","okay","adult");
        testEndangered2.save();
        assertEquals(true,Endangered.all().get(0).equals(testEndangered1));
        assertEquals(true,Endangered.all().get(1).equals(testEndangered2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void find_throwsExceptionIfAnimalNotFound(){
        Endangered.findById(1);
    }

    @Test
    public void delete_deleteEntryInDatabase_0(){
        Endangered  testEndangered= new Endangered("tiger","ill","young");
        testEndangered.save();
        testEndangered.delete();
        assertEquals(0,Endangered.all().size());
    }

    @Test
    public void delete_deleteSightingGroup() {
        Endangered  testEndangered= new Endangered("tiger","ill","young");
        testEndangered.save();
        Sightings sightings = new Sightings("NE Quadrant", "Beline");
        sightings.addAnimal(testEndangered);
        sightings.save();
        testEndangered.delete();
        assertEquals(0, sightings.getEndangered().size());

    }

    @Test
    public void getSightings_filterAllSightingsInEndangered_int() {
        testEndangered.save();
        Sightings sightings = new Sightings("NE Quadrant", "Beline");
        sightings.save();
        sightings.addAnimal(testEndangered);
        List savedSightings = testEndangered.getSightings();
        assertEquals(1, savedSightings.size());
        assertTrue(savedSightings.contains(sightings));
    }
}
