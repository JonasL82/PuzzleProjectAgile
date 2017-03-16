package util.data_utils;
import java.awt.*;
import java.util.ArrayList;

public class DataCheckUtils{

  static char[] characters_not_allowed_dates = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k','l', 'm', 'n', 'o', 'p',
                                                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
                                                'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                                                'T', 'U', 'V', 'X', 'Y', 'Z', '!', '"', '@', '#', '$', '%', '&',
                                                '/', '{', '}', '(', ')', '[', ']', '=', '?', '+'};
                                                //Kunde inte tas med: å, ä, ö, Å, Ä, Ö

  private DataCheckUtils(){
    //Ska inte kunna skapas; alla metoder ska vara statiska
  }

  public static boolean checkEntryBasic(String test_string){
    if (test_string.equals("") || test_string.equals(null)){
      return false;
    }

    int illegal_characters = 0;
    for (int i = 0; i<test_string.length(); i++) {
      if (test_string.charAt(i)=='1' || test_string.charAt(i)=='2' || test_string.charAt(i)=='3' ||
        test_string.charAt(i)=='4' || test_string.charAt(i)=='5' || test_string.charAt(i)=='6' ||
        test_string.charAt(i)=='7' || test_string.charAt(i)=='8' || test_string.charAt(i)=='9' ||
        test_string.charAt(i)=='0' || test_string.charAt(i)=='@' || test_string.charAt(i)=='#'  ||
        test_string.charAt(i)=='$' || test_string.charAt(i)=='%' ||
        test_string.charAt(i)=='&' || test_string.charAt(i)=='/' || test_string.charAt(i)=='{' ||
        test_string.charAt(i)=='}' || test_string.charAt(i)=='(' || test_string.charAt(i)==')' ||
        test_string.charAt(i)=='[' || test_string.charAt(i)==']' || test_string.charAt(i)=='='){

          illegal_characters++;
        }
    }
    if (illegal_characters>(test_string.length()/3)){
      return false;
    }

    return true;
  }

  public static boolean checkEmail(String test_string){
    return false;
  }

  public static boolean checkProdyearForShort(String prodyear_string){
    int defects = 0;
    for (int i = 0; i<prodyear_string.length(); i++) {
      //Går inte att ta med ¤
      if (prodyear_string.charAt(i) == 'a' || prodyear_string.charAt(i) == 'b' || prodyear_string.charAt(i) == 'c' ||
      prodyear_string.charAt(i) == 'd' || prodyear_string.charAt(i) == 'e' || prodyear_string.charAt(i) == 'f' ||
      prodyear_string.charAt(i) == 'g' || prodyear_string.charAt(i) == 'h' || prodyear_string.charAt(i) == 'i' ||
      prodyear_string.charAt(i) == 'j' || prodyear_string.charAt(i) == 'k' || prodyear_string.charAt(i) == 'l' ||
      prodyear_string.charAt(i) == 'm' ||
      prodyear_string.charAt(i) == 'n' || prodyear_string.charAt(i) == 'o' || prodyear_string.charAt(i) == 'p' ||
      prodyear_string.charAt(i) == 'q' || prodyear_string.charAt(i) == 'r' || prodyear_string.charAt(i) == 's' ||
      prodyear_string.charAt(i) == 't' || prodyear_string.charAt(i) == 'u' || prodyear_string.charAt(i) == 'v' ||
      prodyear_string.charAt(i) == 'x' || prodyear_string.charAt(i) == 'y' || prodyear_string.charAt(i) == 'z' ||
      prodyear_string.charAt(i) == 'A' || prodyear_string.charAt(i) == 'B' || prodyear_string.charAt(i) == 'C' ||
      prodyear_string.charAt(i) == 'D' || prodyear_string.charAt(i) == 'E' || prodyear_string.charAt(i) == 'F' ||
      prodyear_string.charAt(i) == 'G' || prodyear_string.charAt(i) == 'H' || prodyear_string.charAt(i) == 'I' ||
      prodyear_string.charAt(i) == 'J' || prodyear_string.charAt(i) == 'K' || prodyear_string.charAt(i) == 'L' ||
      prodyear_string.charAt(i) == 'M' || prodyear_string.charAt(i) == 'N' || prodyear_string.charAt(i) == 'O' ||
      prodyear_string.charAt(i) == 'P' || prodyear_string.charAt(i) == 'Q' || prodyear_string.charAt(i) == 'R' ||
      prodyear_string.charAt(i) == 'S' || prodyear_string.charAt(i) == 'T' || prodyear_string.charAt(i) == 'U' ||
      prodyear_string.charAt(i) == 'V' || prodyear_string.charAt(i) == 'X' || prodyear_string.charAt(i) == 'Z'
      || prodyear_string.charAt(i) == '#' ||  prodyear_string.charAt(i) == '$'
      || prodyear_string.charAt(i) == '%' || prodyear_string.charAt(i) == '&'
      || prodyear_string.charAt(i) == '/' || prodyear_string.charAt(i) == '{' || prodyear_string.charAt(i) == '}'
      || prodyear_string.charAt(i)=='(' || prodyear_string.charAt(i)==')' || prodyear_string.charAt(i)=='['
      || prodyear_string.charAt(i)==']' || prodyear_string.charAt(i)=='='){
        defects++;
      }
    }
    if (defects!=0){
      return false;
    }
    return true;
  }

  public static boolean checkDateEntry(String date){
    if (date.length()!=10) {
      return false;
    }
    if ((date.charAt(4)==('-'))==false) {
      return false;
    }
    if ((date.charAt(7)=='-')==false) {
      return false;
    }
    boolean numbers_correct = true;
    for (int i = 0; i<date.length(); i++) {
      for (int j = 0; j<characters_not_allowed_dates.length; j++) {
        if (date.charAt(i)==(characters_not_allowed_dates[j])){
          numbers_correct = false;
        }
      }
    }
    return numbers_correct;
  }
}
