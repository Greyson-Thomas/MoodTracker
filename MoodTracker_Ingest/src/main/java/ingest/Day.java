package ingest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Map;
import static java.util.Map.entry;

import services.WeatherService;

public class Day {
  private LocalDate todayDate;
  private LocalTime todayTime;
  private String todayWeather;
  private Season seasonOfYear;
  private String entry;
  private LinkedList<Integer> activityOccurring = new LinkedList<Integer>();
  private LinkedList<Integer> amountOccuring = new LinkedList<Integer>();
  private String activityString = "";
  private String amountString = "";


  private Map<Integer,Season> seasonMapUS = Map.ofEntries(
    entry(12,Season.winter),
    entry(1, Season.winter),
    entry(2, Season.winter),
    entry(3, Season.spring),
    entry(4, Season.spring),
    entry(5, Season.spring),
    entry(6, Season.summer),
    entry(7, Season.summer),
    entry(8, Season.summer),
    entry(9, Season.fall),
    entry(10, Season.fall),
    entry(11, Season.fall)
  );

  public enum Season {
    summer,
    winter,
    fall,
    spring
  }

  public Day(){
    todayDate = LocalDate.now();
    todayTime = LocalTime.now();
    seasonOfYear = computeSeason(todayDate.getMonthValue());
  }

  /*************************************************
   *              Getter Section
   *************************************************/
  public LocalDate getTodayDate(){
    return todayDate;
  }

  public Map<Integer,Season> getSeasonMap(){
    return seasonMapUS;
  }

  public LocalTime getTodayTime(){
    return todayTime;
  }

  public String getSeason(){
    String seasonString = "";
    switch(seasonOfYear){
      case summer: seasonString = "summer";
           break;
      case winter: seasonString = "winter";
           break;
      case fall: seasonString = "fall";
           break;
      case spring: seasonString = "spring";
           break;
    }
    return seasonString;
  }

  public LinkedList<Integer> getActivities(){
    return activityOccurring;
  }

  public LinkedList<Integer> getAmounts(){
    return amountOccuring;
  }

  public String getActivityString(){
    return activityString;
  }

  public String getAmountString(){
    return amountString;
  }

  public String getWeather(){
    // Figure out API to get local weather
    try {
      return WeatherService.computeWeather();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "";
  }

  public String getEntry(){
    return entry;
  }


  /*************************************************
   *              Setter Section
   *************************************************/
  public void setActivityOccurringList(LinkedList<Integer> list){
    String buildActivityString = "";
    for(int i = 0; i < list.size(); i++){
      buildActivityString += list.get(i) + ",";
    }
    
    activityOccurring = list;
    activityString = buildActivityString;
  }

  public void setAmountOccurringList(LinkedList<Integer> list){
    String buildAmountString = "";
    for(int i = 0; i < list.size(); i++){
      if(i < list.size() -1){
        buildAmountString += list.get(i) + ",";
      } else {
        buildAmountString += list.get(i);
      }
      
    }
    
    amountOccuring = list;
    amountString = buildAmountString;
  }

  /*************************************************
   *              Method Section
   *************************************************/

  public static Day createDay(){
    try {
        Day today = new Day();
        today.setActivityOccurringList(Ingestion.getDidOrDidNot());
        today.setAmountOccurringList(Ingestion.getHowMany());
        return today;
    } catch (Exception e) {
        System.out.println("Cannot create the day. Make sure you entered in all activities and the amount they occurred");
    }
    return null;
  }

  public Season computeSeason(int month){
    return seasonMapUS.get(month);
  }

  public void computeWeather(){
    todayWeather = "";
  }

}
