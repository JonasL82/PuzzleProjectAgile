package account;
import content.Movies;
import people.Actors;
import java.util.ArrayList;
import review.Review;
import random.RandGen;
import java.util.Scanner;

public class Account{

  int id_nr;
  String username, password, email;
  boolean admin_status;
  ArrayList<Review> reviews;
  //Account login();
  public Account(String username, String password, String email){ //För standard-kontot
    this.username = username;
    this.password = password;
    this.email = email;
    //id_nr = RandGen.RandNum();
  }
  public Account(String username, String password, String email, boolean admin_status){ //För "riktiga" konton
    this.username = username;
    this.password = password;
    this.email = email;
    this.admin_status = admin_status;
    ArrayList<Review> reviews = new ArrayList<Review>();
    id_nr = RandGen.RandNum();
  }
  /*
  public Account(String username, String password, String email,
                  boolean admin_status, int id_nr, ArrayList<Review> reviews){
    this.username = username;
    this.password = password;
    this.email = email;
    this.admin_status = admin_status;
    this.reviews = reviews;
    this.id_nr = id_nr;
  }
  */
  /*
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
  //UPPREPAD I TestApplication; VÄLJ EN OCH TA BORT DEN ANDRA

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
  */
  //Från Account SLUT

  public int getID(){
    return id_nr;
  }

  public String getUsername(){
    return username;
  }

  public String getPassword(){
    return password;
  }

  public String getEmail(){
    return email;
  }

  public boolean getAdminStatus(){
    return admin_status;
  }

  public void changePassword(String new_password){
    this.password = new_password;
  }

  public void changeEmail(String new_email){
    this.email = new_email;
  }

  public Movies addMovie(){
    String title, genre, language, plot, director, scriptwriter, release_dates;
    byte age_limit;
    short year;
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter a title:");
    title = sc.nextLine();
    System.out.println("Please choose a genre:");
    genre = sc.nextLine();
    System.out.println("Please choose a language:");
    language = sc.nextLine();
    System.out.println("Please provide a brief plot summary:");
    plot = sc.nextLine();
    System.out.println("Please enter the name of the director:");
    director = sc.nextLine();
    System.out.println("Please enter the name of the screenwriter:");
    scriptwriter = sc.nextLine();
    //cast = sc.next;
    System.out.println("Please enter a release date (YYYY-MM-DD):");
    release_dates = sc.nextLine();
    System.out.println("Please enter the movie's age limit:");
    age_limit = sc.nextByte();
    System.out.println("Please enter the movie's production year:");
    year = sc.nextShort();
    Movies m = new Movies(title, genre, language, plot, director, scriptwriter, release_dates, age_limit, year);
    return m;
    /*
    (String title,String genre,String language,String plot,
                  String director,String scriptwriter,String release_dates,
                  byte age_limit,short year)
    */
  }

  public ArrayList<Review> getReviews(){
    return reviews;
  }

  public Review createReview(int user_id, Movies movie_to_review){
    Scanner sc = new Scanner(System.in);
    String review_text;
    byte grade;
    //boolean length_OK = false;
    do{
      System.out.println("Enter your review text (max 240 characters):");
      review_text = sc.nextLine();
    }while (review_text.length()>240 && review_text == "" && review_text == null);
    /*
    while (length_OK == false){
      if (review_text.length()<=240){ //DO WHILE review_text.length()>=241
        length_OK = true;
      }
      else {
        review_text = enterReviewText();
      }
    }
    */
    do{
      System.out.println("Enter your grade (1-5)");
      grade = sc.nextByte();
    }while (grade>5 && grade<1);

    Review review = new Review(review_text, grade, user_id, username, movie_to_review.id_nr());
    return review;
  }

  private String listGenres(){
    String ac = "Action";
    String dr = "Drama";
    String co = "Comedy";
    String ho = "Horror";
    String ro = "Romance";
    String sf = "Science Fiction";
    String th = "Thriller";
    String my = "Mystery";
    String cr = "Crime";
    ArrayList<String> genres = new ArrayList<String>();
    genres.add(ac); genres.add(dr); genres.add(co); genres.add(ho);
    genres.add(ro); genres.add(sf); genres.add(th); genres.add(my);
    genres.add(cr);
    System.out.println("Select genres to add:");
    for (int i = 1; i<=genres.size()-1; i++){
      System.out.println(i + ". " + genres.get(i));
    }
    return ""; //ÄNDRA SEDAN
  }

  public Actors addActor(){
    String name, birthplace, date_of_birth;
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter the actor's name");
    name = sc.nextLine();
    System.out.println("Please enter the actor's date of birth (YYYY-MM-DD):");
    date_of_birth = sc.nextLine();
    System.out.println("Please enter the actor's place of birth:");
    birthplace = sc.nextLine();
    Actors a = new Actors(name, birthplace, date_of_birth);
    return a;
  }
  @Override
  public String toString(){
    return "Username: " + getUsername() + "\n" +
          (getID()>0? "Email: " + getEmail() + "\n" +
          "Password: " + getPassword() + "\n" + "ID: " + getID() : "");
  }

  @Override
  public boolean equals(Object obj){
    if ((obj instanceof Account) && (obj != null)){
      Account other_account = (Account)obj;
      if (this.username==other_account.getUsername() && this.password==other_account.getPassword()
          && this.email==other_account.getEmail() && this.id_nr==other_account.getID()){
            return true;
          }
      else return false;
    }
    else return false;
  }
}
