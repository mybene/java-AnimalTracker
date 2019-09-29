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

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "layout.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animalForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String species = request.queryParams("species");
            model.put("name", name);
            Animal newanimal = new Animal(name, species);
            newanimal.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/data", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> post = Animal.all();
            model.put("post", post);

            return new ModelAndView(model, "result.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sightingForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            String location = request.queryParams("location");
            model.put("name", rangerName);
            model.put("location", location);
            Sightings animal = new Sightings(rangerName, location);
            animal.save();
            return new ModelAndView(model, "sighting.hbs");
        }, new HandlebarsTemplateEngine());


        get("/sightings", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> post = Animal.all();
            model.put("post", post);

            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangeredForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endnagered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName");
            String location = request.queryParams("location");
            model.put("name", rangerName);
            model.put("location", location);
            Sightings animal = new Sightings(rangerName, location);
            animal.save();
            return new ModelAndView(model, "endangered.hbs");
        }, new HandlebarsTemplateEngine());


    }
}

