import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Sightings {
    private int id;
    private  String location;
    private String rangeName;
    private Timestamp whichtime;
    private String NormalAnimal;

    public Sightings(String location, String rangeName) {
        this.location = location;
        this.rangeName = rangeName;
    }

    public String getLocation() {

        return location;
    }

    public String getRangename()
    {
        return rangeName;
    }

    public int getId(){
        return id;
    }

    public static List<Sightings> allByDate() {
        String sql="SELECT *FROM sightings ORDER BY whichtime DESC";
        try(Connection con=DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }

    public static Sightings findById(int id) {
        try(Connection con=DB.sql2o.open()){
            String sql="SELECT * FROM sightings WHERE id=:id";
            Sightings sightings=con.createQuery(sql).addParameter("id",id)
                    .executeAndFetchFirst(Sightings.class);
            if(sightings==null){
                throw  new IndexOutOfBoundsException("I'm soory ,I think this sighting doesn't exist");
            }
            return sightings;
        }
    }

    public static List<Sightings> all() {
        String sql="SELECT *FROM  sightings";
        try(Connection con=DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }


    public List<Endangered> getEndangered() {
        try(Connection con=DB.sql2o.open()){
            String sql="SELECT animals.*FROM animals JOIN animals_sightings ON(animals.id=animals_sighting.animal_id)WHERE animals_sightings.sighting_id=:id AND animals.endangered=true";
         return con.createQuery(sql).addParameter("id",this.id).throwOnMappingFailure(false).executeAndFetch(Endangered.class);
        }
    }

    public void save() {
        try(Connection con=DB.sql2o.open()){
            String sql="INSERT INTO  sightings(location, rangeName ,whichtime) VALUES(:location,:rangerName,now())";
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("location",this.location)
                    .addParameter("rangerName",this.rangeName)
                    .executeUpdate()
                    .getKey();
        }
    }

    public void addAnimal(Animal animal) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals_sightings(sighting_id,animal_id)VALUES(:sighting_id,:animal_id)";
            con.createQuery(sql).addParameter("sighting_id", this.id)
                    .addParameter("animal_id", animal.getId())
                    .executeUpdate();

        }
    }

//    public List getNoramalAnimal() {
//    }

    public void delete(){
        try(Connection con= DB.sql2o.open()){
           String sql="DELETE FROM sightings WHERE id=:id";
           con.createQuery(sql).addParameter("id",this.id).executeUpdate();
           String joinSql=" DELETE FROM animals_sightings WHERE sighting_id=:id";
           con.createQuery(joinSql).addParameter("id",this.id)
                   .executeUpdate();
        }
    }


    public List<NormalAnimal> getNormalAnimal(){
        try(Connection con=DB.sql2o.open()){
            String sql="SELECT animals.*FROM animals JOIN animals_sightings ON(animals.id=animals_sightings.animal_id)WHERE animals_sightings.sighting_id =:id AND animals.endangered='false' ";
         con.createQuery(sql).addParameter("id",this.id).throwOnMappingFailure(false).executeAndFetch(NormalAnimal.class);
        }
    }

    public static List<Sightings> mostRecent(){
            String sql="SELECT *FROM SIGHTINGS where whichtime BETWEEN now()-interval'36hours' AND now() ORDER BY whichtime DESC LIMIT 5";
            try(Connection con=DB.sql2o.open()){
                return  con.createQuery(sql).executeAndFetch(Sightings.class);
             }
        }




    public String getFormattedDate() {
        return DateFormat.getDateTimeInstance().format(whichtime);
    }

    public Timestamp getWhichtime() {
        return whichtime;
    }



    public String getNormalAnimal(Sightings sightings) {
        return NormalAnimal;
    }
}
