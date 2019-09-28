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

//        post("/success",(request, response) ->{
//           Map<String,Object>model=new HashMap<>();
////           Integer id= parseInt(request.queryParams("id"));
//           String name=request.queryParams("name");
//            String species=request.queryParams("species");
//           String range_name=request.queryParams("range_name");
//           String health=request.queryParams("health");
//           String age= request.queryParams("age");
//           String location= request.queryParams("location");
////          Animal newAnimal = new Animal(id,name,species,range_name,health,age,location);
//           model.put("name",name);
//           model.put("range_name",range_name);
//           model.put("health",health);
//           model.put("age",age);
//           model.put("location",location);
////            newAnimal.save();
//           return new ModelAndView(model,"success.hbs");
//        } , new HandlebarsTemplateEngine());

       post("/endangered",(request, response) -> {
           Map<String,Object>model=new HashMap<>();

       })

        post("/animals",(request, response) -> {
            Map<String,Object>model=new HashMap<>();
            List<Sightings>endangers=Sightings.all();
            List<Animal>animals=Animal.all();
            model.put("animals",animals);
            model.put("endangers",endangers);
            return  new ModelAndView(model,"sightings.hbs");
        },new HandlebarsTemplateEngine());



    }
}

