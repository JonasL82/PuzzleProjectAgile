package account;
import java.util.Scanner;
import random.RandGen;
public class Admin extends Account{

  int id_nr;
  String username, password, email;
  boolean admin;

/*
  public Account login(String username, String password){

  }
*/
  public Admin(String username, String password, String email){
    super(username, password, email);
    this.admin = true;
  }
/*
  public void manageAccount(){
    super.manageAccount();
    System.out.println("3. Return to main menu");
    Scanner sc = new Scanner(System.in);
    byte choice = sc.nextByte();
    switch (choice){
      case 1: //change Password

      case 2: //change Email

      case 3: //list Reviews
        System.out.println("Work in Progress");
        break;
    }
  }

  private void changePassword(){

  }
  private void changeEmail(){

  }
*/
  public int getID(){
    return id_nr;
  }

  public String getUsername(){
    return super.getUsername();
  }

  public String getPassword(){
    return super.getPassword();
  }

  public String getEmail(){
    return super.getEmail();
  }
  public boolean adminStatus(){
    return admin;
  }
  @Override
  public String toString(){
    return super.toString() + "\n" + "Admin status: Yes";
  }
  @Override
  public boolean equals(Object obj){
    return super.equals(obj);
  }
  /*
  private String enterAccountPassword(){
    super.enterAccountPassword();
  }
  */
}
