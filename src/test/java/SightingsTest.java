//import org.junit.Rule;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//
//import static org.junit.Assert.*;
//
//public class SightingsTest {
//    Sightings testSightings;
//    @Rule
//    DatabaseRule database= new DatabaseRule();
//
//    @Test
//    public void Sightings_instatiatedCorrectly_true(){
//        Sightings testSightings=new Sightings("zone A","David");
//       assertEquals(true,testSightings instanceof Sightings);
//    }
//
////    @Test
////    public void identity_animalIdentified_true(){
////        Sightings testSightings=new Sightings("kangourou","zone A","David");
////        assertEquals("kangourou",testSightings.getSpecies());
////    }
//
//    @Test
//    public void location_animalLocalized_true(){
//        Sightings testSightings=new Sightings("kangourou","David");
//        assertEquals("zone A",testSightings.getLocation());
//    }
//
//    @Test
//    public void rangerName_instatiatedCorrectly_true(){
//        Sightings testSightings=new Sightings("zone A","David");
//        assertEquals("David",testSightings.getRangename());
//    }
//
//    @Test
//    public void equals_returnTrueIfPropertiesAreSame_true(){
//        Sightings testSightings1= new Sightings("zone A","David");
//        assertTrue(testSightings.equals(testSightings1));
//    }
//
//    @Test
//    public void savedIntoDataBase_Sightings(){
//        testSightings.save();
//        Sightings testSightings2=null;
//        try(Connection con=DB.sql2o.open()){
//            testSightings2=con.createQuery("SELECT *FROM sightings WHERE location='zone A'")
//                    .executeAndFetchFirst(Sightings.class);
//        }
//        assertTrue(testSightings2.equals(testSightings));
//    }
//
//    @Test
//    public void all_returnAllInstancesOfNormalAnimal_true(){
//        testSightings.save();
//        Sightings testSightings2 = new Sightings("NE Quadrant","Beline");
//        testSightings2.save();
//        assertEquals(true,Sightings.all().get(0).equals(testSightings));
//        assertEquals(true,Sightings.all().get(1).equals(testSightings2));
//    }
//
//    @Test
//    public void save_assignedIdToSightings(){
//        testSightings.save();
//        Sightings  sighting1= Sightings.all().get(0);
//        assertEquals(testSightings.getId(),sighting1.getId());
//    }
//
//    @Test
//    public void find_filterSightinsWithSameId_secondSighting(){
//        testSightings.save();
//        Sightings sighting1= new Sightings("NE Quadrant","Beline");
//        testSightings.save();
//        assertEquals(Sightings.find(sighting1.getId()),sighting1);
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void find_throwsExceptionIfSightinNotFound(){
//        Sightings.find(1);
//    }
//
//    @Test
//    public void save_updateDateInDatabase_Sighting(){
//        Sightings testSightings= new Sightings("NE Quadrant","Beline");
//        testSightings.save();
//        Timestamp savedDate=Sightings.find(testSightings.getId()).getWhichtime();
//        Timestamp rigthNow= new Timestamp(new Date().getTime());
//        assertEquals(rigthNow.getDate(),savedDate.getDate());
//        assertEquals(rigthNow.getHours(),savedDate.getHours());
//    }
//
//    @Test
//    public void formattedDate_getFormattedDate_Sighting(){
//        Sightings testSightings= new Sightings("NE Quadrant","Beline");
//        testSightings.save();
//        Sightings savedSighting= Sightings.find(testSightings.getId());
//        Timestamp rigthNow= new Timestamp(new Date().getTime());
//        assertEquals(DateFormat.getDateTimeInstance().format(rigthNow),savedSighting.getFormattedDate());
//    }
//
//    @Test
//    public  void delete_deletesEntryInDatabase_0(){
//        Sightings testSightings= new Sightings("NE Quadrant","Beline");
//        testSightings.save();
//        testSightings.delete();
//        assertEquals(0,Sightings.all().size());
//
//    }
//    @Test
//    public  void  delete_deletesSightingGroup(){
//        testSightings.save();
//        NormalAnimal testNormalAnimal= new NormalAnimal("cat","carnivor");
//        testNormalAnimal.save();
//        testSightings.addAnimal(testNormalAnimal);
//        testSightings.delete();
//        assertEquals(0,testNormalAnimal.getSighting().size());
//    }
//
//    @Test
//    public void update_updateSightingsGroup(){
//        testSightings.save();
//        NormalAnimal testNormalAnimal= new NormalAnimal("cat","carnivor");
//
//
//    }
//
//}
