package account;

import java.util.Scanner;

public class Account{

  int id_nr;
  String username, password, email;
  //Account login();
  protected Account(String username, String password, String email){
    this.username = username;
    this.password = password;
    this.email = email;
  }
  //Från Account START
  public Account createAccount(){
    Scanner sc = new Scanner(System.in);
    String email, username, password;
    System.out.println("Enter your username: ");
    username = sc.nextLine();
    System.out.println("Enter your email: ");
    email = sc.nextLine(); //Lägg till check för om addressen är rätt formaterad
    password = "";
    do{
      enterAccountPassword();
    }while (password == "");
    Account newAccount = new Account(username, password, email);
    sc.close();
    return newAccount;
  }

  private String enterAccountPassword(){
      Scanner sc = new Scanner(System.in);
      String firstPW, secondPW;
      System.out.println("Enter your password: ");
      firstPW = sc.nextLine();
      System.out.println("Enter your password again for confirmation: ");
      secondPW = sc.nextLine();
      if (firstPW == secondPW){
        sc.close();
        return firstPW;
      }
      else{
        sc.reset();
        System.out.println("Passwords do not match. Please re-enter");
        enterAccountPassword();
      }
      return "";
  }
  //Från Account SLUT

  private void manageAccount(){

  }

  private int getID(){
    return id_nr;
  }

  private String getUsername(){
    return username;
  }

  private String getPassword(){
    return password;
  }

  private String getEmail(){
    return email;
  }
  @Override
  public String toString(){
    return "Username: " + getUsername() + "\n" +
          "Email: " + getEmail() + "\n" +
          "Password: " + getPassword();
  }
}
