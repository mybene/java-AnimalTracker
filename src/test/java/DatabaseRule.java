import org.junit.rules.ExternalResource;

public class DatabaseRule extends ExternalResource {
@Override
    protected void before(){
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animals", null, null);  //Those with linux or windows use  astring for username and an intenger for id
}
}
