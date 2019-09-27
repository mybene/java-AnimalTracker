import java.util.Collection;
import java.util.List;
import org.sql2o.*;

public class NormalAnimal{
    public String name;
    public String species;
    public   int id;


    public NormalAnimal(String name, String species) {
        this.name = name;
        this.species = species;
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
        String sql="SELECT *FROM animals WHERE endangered='false' ";
        try(Connection con=DB.sql2o.open()){
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(NormalAnimal.class);
        }
    }


    public void save() {
        try (Connection con= DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,id, species) VALUES (:name, :id,:species)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("species", this.species)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static  NormalAnimal findById(int id){
        try(Connection con=DB.sql2o.open()){
            String sql ="SELECT *FROM animals where id=:id";
            NormalAnimal animal=con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(NormalAnimal.class);
            return animal;
        }
    }

    public void delete() {
    }

    public static long findByName(String name) {
    }

    public List getSightings() {
    }

    public Collection<Object> getSighting() {
    }


//    public interface Animal1 {
//    }
}
