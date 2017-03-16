package account;
import java.util.ArrayList;
import java.util.Scanner;
import content.*;
import people.*;
import util.data_utils.DataCheckUtils;
public class TestApplication {

  ArrayList<Account> accounts;
  public final String[] authorization_codes = {"ABCDEFG", "ZYXWVU", "A1B2C3D4"};
  public TestApplication(){
    accounts = new ArrayList<Account>();
  }

  /*  public void runApplication(){
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
    }*/



  //Från Account START
  public Account createAccount(){
    Scanner sc = new Scanner(System.in);
    String email, username, password;
    System.out.println("Enter your username: ");
    //if (sc.hasNext()){
    boolean username_exists = true;
    int us_found = 0;
    do {
      username_exists = false;
      username = sc.nextLine();
      for (int i = 0; i<accounts.size(); i++){
        if (username.equals(accounts.get(i).getUsername())){
          username_exists = true;
          us_found++;
          if(us_found>0){
            System.out.println("Username already exists. Please enter a new one");
          }
        }
      }
    } while (username_exists == true);

    //}
    //else username = "Username fail";
    System.out.println("Enter your email: ");
/*
    do{
      email = sc.next();
      //checkEmail(email);
    }while (checkEmail(email)==false);
    */
    //if (sc.hasNext()){
      email = sc.nextLine(); //Lägg till check för om addressen är rätt formaterad
    //}
    //else email = "Email fail";
    //password = ""; //Se slutet av enterAccountPassword
    //do{
      password = enterAccountPassword();
    //}while (password == "");
    //ÄNDRA TILL BARA SKAPA ACCOUNT
    Account preAccount = new Account(username, password, email); //Behövs till inställning av adminstatus
    Account newAccount = null;
    int createAccountChoice = 0;
    String attempted_authorization_code;
    boolean found_authorization_code = false;
    boolean accountFinished = false;
    do{
      System.out.println("What kind of account?" + "\n" + "1. User" + "\n" + "2. Admin"
                           + "\n" + "3. Cancel");
      createAccountChoice = sc.nextInt();
      switch (createAccountChoice){
        case 1: Account newUser = new Account(preAccount.getUsername(), preAccount.getPassword(), //Admin
                                    preAccount.getEmail(), false);
                newAccount = newUser;
                return newAccount;
        case 2: System.out.println("Enter authorization code:"); //User
                attempted_authorization_code = sc.nextLine();
                for (int i = 0; i < authorization_codes.length; i++){ //Lägg till begränsat antal försök
                  if (authorization_codes[i].equals(attempted_authorization_code)){
                    found_authorization_code = true;
                  }
                }
                if (found_authorization_code){
                  Account newAdmin = new Account(preAccount.getUsername(), preAccount.getPassword(),
                                              preAccount.getEmail(), true);
                  newAccount = newAdmin;
                  return newAccount;
                }
                else System.out.println("Incorrect authorization code");
                break;
        case 3: System.out.println("Returning to main menu"); //Cancel
                accountFinished = true;
                break;
        default: System.out.println("Invalid entry");
                 break;
      }
      //sc.close();
    }while(accountFinished = false);
    return newAccount; //Det slutgiltiga kontot som returneras
  }

  //UNDANLAGD TILLS VIDARE
  private String enterAccountEmail(){
    Scanner sc = new Scanner(System.in);
    String email;
    boolean checked;
    email = sc.next();
    return email;
    /*
    checked = checkEmail(email);
    if (checked == true){
      return email;
    }
    else {
      System.out.println("Invalid email. Please re-enter:" + "\n");
      enterAccountEmail();
    }
    return "enterAccountEmail FAIL";
    */
  }
  //UPPREPAD I Account; VÄLJ EN OCH TA BORT DEN ANDRA
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
      return ""; //Kan vara anledningen till att intryckningen upprepas en gång för mycket vid felskrevning
  }
  //Från Account SLUT

  public Account logIn(Account active_account){
    //accounts
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the username you want to log in to:");
    String attempted_username = sc.nextLine();
    boolean found_username = false;
    Account dummy = null; //Försök till pekare ("pointer"); ej säkert att det fungerar
    //ERSÄTT SÖKNING MED EN MAP DÄR USERNAME ÄR KEY OCH VALUE ÄR ACCOUNT
      for (int i = 0; i<accounts.size(); i++){
        if (accounts.get(i).getUsername().equals(attempted_username)){ //Hittar ej inlagt användarnamn
          dummy = accounts.get(i);
          //Skrivs inte ut
          //System.out.println("Debug: username found: " + accounts.get(i) + " VS " + attempted_username);
          found_username = true;
        }
      }
      //System.out.println("Debug: found_username: " + found_username);
      if (found_username){
        System.out.println("Please enter your password:");
        int attempts = 0;
        do{
          String attempt_password = sc.nextLine();
          if (attempt_password.equals(dummy.getPassword())){
            return dummy;
          }
          attempts++;
          if (attempts>0){
            System.out.println((3 - attempts) + " attempts left");
          }
        }while (attempts<3);
      }
      System.out.println("Failed to log in.");
      return active_account;
  }

  public void listAccounts(){
    if (accounts.size()>0){
    int i = 1;
    for (Account a : accounts){
      System.out.println("Account " + i + ":" + "\n" + a + "\n");
      i++;
      }
    }
    else System.out.println("No accounts stored");
  }

  public Account manageAccount(Account active_account, boolean logged_in){
    Scanner sc = new Scanner(System.in);
    if (logged_in){
      boolean running_account_management = true;
      do{
        System.out.println("Please choose an action:");
        System.out.println("You are logged in as: " + active_account.getUsername());
        System.out.println("1. Change your password");
        System.out.println("2. Change your email");
        System.out.println("3. Cancel");
        int manageAccountChoice = sc.nextInt();
        Scanner sc2 = new Scanner(System.in);

        switch (manageAccountChoice){
          case 1:
            System.out.println("Please enter your current password:");
            String compare_password = sc2.nextLine();
            if(compare_password.equals(active_account.getPassword())){
              System.out.println("Enter your new password");
              String new_password = sc2.nextLine();
              active_account.changePassword(new_password);
              for(int i = 0; i<accounts.size(); i++){
                if (accounts.get(i).getID()==active_account.getID()){
                  accounts.set(i, active_account);
                }
              }
            }
            else{
              System.out.println("Incorrect password");
            }
            break;
          case 2:
            System.out.println("Please enter your new email adress:");
            String new_email = sc2.nextLine();
            boolean email_not_null = true;
            boolean email_not_blank = true;
            if (new_email.equals(null)){
              email_not_null = false;
            }
            if (new_email.equals("")){
              email_not_blank = false;
            }
            if(email_not_blank==true && email_not_null==true){
              active_account.changeEmail(new_email);
              System.out.println("Email changed to " + new_email);
            }
            else{
              System.out.println("Invalid email entry");
            }
            for(int i = 0; i<accounts.size(); i++){
              if (accounts.get(i).getID()==active_account.getID()){
                accounts.set(i, active_account);
              }
            }
            break;
          case 3:
            System.out.println("Cancelling account management");
            running_account_management = false;
            return active_account;
          default:
            System.out.println("Invalid choice");
            break;
        }
      }while(running_account_management);
  }
  else System.out.println("You are currently not logged in");
  return active_account;
}

  private boolean checkEmail(String email){
    boolean checkAt, checkDot;
    //START checkAt (kollar @)
    if (!email.contains("@")){
      return false;
    }

    System.out.println("debug: finns @:" + email);
    if (email.charAt(0) != '@' && email.charAt(email.length()-1) != '@'){
        ;
    } else {
      System.out.println("debug: uh oh");
      return false;
    }
    //SLUT checkAt
    //START checkDot (kollar punktens placering)
    if (email.contains(".")){
      System.out.println("debug: finns .");
      System.out.println("debug: .:" +email.charAt(0) );
      System.out.println("debug: .:" + email.indexOf('@') );
      System.out.println("debug: .:" +  email.lastIndexOf('.'));
      if (email.charAt(0) != '.' && email.indexOf('@')<email.lastIndexOf('.')){
        System.out.println("debug: TRUE");
        return true;
      }
    }
    System.out.println("debug: FALSE");

    return false;
  }

  public void addCreatedAccount(Account account){
    accounts.add(account);
  }

  public Movies editMovie(Movies edit_movie, ArrayList<Actors> actors){
    Scanner sc2 = new Scanner(System.in);
    System.out.println(edit_movie);
    System.out.println("What would you like to edit?");
    boolean running_movie_edit = true;
    int movie_edit_choice;

    /*
    (String title, String genre, String language, String plot,
                  String director, String scriptwriter, String release_dates,
                  byte age_limit, short year)
                  */
    do{
      System.out.println("Select an action");
      System.out.println("1. Change the title");
      System.out.println("2. Change the production year");
      System.out.println("3. Change the genre");
      System.out.println("4. Add an actor to the cast");
      System.out.println("5. Remove an actor from the cast");
      System.out.println("6. Change the director");
      System.out.println("7. Change the scriptwriter");
      System.out.println("8. Change the plot"); //System.out.println("8. Add Actor plot");
      System.out.println("9. Change the release date");
      System.out.println("10. Change the age limit");
      System.out.println("11. Cancel");
      movie_edit_choice = sc2.nextInt();
      switch (movie_edit_choice) {
        case 1: //Title
          System.out.println("Current title: " + edit_movie.title());
          System.out.print("New title: ");
          String new_title;
          int new_title_wrong = 0;
          do {
            if (new_title_wrong>0) {
              System.out.println("Invalid title. Please re-enter");
            }
            new_title = sc2.nextLine();
            new_title_wrong++;
          } while (new_title.equals("") || new_title.equals(null));

          edit_movie.changeTitle(new_title);
          System.out.println("Title changed.");
          break;
        case 2: //Year
          System.out.println("Current year: " + edit_movie.year());
          System.out.print("New year: ");
          boolean valid_prodyear = false;
          String new_year_string;
          int new_year_int = 0;
          int new_year_miss = 0;
          do {
            Scanner sc3 = new Scanner(System.in);
            if (new_year_int<1878 && new_year_miss>0) {
              System.out.println("There was a problem with your entry. Please re-enter");
            }
            new_year_miss++;
            new_year_string=sc3.nextLine();
            valid_prodyear = DataCheckUtils.checkProdyearForShort(new_year_string);
            if (valid_prodyear==true){
              try {
                new_year_int = Integer.parseInt(new_year_string);
              }catch (NumberFormatException nfe) {
                new_year_int = 0;
              }
            }
            else{
              new_year_int = 0;
            }
          } while (new_year_int<1878 && valid_prodyear == false);
          edit_movie.changeYear(new_year_int);
          System.out.println("Year changed.");
          break;
        case 3: //Genre
          System.out.println("Current genre: " + edit_movie.genre());
          System.out.print("New genre: ");
          String new_genre;
          do {
            Scanner sc3 = new Scanner(System.in);
            new_genre = sc3.nextLine();
          } while (new_genre.equals("") || new_genre.equals(null));
          edit_movie.changeGenre(new_genre);
          System.out.println("Genre changed.");
          break;
        case 4: //Add Cast Member
          System.out.println("Choose which actor to add");
          for (int i = 0; i < actors.size(); i++){
            System.out.println(actors.get(i).name() + ". ID: " + actors.get(i).id_nr());
          }
          System.out.println("Enter ID of actor to add to the cast");
          int add_id_castmember;
          do {
            add_id_castmember = sc2.nextInt();
            if(add_id_castmember<10000 || add_id_castmember>99999){
              System.out.println("Invalid ID number");
            }
          } while (add_id_castmember<10000 && add_id_castmember>99999);
          boolean found_cast_actor;
          for (int j = 0; j < actors.size(); j++){
            if (actors.get(j).id_nr() == add_id_castmember){
              found_cast_actor = true;
              edit_movie.addActorToCast(actors.get(j));
              System.out.println("Actor added to cast");
            }
          }
          break;

        case 5: //Remove Cast Member
          if (edit_movie.cast().size()>0) {
            for (int i = 0; i<edit_movie.cast().size(); i++){
              System.out.println(edit_movie.cast().get(i).name()+", DOB: " +
              edit_movie.cast().get(i).date_of_birth() + ", ID: " + edit_movie.cast().get(i).id_nr());
            }
            System.out.println("Enter the ID of the cast member to be removed");
            boolean found_castmember_remove = false;
            int remove_id_castmember;
            do {
              remove_id_castmember = sc2.nextInt();
              if(remove_id_castmember<10000 || remove_id_castmember>99999){
                System.out.println("Invalid ID number");
              }
            } while (remove_id_castmember<10000 && remove_id_castmember>99999);
            for (int j = 0; j < edit_movie.cast().size(); j++){
              if (edit_movie.cast().get(j).id_nr() == remove_id_castmember){
                found_castmember_remove = true;
                edit_movie.cast().remove(j);
              }
            }
          }
          else {
            System.out.println("The cast is currently empty");
          }
          break;
        case 6: //Change director
          System.out.println("Current director: " + edit_movie.director());
          System.out.print("New director: ");
          String new_director = "";
          do {
            Scanner sc3 = new Scanner(System.in);
            new_director = sc3.nextLine();
          } while (new_director.equals("") || new_director.equals(null));
          edit_movie.changeDirector(new_director);
          System.out.println("Director changed.");
          break;
        case 7: //Change scriptwriter
          System.out.println("Current scriptwriter: " + edit_movie.scriptwriter());
          System.out.print("New screenwriter: ");
          String new_scriptwriter = "";
          do {
            Scanner sc3 = new Scanner(System.in);
            new_scriptwriter = sc3.nextLine();
          } while (new_scriptwriter.equals("") || new_scriptwriter.equals(null));
          edit_movie.changeScriptwriter(new_scriptwriter);
          System.out.println("Screenwriter changed.");
          break;
        case 8: //Change plot
          System.out.println("Current plot: " + "\n" + edit_movie.plot());
          System.out.print("New plot: ");
          String new_plot = "";
          do{
            Scanner sc3 = new Scanner(System.in);
            new_plot = sc3.nextLine();
          }while(new_plot.length()>240 || new_plot.length()<1);
          edit_movie.changePlot(new_plot);
          System.out.println("Plot changed.");
          break;
        case 9: //Change release date
          System.out.println("Current release date: " + edit_movie.release_dates());
          System.out.print("New release date: ");
          String new_release = "";
          boolean new_release_ready = false;
          do {
            Scanner sc3 = new Scanner(System.in);
            new_release = sc3.nextLine();
            new_release_ready = DataCheckUtils.checkDateEntry(new_release);
          } while (new_release_ready == false);
          edit_movie.changeReleaseDate(new_release);
          System.out.println("Release date changed.");
          break;
        case 10: //Change age limit
          System.out.println("Current age limit: " + edit_movie.age_limit());
          System.out.print("New age limit: ");
          int new_age_limit;
          do {
            Scanner sc3 = new Scanner(System.in);
            new_age_limit = sc3.nextInt();
          } while (new_age_limit>90 && new_age_limit<0);
          edit_movie.changeAgeLimit(new_age_limit);
          System.out.println("Age limit changed.");
          break;
        case 11: //Cancel
          System.out.println("Returning to main menu");
          running_movie_edit = false;
          break;
        default:
          System.out.println("Invalid entry");
          break;
      }
    } while (running_movie_edit);
    return edit_movie;
  }

  public Actors editActor(Actors edit_actor){
    Scanner sc2 = new Scanner(System.in);
    System.out.println(edit_actor);
    System.out.println("What would you like to edit?");
    boolean running_actor_edit = true;
    int actor_edit_choice;
    do {
      System.out.println("Select an action");
      System.out.println("1. Change the actor's name");
      System.out.println("2. Change the actor's date of birth");
      System.out.println("3. Change the actor's place of birth");
      System.out.println("4. Cancel");
      actor_edit_choice = sc2.nextInt();
      switch (actor_edit_choice){
        case 1: //Change name
          System.out.println("Current name: " + edit_actor.name());
          System.out.print("Enter a new name: ");
          String new_name;
          do {
            new_name = sc2.nextLine();
            if (new_name.equals(null) || new_name.equals("")){
              System.out.println("Invalid entry");
            }
          } while (new_name.equals(null) || new_name.equals(""));
          edit_actor.changeName(new_name);
          break;
        case 2: //Change date of birth
          System.out.println("Current date of birth: " + edit_actor.date_of_birth());
          System.out.print("Enter a new date of birth: ");
          String new_date_of_birth;
          do {
            new_date_of_birth = sc2.nextLine();
            if (new_date_of_birth.equals(null) || new_date_of_birth.equals("")){
              System.out.println("Invalid entry");
            }
          } while (new_date_of_birth.equals(null) || new_date_of_birth.equals(""));
          edit_actor.changeDateOfBirth(new_date_of_birth);
          break;
        case 3: //Change place of birth
          System.out.println("Current place of birth: " + edit_actor.birthplace());
          System.out.print("Enter a new place of birth: ");
          String new_place_of_birth;
          do {
            new_place_of_birth = sc2.nextLine();
            if (new_place_of_birth.equals(null) || new_place_of_birth.equals("")){
              System.out.println("Invalid entry");
            }
          } while (new_place_of_birth.equals(null) || new_place_of_birth.equals(""));
          edit_actor.changeBirthplace(new_place_of_birth);
          break;
        case 4: //Cancel
          System.out.println("Returning to main menu");
          running_actor_edit = false;
          break;
        default:
          System.out.println("Invalid entry");
          break;
      }
    } while (running_actor_edit);
    return edit_actor;
  }

}
