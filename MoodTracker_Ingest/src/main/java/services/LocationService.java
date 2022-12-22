package services;

import ingest.helper.ParseXML;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import static java.util.Map.entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




public class LocationService {
  public static String apiURIBase = "http://api.positionstack.com/v1/forward?access_key=";

  public static String apiURIQuery = "&query=";

  public static Map<String,String> computeLocation(){
    try{
      apiURIQuery += ParseXML.parseUserLocationInfo();
      Map<String,String> keyMap = ParseXML.parsKeys("geo");
      String key = keyMap.get("key");
    
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(apiURIBase+key+apiURIQuery))
              .build();

      HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

      JSONParser parser = new JSONParser();
      JSONObject apiObject = (JSONObject) parser.parse(response.body());
      JSONArray apiArray = (JSONArray) apiObject.get("data");
      JSONObject locationMap = (JSONObject) apiArray.get(0);

      Map<String,String> weatherLocation = Map.of(
        "lat", locationMap.get("latitude").toString(),
        "long", locationMap.get("longitude").toString()
      );     

      return weatherLocation;
    
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

}
