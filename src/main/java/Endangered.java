import org.sql2o.Connection;

import java.util.List;

public class Endangered extends Animal{

    private final Object Endangered;
    private String Ename;
    private String health;
    private  String Eage;


 public static final String health_ill = "ill";
 public static final  String health_okay="okay";
 public static final String health_health="healthy";

 public static final String age_newborn="newborn";
 public static final String age_young="young";
 public static final String age_adult="adult";
    public Object getId;

    public Endangered(String Ename, String health, String Eage) {
        this.Ename = Ename;
        this.health = health;
        this.Eage = Eage;
        Endangered=true;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return Eage;
    }

    public void save(){
        save();
        try(Connection con=DB.sql2o.open()){
            String sql= "INSERT INTO endangereds (name,id,health,age) VALUES(:name,:id,:health,:age)";
            this.id=(int)con.createQuery(sql,true)
              .addParameter("name",this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.Eage)
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

    public static List<Endangered> all(){
        String sql="SELECT *FROM endangereds";
        try(Connection con=DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Endangered.class);
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
