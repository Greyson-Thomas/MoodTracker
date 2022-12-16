package ingest;

public class Validation {
    
  /*************************************************
     *              Validation Section
     *************************************************/
    public static int responseValidationBools(String response){
        response = response.trim().toLowerCase();
        if(response.length() == 1 && (response.equals("y") || response.equals("n"))) {
            return (response.equals("y"))? 1 : 0;
        }
        System.out.println("\nPlease enter in the either y or n");
        return -1;
        
    }

    public static int responseValidationNums(String response){
        response = response.trim();
        try {
            int numOfOccurrences = Integer.parseInt(response);
            return numOfOccurrences;
        } catch (Exception e) {
            System.out.println("Please only enter in digits");
            return -1;
        }
    }
}
