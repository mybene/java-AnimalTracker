import org.h2.engine.Database;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class EndangeredTest {
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
    @Test
    public void name_nameGivencorrectly_String(){
        Endangered testEndangered= new Endangered("tiger","ill","young");
        assertEquals("tiger",testEndangered.getName();
    }
    @Test
    public void age_guessAgeCorrectly_int(){
        Endangered testEndangered= new Endangered("tiger","ill","young");
        assertEquals("young",testEndangered.getAge());
    }
}
