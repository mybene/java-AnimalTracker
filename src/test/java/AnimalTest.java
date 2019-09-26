import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void animal_instantiatedCorrectly_true(){
        Animal testAnimal= new Animal("tiger",1);
        assertEquals(true,testAnimal instanceof Animal);
    }

    @Test
    public  void animalName_animalHaveName_String(){
        Animal testAnimal = new Animal("tiger",1);
        assertEquals("tiger",testAnimal.getName());
    }

    @Test
    public void animalId_animalHaveId_Integer(){
        Animal testAnimal= new Animal("tiger",1);
        assertEquals(true,testAnimal.getId());
    }

    @Test
    public void equals_verifiedIfNAmeAndIdAreSame_true(){
        Animal Animal1= new Animal("tiger",1);
        Animal Animal2= new Animal("tiger",1);
        assertTrue(Animal1.equals(Animal2));

    }

    @Test
    public  void save_insertObjectIntoDatabase_Animal(){
        Animal testAnimal= new Animal("tiger",1);
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
}
