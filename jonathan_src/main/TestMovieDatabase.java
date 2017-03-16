package main;
import account.*;
import content.*;
import people.*;
import random.*;
import review.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class TestMovieDatabase{
  public static void main(String[] args) {
    boolean logged_in = false; //Avser inloggningsfunktion
    boolean admin = false; //--""--
    boolean running = true;
    final String SEPARATOR = "------------";
    int choice; //för menyn
    ArrayList<Movies> movies = new ArrayList<>();
    ArrayList<Actors> actors = new ArrayList<Actors>();
    TestApplication ta = new TestApplication();
    Scanner sc = new Scanner(System.in);
    Account start_account = new Account("standard", "!#¤%&/()", "none@email.com");
    ta.addCreatedAccount(start_account);
    Account active_account = start_account;

    //Demonstrationsinstanser
    Account test_admin = new Account("DemoAdmin", "aaaeee", "who@cares.net", true);
    ta.addCreatedAccount(test_admin);
    Account test_user = new Account("DemoUser", "bbbfff", "user@iths.se", false);
    ta.addCreatedAccount(test_user);
    byte standard_age = 11;
    short r1 = 1940; short r2 = 2016;
    Movies mo1 = new Movies("Citizen Kane", "Drama", "English",
     "A journalist searches for the meaning of a dead millionaire's last words",
    "Orson Welles", "Orson Welles", "1941", standard_age, r1);
    Movies mo2 = new Movies("Gods of Egypt", "Fantasy", "English", "Blablabla",
                            "Unknown", "Unknown", "2016", standard_age, r2);
    movies.add(mo1); movies.add(mo2);
    byte review_byte = 2;
    Review test_review = new Review("This movie sucks", review_byte, test_user.getID(),
                                    test_user.getUsername(), mo2.id_nr());
    mo2.addReview(test_review);
    Actors ac1 = new Actors("Orson Welles", "USA", "1915-05-06");
    Actors r_gosling = new Actors("Ryan Gosling", "Canada", "1978/05/01");
    actors.add(r_gosling);
    Actors e_stone = new Actors("Emma Stone", "USA", "1980/06/03");
    actors.add(e_stone);
    active_account = test_admin;

    System.out.println("Welcome to IthsMDB!");
    System.out.println("Product of PUZZLE" + "\n" + "------");
    do{
      if(active_account.getID()!=0){
        logged_in = true;
      }
      if(active_account.getAdminStatus()==true){
        admin = true;
      }
      else {
        admin = false;
      }
      System.out.println("Select an action");
      System.out.println("1. My Account Information");
      System.out.println("2. Create Account");
      System.out.println("3. Log In");
      System.out.println("4. List Movies");
      System.out.println("5. List Actors");
      System.out.println("6. List Accounts");
      System.out.println("7. Add Movie");
      System.out.println("8. Add Actor");
      System.out.println("9. Delete Movie");
      System.out.println("10. Delete Actor");
      System.out.println("11. Review Movie");
      System.out.println("12. Log Out");
      System.out.println("13. Alter Movie Information");
      System.out.println("14. Alter Actor Information");
      System.out.println("15. Exit");
      //KOLLA UPP JAVA KEYEVENT:
      //https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html
      //try{
        choice = sc.nextInt();
      /*}catch(InputMismatchException ime){ //försök till buggfixning; ska göra klart senare
        System.out.println("Invalid entry. Try again");
        choice = sc.nextInt();
      }*/
      switch (choice){
        case 1: //My Account Information
          System.out.println("Active account:" + "\n" + active_account);
          if(active_account.getID()!=0){
            System.out.println("Change the following information?" + "\n" +
                              "1. Yes" + "\n" + "2. No");
            int account_info_choice = sc.nextInt();
            if (account_info_choice == 1){
              active_account = ta.manageAccount(active_account, logged_in);
            }
            else{
              System.out.println("Returning to main menu");
            }
          }
          System.out.println(SEPARATOR);
          break;
        case 2: //Create Account
          if (logged_in == true){
            System.out.println("Already logged in");
          }
          else{
            Account temp = ta.createAccount(); //Objekt skapas som vanliga accounts
            if(temp!=null){
              ta.addCreatedAccount(temp);
            }
            else{
              System.out.println("Account creation cancelled");
            }
          }
        System.out.println(SEPARATOR);
        break;
        case 3: //Log In
          Account login_account = ta.logIn(active_account);
          active_account = login_account;
          admin = active_account.getAdminStatus();
          logged_in = true;
          System.out.println(SEPARATOR);
          break;
        case 4: //List Movies
          System.out.println("Movies:");
          for (int i = 0; i < movies.size(); i++){
            System.out.println(movies.get(i));
            if (movies.get(i).reviews().size()>0){
              System.out.println("Reviews:");
              for (int j = 0; j<movies.get(i).reviews().size(); j++){
                System.out.println(SEPARATOR + "\n" + movies.get(i).reviews().get(j));
              }

            }
            else{
              System.out.println("No reviews" + "\n" + SEPARATOR);
            }
          }
          System.out.println(SEPARATOR);
          break;
        case 5: //List Actors
        System.out.println("Actors:");
        for (int i = 0; i < actors.size(); i++){
          System.out.println(actors.get(i));
        }
          System.out.println(SEPARATOR);
          break;
        case 6: //List Accounts
          ta.listAccounts();
          System.out.println(SEPARATOR);
          break;
        case 7: //Add Movie
          //System.out.println("Work In Progress");
          if(admin == true){
            Movies m = active_account.addMovie();
            movies.add(m);
          }
          else System.out.println("You are not authorized");
          System.out.println(SEPARATOR);
          break;
        case 8: //Add Actor
          //System.out.println("Work In Progress");
          if (admin == true){
            Actors a = active_account.addActor();
            actors.add(a);
          }
          else System.out.println("You are not authorized");
          System.out.println(SEPARATOR);
          break;
        case 9: //Delete Movie
          if (admin == true){
            for (int i = 0; i < movies.size(); i++){
              System.out.println(movies.get(i).title() + " (" + movies.get(i).year() + "). ID: " + movies.get(i).id_nr());
            }
            int remove_id_movie;
            boolean found_movie = false;
            System.out.println("Enter the ID of the movie to remove");
            remove_id_movie = sc.nextInt();
            for (int j = 0; j < movies.size(); j++){
              if (movies.get(j).id_nr() == remove_id_movie){
                found_movie = true;
                movies.remove(j);
              }
            }
            if (found_movie){
              System.out.println("Film removed");
            }
            else{
              System.out.println("No film with that ID");
            }
          }
          else System.out.println("You are not authorized");
          System.out.println(SEPARATOR);
          break;
        case 10: //Delete Actor
          if (admin == true){
            for (int i = 0; i < actors.size(); i++){
              System.out.println(actors.get(i).name() + ". ID: " + actors.get(i).id_nr());
            }
            int remove_id_actor;
            boolean found_actor = false;
            System.out.println("Enter the ID of the actor to remove");
            remove_id_actor = sc.nextInt();
            for (int j = 0; j < actors.size(); j++){
              if (actors.get(j).id_nr() == remove_id_actor){
                found_actor = true;
                actors.remove(j);
              }
            }
            if (found_actor){
              System.out.println("Actor removed");
            }
            else{
              System.out.println("No actor with that ID");
            }
          }
          else System.out.println("You are not authorized");

          System.out.println(SEPARATOR);
          break;
        case 11: //Skriv recension for filmer
        if (admin == false){
          for (int i = 0; i < movies.size(); i++){
            System.out.println(movies.get(i).title() + " (" + movies.get(i).year() + "). ID: " + movies.get(i).id_nr());
          }
          int review_id_movie;
          boolean found_review_movie = false;
          System.out.println("Enter the ID of the movie to review");
          review_id_movie = sc.nextInt();
          for (int j = 0; j < movies.size(); j++){
            if (movies.get(j).id_nr() == review_id_movie){
              found_review_movie = true;
              Review temp_review = active_account.createReview(active_account.getID(), movies.get(j));
              movies.get(j).addReview(temp_review);
            }
          }
          if (found_review_movie){
            System.out.println("Review added");
          }
          else{
            System.out.println("No film with that ID");
          }
        }
        else System.out.println("You are not authorized");

          System.out.println(SEPARATOR);
          break;
        case 12: //Log out
        if (logged_in == true){
          System.out.println("Logging out");
          logged_in = false;
          admin = false;
          active_account = start_account;
        }
        else System.out.println("No account is currently logged in");
          System.out.println(SEPARATOR);
          break;
        case 13: //Ändra film
          System.out.println("Work In Progress");
          if (admin == true){
            for (int i = 0; i < movies.size(); i++){
              System.out.println(movies.get(i).title() + " (" + movies.get(i).year() + "). ID: " + movies.get(i).id_nr());
            }
            int edit_movie_id;
            boolean found_edit_movie = false;
            Movies temp_film = null;
            System.out.println("Enter the ID of the movie you wish to edit");
            edit_movie_id = sc.nextInt();
            for (int j = 0; j<movies.size(); j++) {
              if(movies.get(j).id_nr()==edit_movie_id){
                found_edit_movie = true;
                temp_film = ta.editMovie(movies.get(j), actors); //Problem med kompileringen
                movies.set(j, temp_film);
              }
            }
            if (found_edit_movie) {
              System.out.println("Movie edit complete");
            }
            else {
              System.out.println("No movie with that ID");
            }
          }
          else{
            System.out.println("You are not authorized for this function");
          }
          break;
        case 14: //Ändra skådespelare
          System.out.println("Work In Progress");
          if (admin == true){
            for (int i = 0; i < actors.size(); i++){
              System.out.println(actors.get(i).name() + ". ID: " + actors.get(i).id_nr());
            }
            boolean found_edit_actor = false;
            int edit_actor_id;
            System.out.println("Enter the ID of the actor you wish to edit");
            edit_actor_id = sc.nextInt();
            for (int j = 0; j<actors.size(); j++) {
              if (actors.get(j).id_nr()==edit_actor_id) {
                found_edit_actor = true;
                Actors temp_actor = ta.editActor(actors.get(j));
                actors.set(j, temp_actor);
              }
            }
            if (found_edit_actor) {
              System.out.println("Actor has been edited");
            }
            else {
              System.out.println("No actor with that ID");
            }
          }
          else{
            System.out.println("You are not authorized for this function");
          }
          break;
        case 15:
          System.out.println("Bye-bye!");
          running = false;
          break;
        default:
          System.out.println("Invalid entry");
          System.out.println(SEPARATOR);
          break;
      }
    }while (running);

  }

  /*
  //ANVÄNDS SENARE VID UTSKRIFT AV MENY
  public static String printAccess(Account a){
    if (a instanceof User){
      return " (NOT AUTHORIZED)";
    }
    else return "";
  }
  */
  //(title,genre,language,plot,director,scriptwriter,cast,release_dates,age_limit,year)
/*
  public static Movies addMovie(){
    String title,genre,language,plot,director,scriptwriter,cast,release_dates;
    byte age_limit;
    short year;
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter a title");
    title = sc.next();
    genre = sc.next;
    language = sc.next;
    plot = sc.next;
    director = sc.next;
    scriptwriter = sc.next;
    cast = sc.next;
    release_dates = sc.next;
    age_limit = sc.nextByte();
    year = nextShort();
  }
*/
}
