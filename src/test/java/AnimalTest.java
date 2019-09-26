import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatedCorrectly_true(){
        Animal testAnimal= new Animal("tiger","carnivore");
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test
    public  void animalName_animalHaveName_String(){
        Animal testAnimal = new Animal("tiger","carnivore");
        assertEquals("tiger",testAnimal.getName());
    }

    @Test
    public void animalSpecies_animalHaveId_String(){
        Animal testAnimal= new Animal("tiger","carnivore");
        assertEquals(true,testAnimal.getSpecies());
    }

    @Test
    public void equals_verifiedIfNAmeAndIdAreSame_true(){
        Animal Animal1= new Animal("tiger","carnivore");
        Animal Animal2= new Animal("tiger","carnivore");
        assertTrue(Animal1.equals(Animal2));

    }

    @Test
    public  void save_insertObjectIntoDatabase_Animal(){
        Animal testAnimal= new Animal("tiger","carnivore");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void  all_returnAllInstanceOfAnimal_true(){
        Animal Animal1=new Animal("tiger","carnivore");
        Animal1.save();
        Animal Animal2= new Animal("gazel","herbivor");
        Animal2.save();
        assertEquals(true,Animal.all().get(0).equals(Animal1));
        assertEquals(true,Animal.all().get(1).equals(Animal2));
    }

    @Test
    public void save_idGivenToObject(){
        Animal testAnimal= new Animal("tiger","carnivor");
        testAnimal.save();
        Animal savedAnimal=Animal.all().get(0);
        assertEquals(testAnimal.getId(),savedAnimal.getId());
    }

    @Test
    public void find_filterAnimalwithTheSameId_Animal1(){
        Animal Animal1= new Animal("tiger","carnivor");
        Animal1.save();
        Animal Animal2= new Animal("gazel","herbivor");
        Animal2.save();
        assertEquals(Animal.findById(Animal2.getId()),Animal2);
    }


}
