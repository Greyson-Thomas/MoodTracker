package services;

import ingest.helper.ParseXML;

import java.util.Map;




public class LocationService {
  public static String apiURI = "https://api.api-ninjas.com/v1/geocoding?city=london";

  public static void main(String[] args){
    try{
      Map<String,String> map = ParseXML.parsKeys("This");
      System.out.println(map.get("key"));
      // HttpClient client = HttpClient.newHttpClient();
      // HttpRequest request = HttpRequest.newBuilder()
      //         .uri(URI.create(apiURI))
      //         .build();

      // HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
      // System.out.println(response.body());
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}
