
import org.junit.Rule;
        import org.junit.Test;

        import static org.junit.Assert.*;

public class NormalAnimalTest {

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
    public void equals_verifiedIfNAmeAndIdAreSame_true(){
        NormalAnimal NormalAnimal1= new NormalAnimal("tiger","carnivore");
        NormalAnimal NormalAnimal2= new NormalAnimal("tiger","carnivore");
        assertTrue(NormalAnimal1.equals(NormalAnimal2));

    }

    @Test
    public  void save_insertObjectIntoDatabase_Animal(){
        NormalAnimal testNormalAnimal= new NormalAnimal("tiger","carnivore");
        testNormalAnimal.save();
        assertTrue(NormalAnimal.all().get(0).equals(testNormalAnimal));
    }

    @Test
    public void  all_returnAllInstanceOfAnimal_true(){
        NormalAnimal NormalAnimal1=new NormalAnimal("tiger","carnivore");
        NormalAnimal1.save();
        NormalAnimal NormalAnimal2= new NormalAnimal("gazel","herbivor");
        NormalAnimal2.save();
        assertEquals(true,NormalAnimal.all().get(0).equals(NormalAnimal1));
        assertEquals(true,NormalAnimal.all().get(1).equals(NormalAnimal2));
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
        NormalAnimal NormalAnimal1= new NormalAnimal("tiger","carnivor");
        NormalAnimal1.save();
        NormalAnimal NormalAnimal2= new NormalAnimal("gazel","herbivor");
        NormalAnimal2.save();
        assertEquals(NormalAnimal.findById(NormalAnimal2.getId()),NormalAnimal2);
    }


}
