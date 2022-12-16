package ingest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ingest.Validation;

public class ValidationTest {
  
  @Test
  public void TestValidationBoolsCorrectInput(){
    int validationCode = Validation.responseValidationBools("y");
    assertEquals(validationCode, 1,"[Test Failed]: 'y' response resulted in incorrect validation code");

    validationCode = Validation.responseValidationBools("n");
    assertEquals(validationCode, 0,"[Test Failed]: 'n' response resulted in incorrect validation code");
  }

  @Test
  public void TestValidationNumsCorrectInput(){
    int validationCode = Validation.responseValidationNums("500");
    assertEquals(validationCode, 500,"[Test Failed]: 500 was not properly parsed to an Integer");

    validationCode = Validation.responseValidationNums("2");
    assertEquals(validationCode, 2,"[Test Failed]: 2 was not properly parsed to an Integer");
  }

  @Test
  public void TestValidationBoolsIncorrectInput(){
    int validationCode = Validation.responseValidationBools("500");
    assertEquals(validationCode, -1, "[Test Failed]: Incorrect validation code received when function receives bad input");

    validationCode = Validation.responseValidationBools("!");
    assertEquals(validationCode, -1, "[Test Failed]: Incorrect validation code received when function receives bad input");

    validationCode = Validation.responseValidationBools("[y]]");
    assertEquals(validationCode, -1, "[Test Failed]: Incorrect validation code received when function receives bad input");

    validationCode = Validation.responseValidationBools("yo-yo");
    assertEquals(validationCode, -1, "[Test Failed]: Incorrect validation code received when function receives bad input");

    validationCode = Validation.responseValidationBools("never");
    assertEquals(validationCode, -1, "[Test Failed]: Incorrect validation code received when function receives bad input");
  }

  @Test
  public void TestValidationNumsIncorrectInput(){
    int validationCode = Validation.responseValidationNums("non-number");
    assertEquals(validationCode, -1, "[Test Failed]: Incorrect validation code received when function receives bad input");

    validationCode = Validation.responseValidationNums("1-num");
    assertEquals(validationCode, -1, "[Test Failed]: Incorrect validation code received when function receives bad input");
  }
}
