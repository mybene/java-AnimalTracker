import java.util.List;
import org.sql2o.*;

public class Animal {
    public String name;
    public String species;
    public   int id;


    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public Animal() {
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



    public void save() {
        try (Connection con= DB.sql2o.open()) {
            String sql = "INSERT INTO persons (name, species) VALUES (:name, :species)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("species", this.species)
                    .executeUpdate()
                    .getKey();
        }
    }

     public static List<Animal> all(){
        String sql= "SELECT *FROM Animal";
        try(Connection con= DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
     }
     public static  Animal findById(int id){
        try(Connection con=DB.sql2o.open()){
            String sql ="SELECT *FROM animals where id=:id";
            Animal animal=con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
     }


//    public interface Animal1 {
//    }
}