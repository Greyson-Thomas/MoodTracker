package ingest;
/* Metric input order:
 *  Booleans:
 *   Exercise, Grabbed Phone, Went Outside, Ate Meal, Bad Habit
 *   Spoke with Friends, Spoke with Family, Junk Food, Read,
 *   Cleaned, Showered, Brushed Teeth, Felt Emotion, Good Habit
 *   Bowel Movement
 *  Numerical:
 *   Times outside, Unhealthy Meals Ate, Healthy Meals Ate, Number of Meals Enjoyed,
 *   Number of Bad Habits, Number of Good Habits, Number of Showers, Number of times
 *   brushed teeth, Number of Hours Digital Media
 *  Categorical:
 *   Season, Weather
 *  Unstructured:
 *   Journal Entry
 */

public class Main  {
    
    public static void main(String[] args) throws Exception {

        System.out.println("Everyday above ground is a good day! May we see many more");
        System.out.println("Remember to insert some ASCII Art here");

        try{
            int temp = 1;
            do{
                Menu.Menu();
                temp = 2;
            } while(temp == 1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
