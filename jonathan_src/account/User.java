package account;
import java.util.Scanner;
import random.RandGen;
public class User extends Account{

  int id_nr;
  String username, password, email;
  boolean admin;

/*
  public Account login(){

  }
*/
  public User(String username, String password, String email){
    super(username, password, email);
    this.admin = false;
  }
  /*
  public void manageAccount(){
    super.manageAccount();
    System.out.println("3. See my reviews");
    System.out.println("4. Return to main menu");
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
    return super.toString() + "\n" + "Admin status: No";
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
