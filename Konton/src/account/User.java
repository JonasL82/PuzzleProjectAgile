package account;
public class User extends Account{

  int id_nr;
  String username, password, email;

/*
  public Account login(){

  }
*/
  private User(String username, String password, String email){
    super(username, password, email);
  }

  public void manageAccount(){

  }

  private void changePassword(){

  }

  private void changeEmail(){

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
}
