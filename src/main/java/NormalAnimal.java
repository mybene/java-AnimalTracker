import java.util.List;
import org.sql2o.*;

public class NormalAnimal extends Animal {
//    private static Object NormalAnimal;
    private String name;
    private String species;
    private int id;
    private boolean endangered;


    public NormalAnimal(String name, String species) {
        this.name = name;
        this.species = species;
        endangered = false;
    }


    public String getName() {

        return name;
    }

    public String getSpecies() {

        return species;
    }

    public int getId() {

        return id;
    }

    public static List<NormalAnimal> all() {
        String sql = "SELECT *FROM animals WHERE endangered='false' ";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(NormalAnimal.class);
        }
    }


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,id, species) VALUES (:name, :id,:species)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("species", this.species)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static NormalAnimal findById(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT *FROM animals where id=:id";
            NormalAnimal newanimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NormalAnimal.class);
            if (newanimal == null) {
                throw new IndexOutOfBoundsException("this animal does not exist!!!");

            }
            return newanimal;
        }

    }


    public static NormalAnimal findByName(String name) {
        try(Connection con=DB.sql2o.open()){
            String sql="SELECT *FROM animals WHERE name=:name";
            NormalAnimal animal=con.createQuery(sql)
                    .addParameter("name",name)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NormalAnimal.class);
            return animal;

        }
    }

//    public List getSightings() {
//    }

//    public Collection<Object> getSighting() {
//    }

}
