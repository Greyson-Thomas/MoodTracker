package ingest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedList;

import ingest.Day.*;

public class DayTest {

  @Test
  public void TestDayConstructorDateTimeSeason(){
    Day testDay = new Day();

    assertNotEquals(testDay.getSeason(),null ,"[Test Failed]: constructor did not compute the season");
    assertNotEquals(testDay.getTodayDate(),null,"[Test Failed]: constructor was not able to grab the date" );
    assertNotEquals(testDay.getTodayTime(),null,"[Test Failed]: constructor was not able to grab time" );
  }

  @Test
  public void TestDayComputationSeasonWinterUS(){
    Day testDay = new Day();

    assertEquals(testDay.computeSeason(12),Season.winter, "[Test Failed]: Winter season for US was improperly computed");
    assertEquals(testDay.computeSeason(1),Season.winter, "[Test Failed]: Winter season for US was improperly computed");
    assertEquals(testDay.computeSeason(2),Season.winter, "[Test Failed]: Winter season for US was improperly computed");

  }

  @Test
  public void TestDayComputationSeasonSpringUS(){
    Day testDay = new Day();

    assertEquals(testDay.computeSeason(3),Season.spring, "[Test Failed]: Spring season for US was improperly computed");
    assertEquals(testDay.computeSeason(4),Season.spring, "[Test Failed]: Sprin season for US was improperly computed");
    assertEquals(testDay.computeSeason(5),Season.spring, "[Test Failed]: Spring season for US was improperly computed");

  }

  @Test
  public void TestDayComputationSeasonSummerUS(){
    Day testDay = new Day();

    assertEquals(testDay.computeSeason(6),Season.summer, "[Test Failed]: Summer season for US was improperly computed");
    assertEquals(testDay.computeSeason(7),Season.summer, "[Test Failed]: Summer season for US was improperly computed");
    assertEquals(testDay.computeSeason(8),Season.summer, "[Test Failed]: Summer season for US was improperly computed");

  }

  @Test
  public void TestDayComputationSeasonFallUS(){
    Day testDay = new Day();

    assertEquals(testDay.computeSeason(9),Season.fall, "[Test Failed]: Fall season for US was improperly computed");
    assertEquals(testDay.computeSeason(10),Season.fall, "[Test Failed]: Fall season for US was improperly computed");
    assertEquals(testDay.computeSeason(11),Season.fall, "[Test Failed]: Fall season for US was improperly computed");

  }

  @Test
  public void TestDayActivities(){
    Day testDay = new Day();

    String testString = "1,1,2,1,1,2,1,";
    String[] testActivityString = testString.split(",");
    LinkedList<Integer> testList = new LinkedList<>();

    for(String item : testActivityString){
      testList.add(Integer.parseInt(item));
    }
    testDay.setActivityOccurringList(testList);

    assertNotNull(testDay.getActivities(),"[Test Failed]: Day activity list was not computed");
    assertNotNull(testDay.getActivityString(),"[Test Failed]: Was not able to create");
    assertEquals(testDay.getActivities().size(),testList.size(), "[Test Failed]: Activity list has the wrong number of items");
  }

  @Test
  public void TestDayAmountString(){
    Day testDay = new Day();

    String testString = "1,1,2,1,1,2,1,";
    String[] testAmountString = testString.split(",");
    LinkedList<Integer> testList = new LinkedList<>();

    for(String item : testAmountString){
      testList.add(Integer.parseInt(item));
    }
    testDay.setAmountOccurringList(testList);

    assertNotNull(testDay.getAmounts(),"[Test Failed]: Day amount list was not computed");
    assertNotNull(testDay.getAmountString(),"[Test Failed]: Was not able to create amount string");
    assertEquals(testDay.getAmounts().size(),testList.size(), "[Test Failed]: Amount list has the wrong number of items");
  }
}
