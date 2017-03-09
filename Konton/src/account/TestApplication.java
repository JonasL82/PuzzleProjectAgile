package account;
import java.util.ArrayList;
import java.util.Scanner;

public class TestApplication {

  ArrayList<Account> accounts;

  public TestApplication(){
    accounts = new ArrayList<Account>();
  }

  public void runApplication(){
    boolean running = true;
    Scanner sc = new Scanner(System.in);
    while (running){
      System.out.println("Testing the account function");
      System.out.println("1. Create Account" + "\n" + "2. List Accounts" + "\n"
                          + "3. Quit");
      byte command = sc.nextByte();
      switch (command) {
        case 1: accounts.add(createAccount());
                break;
        case 2: listAccounts();
                break;
        case 3: sc.close();
                System.out.println("Quitting...");
                running = false;
                break;
        default: System.out.println("Invalid entry. Enter new command");
      }
    }
  }

  //Från Account START
  public Account createAccount(){
    Scanner sc = new Scanner(System.in);
    String email, username, password;
    System.out.println("Enter your username: ");
    //if (sc.hasNext()){
      username = sc.next();
    //}
    //else username = "Username fail";
    System.out.println("Enter your email: ");
    //if (sc.hasNext()){
      email = sc.next(); //Lägg till check för om addressen är rätt formaterad
    //}
    //else email = "Email fail";
    password = "";
    do{
      password = enterAccountPassword();
    }while (password == "");
    Account newAccount = new Account(username, password, email);
    //sc.close();
    return newAccount;
  }

  private String enterAccountPassword(){
      Scanner sc = new Scanner(System.in);
      String firstPW, secondPW;
      System.out.println("Enter your password: ");
      if (sc.hasNext()){
        firstPW = sc.next();
      }
      else firstPW = "Password fail";
      System.out.println("Enter your password again for confirmation: ");
      if (sc.hasNext()){
        secondPW = sc.next();
      }
      else secondPW = "Password fail";
      if (firstPW.compareTo(secondPW) == 0){
        //sc.close();
        return firstPW;
      }
      else{
        //sc.reset();
        System.out.println("Passwords do not match. Please re-enter");
        enterAccountPassword();
      }
      return "";
  }
  //Från Account SLUT

  public void listAccounts(){
    if (accounts.size()>0){
    int i = 1;
    for (Account a : accounts){
      System.out.println("Account " + i + ":" + "\n" + a + "\n");
      }
    }
    else System.out.println("No accounts stored");
  }
}
