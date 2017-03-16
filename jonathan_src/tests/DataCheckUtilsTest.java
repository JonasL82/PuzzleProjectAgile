package tests;
import util.data_utils.DataCheckUtils;
public class DataCheckUtilsTest{
  public static void main(String[] args) {
    boolean checkProdyearForShortWorks;
    boolean checkEntryBasicWorks;
    String test_string = "ABCDEFGH";
    String prodyear_string = "1990";
    String other_prodyear_string = "ABCD";
    String other_test_string = "AAA99999";
    String other_test_string_2 = "BBBBB88";
    System.out.println("Debug: checkProdyearForShort good: " + prodyear_string + ": " + DataCheckUtils.checkProdyearForShort(prodyear_string));
    System.out.println("Debug: checkEntryBasic good: " + test_string + ": " + DataCheckUtils.checkEntryBasic(test_string));
    System.out.println("Debug: checkProdyearForShort bad: " + other_prodyear_string + ": " + DataCheckUtils.checkProdyearForShort(other_prodyear_string));
    System.out.println("Debug: checkEntryBasic bad: " + other_test_string + ": " + DataCheckUtils.checkEntryBasic(other_test_string));
    System.out.println("Debug: checkEntryBasic bad 2: " + other_test_string_2 + ": " + DataCheckUtils.checkEntryBasic(other_test_string_2));
    String test_date_good1 = "1990-04-01";
    String test_date_good2 = "1890-01-08";
    String test_date_bad1 = "18000403";
    String test_date_bad2 = "1974/05/01";
    System.out.println("--------" + "\n" + "Test of checkDateEntry:");
    System.out.println("Debug: checkDateEntry good 1: " + test_date_good1 + ": " + DataCheckUtils.checkDateEntry(test_date_good1));
    System.out.println("Debug: checkDateEntry good 2: " + test_date_good2 + ": " + DataCheckUtils.checkDateEntry(test_date_good2));
    System.out.println("Debug: checkDateEntry bad 1: " + test_date_bad1 + ": " + DataCheckUtils.checkDateEntry(test_date_bad1));
    System.out.println("Debug: checkDateEntry bad 2: " + test_date_bad2 + ": " + DataCheckUtils.checkDateEntry(test_date_bad2));
  }
}
