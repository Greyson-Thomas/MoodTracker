package ingest;

import java.util.LinkedList;
import java.util.Scanner;

import ingest.helper.ParseXML;

public class Ingestion {
  
    private static LinkedList<Integer> buildDidOrDidNot = new LinkedList<Integer>();
    private static LinkedList<Integer> buildHowManyTimes = new LinkedList<Integer>();
    public static int NUM_OF_BOOL_QUESTIONS;
    public static int NUM_OF_NUMERICAL_QUESTIONS;
    private static LinkedList<String> boolQuestions;
    private static LinkedList<String> numQuestions;
    private static String entry = "";
    public static Scanner scanner = new Scanner(System.in);
    

    

    /*************************************************
     *              Getter Section
     *************************************************/
    public static LinkedList<Integer> getDidOrDidNot(){
      return buildDidOrDidNot;
    }

    public static LinkedList<Integer> getHowMany(){
      return buildHowManyTimes;
    }

    public static LinkedList<String> getBoolQuestions(){
      return boolQuestions;
    }

    public static LinkedList<String> getNumQuestions(){
      return numQuestions;
    }

    public static String getEntry(){
      return entry;
    }

    /*************************************************
     *              Setter Collection Section
     *************************************************/
    public static void setNumQuestions(LinkedList<String> list){
      numQuestions = list;
    }

    public static void setBoolQuestions(LinkedList<String> list){
      boolQuestions = list;
    }

    public static void setStatics(){
      try{
      Ingestion.setNumQuestions(ParseXML.parseQuestions("num"));
      Ingestion.setBoolQuestions(ParseXML.parseQuestions("bool"));
      Ingestion.NUM_OF_BOOL_QUESTIONS = Ingestion.getBoolQuestions().size();
      Ingestion.NUM_OF_NUMERICAL_QUESTIONS = Ingestion.getNumQuestions().size();
      } catch (Exception e){
        System.out.println(e.getMessage());
      }
    }

    /*************************************************
     *              Data Collection Section
     *************************************************/
    public static void buildBools(){
        try{
          
            buildDidOrDidNot.clear();
            String response = "";
            String possibleResponses = "[y/n]: ";
            int validation = -1;
            scanner.nextLine();
            for(int i = 0; i < NUM_OF_BOOL_QUESTIONS; i++){
                validation = -1;
                while(validation < 0){
                    System.out.println(boolQuestions.get(i) + possibleResponses);
                    response = scanner.nextLine();
                    validation = Validation.responseValidationBools(response);
                    if(validation >= 0){
                        buildDidOrDidNot.add(validation);
                    }
                }
            }
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void buildNums(){
        try{
            buildHowManyTimes.clear();
            String response = "";
            String possibleResponses = "[0-512]: ";
            int validation = -1;
            scanner.nextLine();
            for(int i = 0; i < NUM_OF_NUMERICAL_QUESTIONS; i++){
                validation = -1;
                while(validation < 0){
                    System.out.println(numQuestions.get(i) + possibleResponses);
                    response = scanner.nextLine().trim();
                    validation = Validation.responseValidationNums(response);
                    if(validation >= 0){
                        buildHowManyTimes.add(validation);
                    }
                }
            }
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void makeJournalEntry(){
        boolean confirmation = false;
        try {
            while(!confirmation){
                System.out.println("\nPlease tell me about your day:\n\n");
                scanner.nextLine();
                String response = scanner.nextLine().trim();
                System.out.println("Would you like to save this entry?\n" + response);
                System.out.println("\n[Y/N]: ");
                String answer = scanner.nextLine().trim();
                int confirmedEntry = Validation.responseValidationBools(answer);
                if(confirmedEntry > 0){
                    confirmation = true;
                    DatabaseConnection.setEnteredEntry(confirmation);
                    entry = response;
                } 
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
