package buisnessLayer;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("dukes")
public class DukesResource {
    @GET
    public JsonArray dukes(){
        return Json.createArrayBuilder()
                .add(duke("lol",13))
                .add(duke("kik",19))
                .add(duke("lel",19))
                .build();
    }

    public JsonObject duke(String name, int age){
        return Json.createObjectBuilder().
                add("name",name).
                add("age",age).
                build();
    }
}
