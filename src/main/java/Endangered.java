public class Endangered extends Animal{

    private final Object Endangered;
    public String name;
 public String health;
 public String age;


 public static final String health_ill = "ill";
 public static final  String health_okay="okay";
 public static final String health_health="healthy";

 public static final String age_newborn="newborn";
 public static final String age_young="young";
 public static final String age_adult="adult";

    public Endangered(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        Endangered=true;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }
}
