import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class NormalAnimalTest {
  NormalAnimal testNormalAnimal;
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void NormalAnimal_instantiatedCorrectly_true(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivore");
        assertEquals(true,testNormalAnimal instanceof NormalAnimal);
    }

    @Test
    public  void animalName_animalHaveName_String(){
        NormalAnimal testNormalAnimal = new NormalAnimal("tiger","carnivore");
        assertEquals("tiger",testNormalAnimal.getName());
    }

    @Test
    public void animalSpecies_animalHaveId_String(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivore");
        assertEquals(true,testNormalAnimal.getSpecies());
    }

    @Test
    public void equals_verifiedIfAnimalsWithSameProperties_true(){
        NormalAnimal NormalAnimal1= new NormalAnimal("tiger","carnivore");
        NormalAnimal NormalAnimal2= new NormalAnimal("tiger","carnivore");
        assertTrue(NormalAnimal1.equals(NormalAnimal2));

    }

    @Test
    public  void save_insertNormalAnimalIntoDatabase_NormalAnimal(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivore");
        testNormalAnimal.save();
        NormalAnimal testNormalAnimal1=null;
        try(Connection con=DB.sql2o.open()){
            testNormalAnimal1=con.createQuery("SELECT *FROM animals WHERE name='tiger'")
                    .executeAndFetchFirst(NormalAnimal.class);
        }
        assertTrue(testNormalAnimal1.equals(testNormalAnimal));
    }

    @Test
    public void  all_returnAllInstanceOfAnimal_true(){
        NormalAnimal testNormalAnimal1=new NormalAnimal("tiger","carnivore");
        testNormalAnimal1.save();
        NormalAnimal testNormalAnimal2= new NormalAnimal("gazel","herbivor");
        testNormalAnimal2.save();
        assertEquals(true,NormalAnimal.all().get(0).equals(testNormalAnimal1));
        assertEquals(true,NormalAnimal.all().get(1).equals(testNormalAnimal2));
    }

    @Test
    public void save_idGivenToObject(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivor");
        testNormalAnimal.save();
        NormalAnimal savedNormalAnimal=NormalAnimal.all().get(0);
        assertEquals(testNormalAnimal.getId(),savedNormalAnimal.getId());
    }

    @Test
    public void find_filterAnimalwithTheSameId_Animal1(){
        NormalAnimal testNormalAnimal1= new NormalAnimal("tiger","carnivor");
        testNormalAnimal1.save();
        NormalAnimal testNormalAnimal2= new NormalAnimal("gazel","herbivor");
        testNormalAnimal2.save();
        assertEquals(NormalAnimal.findById(testNormalAnimal2.getId()),testNormalAnimal2);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void find_throwsExceptionIfSightinNotFound() {
        Sightings.findById(1);
    }

    @Test
    public  void findByName_filterNormalAnimalWithSameName_NormalAnimal1(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivor");
        testNormalAnimal.save();
        NormalAnimal testNormalAnimal1= new NormalAnimal("tiger","carnivor");
        testNormalAnimal1.save();
        assertEquals(NormalAnimal.findByName(testNormalAnimal1.getName()),testNormalAnimal1);
    }

    @Test
    public void delete_deleteEntryInDatabase_0(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivor");
        testNormalAnimal.save();
        testNormalAnimal.delete();
        assertEquals(0,NormalAnimal.all().size());
    }

    @Test
    public void  delete_deleteSightingGroup(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivor");
        testNormalAnimal.save();
        Sightings sightings= new Sightings("NE Quadrant", "Beline");
        sightings.save();
        sightings.addAnimal(testNormalAnimal);
        testNormalAnimal.delete();
        assertEquals(0,sightings.getNormalAnimal(sightings).size());
    }

    @Test
    public void filterSightingsFromNormalAnimal_int(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivor");
        testNormalAnimal.save();
        Sightings sightings= new Sightings("NE Quadrant", "Beline");
        sightings.save();
        sightings.addAnimal(testNormalAnimal);
        List savedSightings= testNormalAnimal.getSightings();
        assertEquals(1,savedSightings.size());
        assertTrue(savedSightings.contains(sightings));

    }



}
