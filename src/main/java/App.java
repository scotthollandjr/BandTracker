import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/band/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputName = request.queryParams("name");
      String inputGenre = request.queryParams("genre");
      Band newBand = new Band(inputName, inputGenre);
      newBand.save();
      response.redirect("/");
      return null;
    });

    post("/venue/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputName = request.queryParams("name");
      String inputNumber = request.queryParams("number");
      String inputCity = request.queryParams("city");
      String inputState = request.queryParams("state");
      Venue newVenue = new Venue(inputName, inputNumber, inputCity, inputState);
      newVenue.save();
      response.redirect("/");
      return null;
    });

    get("/band/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("band", Band.find(Integer.parseInt(request.params(":id"))));
      model.put("bands", Band.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/venue/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("venue", Venue.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/band/:id/venue/new", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Band newBand = Band.find(Integer.parseInt(request.params(":id")));
    //   String inputName = request.queryParams("name");
    //   String inputNumber = request.queryParams("number");
    //   String inputCity = request.queryParams("city");
    //   String inputState = request.queryParams("state");
    //   Venue newVenue = new Venue(inputName, inputNumber, inputCity, inputState);
    //   newVenue.save();
    //   newBand.addVenue(newVenue);
    //   response.redirect("/band/" + newBand.getId());
    //   return null;
    // });
  }
}
