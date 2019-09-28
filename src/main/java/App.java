import java.util.List;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        get("/",(request,response)->{
        Map<String,Object>model= new HashMap<>();
        return new ModelAndView(model,"layout.hbs");
        },new HandlebarsTemplateEngine());

        get("/animal/new",(request, response)->{
            Map<String,Object>model=new HashMap<>();
            return new ModelAndView(model,"animalForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/success",(request, response) ->{
           Map<String,Object>model=new HashMap<>();
           String name=request.queryParams("name");
           String range_name=request.queryParams("range_name");
          String eId=request.queryParams("eId");
           String health=request.queryParams("health");
           String age= request.queryParams("age");
           String location= request.queryParams("location");
           model.put("name",name);
           model.put("range_name",range_name);
           model.put("health",health);
           model.put("age",age);
           model.put("location",location);
           return new ModelAndView(model,"success.hbs");
        } , new HandlebarsTemplateEngine());

//        get("/endangered",(request, response) -> {
//            Map<String,Object>model=new HashMap<>();
//            return new ModelAndView(model,"endangered.hbs");
//        }, new HandlebarsTemplateEngine());

        post("/details",(request, response) -> {
            Map<String,Object>model=new HashMap<>();
            List<Sightings>endangers=Sightings.all();
            List<Animal>animals=Animal.all();
            model.put("animals",animals);
            model.put("endangers",endangers);
            return  new ModelAndView(model,"animals.hbs");
        },new HandlebarsTemplateEngine());



    }
}

