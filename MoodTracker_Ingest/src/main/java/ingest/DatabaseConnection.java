package ingest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import ingest.helper.ParseXML;

public class DatabaseConnection {

    public static  String sqlTableCreation;
    public static  String sqlAddDayWOEntry;
    public static  String sqlAddDayWEntry;
    private static boolean enteredEntry = false;
    private static Day sendDay = null;

    /*************************************************
     *              Setters Section
     *************************************************/
    public static void setEnteredEntry(boolean entryEntered){
      enteredEntry = entryEntered;
    }

    /*************************************************
     *              Database Section
     *************************************************/

    public static void sendToDatabase(){
      fillSQLStrings();
      Day today = Day.createDay();
      Connection connection = null;
      if(today.getActivities().size() != Ingestion.NUM_OF_BOOL_QUESTIONS || today.getAmounts().size() != Ingestion.NUM_OF_NUMERICAL_QUESTIONS){
          System.out.println("[REDIRECTING]: To Menu - Please enumerate activities and state whether or not you've done them");
          Menu.Menu();
      } else {
          try {
              connection = DriverManager.getConnection(ParseXML.parsePaths("sqlite"));

              Statement statement = connection.createStatement();
              statement.setQueryTimeout(30);
              statement.executeUpdate(sqlTableCreation);
              

              if(enteredEntry){
                  statement.executeUpdate(sqlAddDayWOEntry + "'" +today.getTodayDate()+ "'" + "," + "'" + today.computeSeason(today.getTodayDate().getMonthValue()) + "'" + "," + "'" +today.getWeather() + "'" + "," + today.getActivityString() + today.getAmountString() +"," + today.getEntry() +")");
              } else {
                  statement.executeUpdate(sqlAddDayWOEntry + "'" +today.getTodayDate()+ "'" + "," + "'" + today.computeSeason(today.getTodayDate().getMonthValue()) + "'" + "," + "'" +today.getWeather() + "'" + "," + today.getActivityString() + today.getAmountString() + ")");
              }
              sendDay = today;
          } catch (SQLException e) {
              System.err.println(e.getMessage());
          } catch (Exception e){
              System.err.println(e.getMessage());
          } finally {
              try {
                  if(connection != null){
                  connection.close();
                  } 
              } catch (SQLException e) {
                  System.err.println(e.getMessage());
              }
          }
          
      }
        
    }

    /*************************************************
     *              Helper Section
     *************************************************/
    public static void fillSQLStrings(){
        try {
            sqlTableCreation = "create table if not exists Day(id integer not null primary key autoincrement, `date` date not null, season string not null, weather string not null," + createSQLTableString() + " entry text)";
            sqlAddDayWOEntry = "insert into Day(`date`,season,weather," + sqlAddEntry("WO") + ") values(";
            sqlAddDayWEntry = "insert into Day(`date`,season,weather," + sqlAddEntry("W") + ") values(";
            AnalysisConnector.createCSVHeaders("`date`,season,weather," + sqlAddEntry("W"));
        } catch (Exception e) {
            System.out.println("Could not fill static variables");
        }
    }

    public static String createSQLTableString(){
        try {
            LinkedList<String> numHeaders = ParseXML.parseQuestionHeaders("num");
            LinkedList<String> boolHeaders = ParseXML.parseQuestionHeaders("bool");
            String tableString = "";

            for(String numHeader : numHeaders){
                tableString += numHeader + " int not null, ";
            }

            for(String boolHeader : boolHeaders){
                tableString += boolHeader + " int not null, ";
            }

            return tableString;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static String sqlAddEntry(String additionType){
        try {
            String insertionString = "";
            LinkedList<String> numHeaders = ParseXML.parseQuestionHeaders("num");
            LinkedList<String> boolHeaders = ParseXML.parseQuestionHeaders("bool");
            if(additionType.equals("WO")){
                for(String numHeader: numHeaders){
                    insertionString += numHeader + ",";
                }
                for(String boolHeader : boolHeaders){
                    insertionString += boolHeader + ",";
                }
                insertionString = insertionString.substring(0, insertionString.length()-1);
            } else {
                for(String numHeader: numHeaders){
                    insertionString += numHeader + ",";
                }
                for(String boolHeader : boolHeaders){
                    insertionString += boolHeader + ",";
                }
                insertionString += "entry";
            }
            return insertionString;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
