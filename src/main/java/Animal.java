import java.util.List;
import org.sql2o.*;

public class Animal {

    public int id;
    public String name;
    public String species;
    public boolean endangered;
    public String health;
    public String location;
    public String rangeName;
    public boolean sighting;

    public Animal(int id, String name, String species, boolean endangered, String health, String location, String rangeName, boolean sighting) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.endangered = false;
        this.health = health;
        this.location = location;
        this.rangeName = rangeName;
        this.sighting = false;
    }




//    public Animal( name, String species, String range_name, String eId, String health, String age, String location) {
//    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isEndangered() {
        return endangered;
    }

  @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animal)){
            return false;
        }else{
            Animal newAnimal=(Animal)otherAnimal;
            return this.id==newAnimal.id&&this.name.equals(newAnimal.name)&&
                    this.species.equals(newAnimal.species);
        }
  }

  public void save(){
        try(Connection con=DB.sql2o.open()){
            String sql="INSERT INTO animals(name,species,endangered) VALUES(:name,:species,:endangered)";
            this.id=(int)con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("endangered",this.endangered)
                    .addParameter("species",this.species)
                    .executeUpdate()
                    .getKey();
        }
  }

 public void delete(){
        try(Connection con=DB.sql2o.open()){
            String sql="DELETE FROM animals WHERE id=:id";
            con.createQuery(sql).addParameter("id",this.id).executeUpdate();
            String joinSql="DELETE FROM animals_sightings WHERE animal_id=:id";
            con.createQuery(joinSql).addParameter("id",this.id).executeUpdate();
        }
 }

 public List<Sightings>getSightings(){
        try(Connection con=DB.sql2o.open()){
            String sql="SELECT sightings.* FROM sightings JOIN animals_sightings ON(sightings.id= animals_sightings.sightings";
           return con.createQuery(sql).addParameter("id",this.id).executeAndFetch(Sightings.class);
        }
 }

 public static List<Animal> all() {
        String sql="SELECT *FROM animals";
        try(Connection con=DB.sql2o.open()){
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Animal.class);
        }
    }

}
