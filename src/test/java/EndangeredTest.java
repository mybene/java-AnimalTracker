//import org.h2.engine.Database;
//import org.junit.*;
//import static org.junit.Assert.*;
//import org.sql2o.*;
//
//
//public class EndangeredTest {
//    Endangered testEndangered;
//    @Rule
//    DatabaseRule database= new DatabaseRule();
//
//    @Test
//    public void endangered_instantiatedCorrectly_true(){
//        Endangered testEndangered= new Endangered("tiger","ill","young");
//        assertEquals(true,testEndangered instanceof Endangered);
//    }
//
//    @Test
//    public void healthState_checkHealth_String(){
//        Endangered testEndangered= new Endangered("tiger","ill","young");
//        assertEquals("ill",testEndangered.getHealth());
//
//    }
////    @Test
////    public void name_nameGivencorrectly_String(){
////        Endangered testEndangered= new Endangered("tiger","ill","young");
////        assertEquals("tiger",testEndangered.getName());
////    }
//    @Test
//    public void age_guessAgeCorrectly_int(){
//        Endangered testEndangered= new Endangered("tiger","ill","young");
//        assertEquals("young",testEndangered.getAge());
//    }
//    @Test
//    public void save_EndangeredIdAreEqualsToAnimalId(){
//       Endangered testEndangered=new Endangered("tiger","ill","young");
//        testEndangered.save();
//       assertEquals(Endangered.findById(testEndangered.getId()),testEndangered.getId());
//    }
//    @Test
//    public void saveNmae_endangeredAndAnimalHaveTheSameName_true(){
//        Endangered testEndangered=new Endangered("tiger","ill","young");
//        testEndangered.save();
//        assertEquals(Endangered.findByName(testEndangered),testEndangered.getName());
//    }
//
//    @Test
//    public void all_returnAllInstancesOfEndangered_true(){
//        Endangered  Endangered1= new Endangered("tiger","ill","young");
//        Endangered1.save();
//        Endangered Endangered2= new Endangered("gazel","okay","adult");
//        Endangered2.save();
//        assertEquals(true,Endangered.all().get(0).equals(Endangered1));
//        assertEquals(true,Endangered.all().get(1).equals(Endangered2));
//    }
//}
