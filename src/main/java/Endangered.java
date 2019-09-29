
import org.sql2o.Connection;

public class Endangered extends Animal{

    private String name;
    private String health;
    private Integer age;


 public static final String health_ill = "ill";
 public static final  String health_okay="okay";
 public static final String health_health="healthy";

 public static final String age_newborn="newborn";
 public static final String age_young="young";
 public static final String age_adult="adult";
    private int id;

    public Endangered(Integer age, String health) {
        this.health = health;
        this.age = age;
    }

    public String getHealth() {
        return health;
    }

    public Integer getAge() {
        return age;
    }

    public void save(){
        save();
        try(Connection con=DB.sql2o.open()){
            String sql= "INSERT INTO endangereds (name,id,health,age) VALUES(:name,:id,:health,:age)";
            this.id=(int)con.createQuery(sql,true)
              .addParameter("name",this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static Endangered findById(int id){
        try(Connection con= DB.sql2o.open()){
            String sql="SELECT *FROM animals WHERE id=:id";
            Endangered animal=con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Endangered.class);
            if(animal==null){
                throw new IndexOutOfBoundsException("This animal does not exist!!please,try agian...");
            }
            return animal;
        }
    }
    public static Endangered findByName(Endangered name){
        try(Connection con=DB.sql2o.open()){
            String sql= "SELECT *FROM animals WHERE name=:name";
            Endangered animal=con.createQuery(sql)
                    .addParameter("name",name)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Endangered.class);
            return animal;
        }
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
//
//    public void setName(Endangered name) {
//        this.name = name;
//    }
//
//    public static long findById(Object getId) {
//    }
//
//    public void delete() {
//    }
//
//    public List getSightings() {
//    }
}

