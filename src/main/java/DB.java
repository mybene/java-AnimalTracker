import org.sql2o.*;
git
public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animals", null, null);
}
