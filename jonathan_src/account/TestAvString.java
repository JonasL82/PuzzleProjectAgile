package account;

public class TestAvString {
  public static void main(String[] args) {
    String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String test2 = "AAABBBCCCDDDEEEFFF";
    for (int i = 0; i<=10; i++){
      System.out.println(test.charAt(i));
    }
    /*
    System.out.println(test.indexOf('A')<test.indexOf('Z'));
    System.out.println(test.indexOf('J')<test.indexOf('K'));
    System.out.println(test.indexOf('Z')<test.indexOf('A'));
    */
    System.out.println(test.lastIndexOf('E'));
  }
}
