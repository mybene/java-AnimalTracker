import java.sql.Connection;
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

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    @Override
    public  boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal).otherAnimal;
            return this.getSpecies().equals(newAnimal.getSpecies()) &&
                    this.getName().equals(newAnimal.getName());
        }
    }

    public void save() {
        try (Connection con = DB.sql20.open()) {
            String sql = "INSERT INTO persons (name, species) VALUES (:name, :species)";
            con.createQuery(sql)
                    .addParameter("name", this.name)
                    .addParameter("species", this.species)
                    .executeUpdate();
        }
    }
 public static List<Animal> all(){
        String sql= "SELECT *FROM Animal";
        try(Connection con= DB.sql20.open()){
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
 }


}