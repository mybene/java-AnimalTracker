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

        get("/home",(request, response) -> {
           Map<String,Object>model=new HashMap<>();
           return  new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/form",(request, response)->{
            Map<String,Object>model=new HashMap<>();
            return new ModelAndView(model,"animalform.hbs");
        },new HandlebarsTemplateEngine());

        get("/success",(request, response) ->{
           Map<String,Object>model=new HashMap<>();
           String name=request.queryParams("name");
           String range_name=request.queryParams("range_name");
           String eId=request.queryParams("eId");
           String
           String age= request.queryParams("age");
           String location= request.queryParams("age");
           Animal record=new Animal(name,range_name,) {
           }
        } ))

         <th scope="row">{{name}}</th>
                <td>{{range_name}}</td>
                <td>{{eId}}</td>
                <td>{{age}}</td>
                <td>{{location}}</td>


    }
//
//
//
//        String layout = "/layout.hbs";
//
//      get("/",(request,response)->{
//          Map<String,Object>model=new HashMap<String,Object>();
//          model.put("templates","index.hbs");
//          return new ModelAndView(model,"layout.hbs");
//      },new HandlebarsTemplateEngine());
//
//      get("/welcome",(request,response)->{
//          Map<String,Object>model= new HashMap<String, Object>();
//          model.put("templates","index.hbs");
//      },new HandlebarsTemplateEngine());
//
//      get("/details",(req,res)->{
//          Map<Map<String, Object>, String> model= new HashMap<>();
//          model.put(model,"animals.hbs");
//      },new HandlebarsTemplateEngine());
//
////      post("/displayed",(request,response)->{
////          Map<String,Object>model=new HashMap<String, Object>();
////          String name=request.queryParams("Name");
////          Animal newAnimal=new Animal(name) ;
////          newAnimal.save();
////          return new ModelAndView(model,"details.hbs");
////      },new HandlebarsTemplateEngine());
//
//      get("/animals",(request,response)->{
//          Map<String,Object>model= new HashMap<String, Object>();
//          List<Animal>animals=Animal.all();
//          model.put("animals",animals);
//          return new ModelAndView(model,"animals.hbs")
//      },new HandlebarsTemplateEngine());
//
//      post("/endangered",(request,response)->{
//          Map<String,Object>model= new HashMap<String, Object>();
//          String Ename=request.queryParams("Ename");
//          String health=request.queryParams("health");
//          int Age= Integer.parseInt(request.queryParams("Eage"));
//          int EId=Integer.parseInt(request.queryParams("id"));
//          model.put("Ename",Ename);
//          model.put("health",health);
////          model.put("Eage",age);
//          return new ModelAndView(model,"endangered.hbs");
//      },new HandlebarsTemplateEngine());
//
//      get("/endangered",(request,response)-> {
//          Map<String, Object> model = new HashMap<String, Object>();
//          List<Endangered> endangered = Endangered.all();
//          model.put("endangered", endangered);
//          return new ModelAndView(model, "endangered.hbs");
//      },new HandlebarsTemplateEngine());
//
//      get("/sightings",(request,response)->{
//          Map<String,Object>model=new HashMap<String, Object>();
//          return new ModelAndView(model,"sighting.hbs");
//      },new HandlebarsTemplateEngine());
//
//      get("/sighting",(request,response)->{
//          Map<String,Object>model=new HashMap<String, Object>();
//          List<Sightings>Sighting=Sightings.all();
//      model.put("sighting",Sighting);
//      return new ModelAndView(model,"details2.hbs");
//      },new HandlebarsTemplateEngine());
//
//      post("/sighting",(request,response)->{
//          Map<String,Object>model=new HashMap<String, Object>();
//          String RangeName=request.queryParams("rangeName");
//          String Location=request.queryParams("location");
//          model.put("rangerName",RangeName);
//          model.put("location",Location);
//          Sightings newSighting=new Sightings(RangeName,Location);
//          newSighting.save();
//          return  new ModelAndView(model,"details2.hbs");
//      },new HandlebarsTemplateEngine());
//    }
}

