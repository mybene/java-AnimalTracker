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

        get("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangeredForm.hbs");
        }, new HandlebarsTemplateEngine());

//        post("/endnagered/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Integer age = Integer.parseInt(request.queryParams("age"));
//            String health= request.queryParams("health");
//            Endangered newEndangers= new Endangered(age,health);
//            request.session().attribute("endangered",newEndangers);
////            model.put("age", age);
////            model.put("health", health);
////            request.session().attribute("item",age);
//            model.put("endangered",request.session().attribute("endangered"));
//            model.put("newEndangers",newEndangers);
//            return new ModelAndView(model, "endangered.hbs");
//        }, new HandlebarsTemplateEngine());


        post("/endangers",(req, res) ->{
            Map<String, Object> model = new HashMap<>();
            String health = req.queryParams("health");
            Integer age = Integer.parseInt(req.queryParams("age"));
            Endangered newEndangers = new Endangered(age, health);
//            req.session().attribute("item",health);
            model.put("item",req.session().attribute("item"));
            model.put("danger",newEndangers);
            return new ModelAndView(model, "endangered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Animal> endangereds= Animal.all();
            model.put("post", endangereds);
            return new ModelAndView(model, "endangered.hbs");
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
            List<Sightings> get = Sightings.all();
//            model.put("get ", get);
            return new ModelAndView(model, "sigthings.hbs");
        }, new HandlebarsTemplateEngine());



//        post("/new/hero",(req, res) ->{
//            Map<String, Object> model = new HashMap<>();
//            String name = req.queryParams("name");
//            Integer age = Integer.parseInt(req.queryParams("age"));
//            String power = req.queryParams("power");
//            String weakness = req.queryParams("weakness");
//            Hero newHero = new Hero(name,age,power,weakness);
//            req.session().attribute("item",name);
//            model.put("item",req.session().attribute("item"));
//            model.put("newHero",newHero);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());



    }
}

