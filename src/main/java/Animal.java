import java.util.List;
import org.sql2o.*;

public class Animal {

   private int id;
   private String name;
   private String species;

   public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public Animal() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
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
            String sql="INSERT INTO animals(name,species) VALUES(:name,:species)";
            this.id=(int)con.createQuery(sql,true)
                    .addParameter("name",this.name)
                  /*  .addParameter("endangered",this.endangered)*/
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
