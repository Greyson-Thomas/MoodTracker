package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import static java.util.Map.entry;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;





public class WeatherService {
  public static String apiURI = "https://api.open-meteo.com/v1/forecast?latitude=30.35&longitude=-97.72&current_weather=true";


  public static Map<Integer,String> weatherCodes = Map.ofEntries(
    entry(0,"Clear Sky"),
    entry(1,"Mainly Clear"),
    entry(2,"Partly Cloudy"),
    entry(3,"Overcast"),
    entry(45,"Fog"),
    entry(48,"Depositing Rime Fog"),
    entry(51,"Light Drizzle"),
    entry(53,"Moderate Drizzle"),
    entry(55,"Dense Drizzle"),
    entry(56,"Light Freezing Drizzle"),
    entry(57,"Dense Freezing Drizzle"),
    entry(61,"Slight Rain"),
    entry(63,"Moderate Rain"),
    entry(65,"Heavy Rain"),
    entry(66,"Light Freezing Rain"),
    entry(67,"Heavy Freezing Rain"),
    entry(71,"Slight Snow Fall"),
    entry(73,"Moderate Snow Fall"),
    entry(75,"Heavy Snow Fall"),
    entry(77,"Snow grains"),
    entry(80,"Slight Rain Showers"),
    entry(81,"Moderate Rain Showers"),
    entry(82,"Violent Rain Showers"),
    entry(85,"Slight Snow Shower"),
    entry(86,"Heavy Snow Shower"),
    entry(95,"Thunderstorm (Slight/Moderate)"),
    entry(96,"Thunderstorm Slight Hail"),
    entry(99,"Thunderstorm Heavy Hail")
  );

  public static String computeWeather() throws IOException{
    try{
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiURI))
            .build();

    HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

    JSONParser parser = new JSONParser();
    JSONObject apiObject = (JSONObject) parser.parse(response.body());
    JSONObject weatherObject = (JSONObject) apiObject.get("current_weather");
    Long weatherCode = (Long) weatherObject.get("weathercode");

    return weatherCodes.get(weatherCode.intValue());
    
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "Was not able to work retrieve weather";
  }
}


