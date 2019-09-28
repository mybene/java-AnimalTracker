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

        post("/success",(request, response) -> {
            Map<String,Object>model=new HashMap<>();
            String name=request.queryParams("name");
            String species=request.queryParams("species");
            String rangeName=request.queryParams("rangeName");
            String location= request.queryParams("location");
            String health=request.queryParams("health");
            String age= request.queryParams("age");
            model.put("name",name);
            model.put("range_name",rangeName);
            model.put("health",health);
            model.put("age",age);
            model.put("location",location);
            return new ModelAndView(model,"success.hbs");
        } , new HandlebarsTemplateEngine());

        post("/endangered",(request, response) -> {
                    Map<String, Object> model = new HashMap<>();
                    String name = request.queryParams("name");
                    String species = request.queryParams("species");
                    boolean endangered = request.queryParamsValues("endangered") != null;
                    if (endangered==true) {
                        String health = request.queryParams("health");
                        String age = request.queryParams("age");
                        Endangered animal = new Endangered(name, species, health, age);
                        animal.save();
                    } else {
                        NormalAnimal normalAnimal = new NormalAnimal(name, species);
                        normalAnimal.save();
                    }
                    return new ModelAndView(model, "endangered.hbs");
                    },new HandlebarsTemplateEngine());

        post("/sightings",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String species = request.queryParams("species");
            boolean sightings = request.queryParamsValues("sightings ") != null;
            if (sightings==true ) {
                String rangeName=request.queryParams("rangeName");
                String location= request.queryParams("location");
                Sightings  animal = new Sightings(rangeName,location);
                animal.save();
            } else {
                NormalAnimal normalAnimal = new NormalAnimal(name, species);
                normalAnimal.save();
            }
            return new ModelAndView(model, "sightings.hbs");
        },new HandlebarsTemplateEngine());

//


    }
}

