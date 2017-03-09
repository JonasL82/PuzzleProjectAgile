package tests;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintToFileTest{
  public static void main(String[] args) {
    try {
      PrintStream print_to_file = new PrintStream(new FileOutputStream(
          "TestAccounts.txt"));
          print_to_file.println("§§§");
          for (int i = 0; i < 10; i++){
            print_to_file.println(i);
          }
          print_to_file.println("§§§");
      print_to_file.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void loadFile(){

  }
}
