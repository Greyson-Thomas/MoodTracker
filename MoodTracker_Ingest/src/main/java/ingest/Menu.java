package ingest;

public class Menu {
    private static enum Answer {Yes,No};
    
    /*************************************************
     *              Menu Print Section
     *************************************************/
    public static int Menu(){
        Integer response = 0;
        Ingestion.setStatics();
        while(true){
          System.out.println("\nEnter the corresponding value");
          System.out.println("\tWhether Activities Were Performed [1]");
          System.out.println("\tNumber of Times Activities Were Performed[2]");
          System.out.println("\tJournal Entry[3]");
          System.out.println("\tSend values to database[4]");
          System.out.println("\tCheck the entered day data[5]");
          System.out.println("\tLeave the Menu[6]");
          System.out.print("Please enter your destination: ");
          try{
              response = Ingestion.scanner.nextInt();
              switch(response){
                  case 1: Ingestion.buildBools();
                          break;
                  case 2: Ingestion.buildNums();
                          break;
                  case 3: Ingestion.makeJournalEntry();
                          break;
                  case 4: DatabaseConnection.sendToDatabase();
                          break;
                  case 5: checkEntries();
                          break;
                  case 6: return 1;
              }
          } catch(Exception e){
              System.out.println("Please make sure that all information is filled out appropriately");
          }
            
        }
    }
  
    /*************************************************
     *              Check Inputs Section
     *************************************************/
    private static void checkBools(){
        try{
            if(Ingestion.getDidOrDidNot().size() == Ingestion.NUM_OF_BOOL_QUESTIONS){
                System.out.println();
                for(int i = 0; i < Ingestion.NUM_OF_BOOL_QUESTIONS; i++){
                    System.out.println(Ingestion.getBoolQuestions().get(i)  + ": " + ((Ingestion.getHowMany().get(i)==1) ? Answer.Yes : Answer.No));
                }

            } else {
                throw new Exception("\nPlease enter in the activities performed first");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void checkNums(){
        try{
            if(Ingestion.getHowMany().size() == 9){
                System.out.println();
                for(int i = 0; i < Ingestion.NUM_OF_NUMERICAL_QUESTIONS; i++){
                    System.out.println(Ingestion.getNumQuestions().get(i) + ": " + Ingestion.getHowMany().get(i));
                }
            } else {
                throw new Exception("\nPlease enter in the number of times the activities were entered");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void checkJournalEntry(){
        try {
            System.out.println(Ingestion.getEntry());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private static void checkEntries(){
        try{
            checkBools();
            checkNums();
            checkJournalEntry();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Menu();
        }
        
    }
}
