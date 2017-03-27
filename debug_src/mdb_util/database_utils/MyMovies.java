package mdb_util.database_utils;
import java.util.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import content.Movies;
import account.Account;
import review.Review;
import people.Actors;

public class MyMovies implements MovieDB{
  DBUtils db = DBUtils.getInstance();

  public void setAutoCommit(boolean autoCommit){
    db.setAutoCommit(autoCommit);
  }

  public void commit(){
    db.commit();
  }

  public void rollback(){
    db.rollback();
  }
  /**
   * Fetches all municipalities from the storage as a List of Municipality objects.
   * @return All municipalities of the storage as a java.util.List&lt;Municipality&gt;
   */

   /*
   *
   * Metoder för att hämta hela tabeller ur databasen
   *
   */
  public List<Movies> getMovieDatabase(){
    ArrayList<Movies> list = new ArrayList<Movies>();
    ResultSet rs = db.executeQuery("SELECT * FROM movies");
    try{
      Movies m=null;
      while(rs.next()){
      m=new Movies(rs.getString("Title"),
                   rs.getString("Genre"),
                   rs.getString("Language"),
                   rs.getString("Plot"),
                   rs.getString("Director"),
                   rs.getString("Screenwriter"),
                   rs.getString("ReleaseDate"),
                   rs.getString("AgeLimit"),
                   rs.getString("ProductionCompany"),
                   rs.getInt("ProductionYear"),
                   rs.getInt("MovieID"),
                   rs.getInt("CastID"));
      //  m.setID(rs.getInt("MunicipalityID"));
        list.add(m);
      }
      db.closeIt(rs);
      return list;
    }catch(Exception e){
      System.err.println("Getting all Movies: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  public List<Actors> getActorsFromDatabase(){
    ArrayList<Actors> list = new ArrayList<Actors>();
    ResultSet rs = db.executeQuery("SELECT * FROM actors");
    try {
      Actors a = null;
      while (rs.next()) {
        a = new Actors(rs.getString("Name"),
                      rs.getString("Birthplace"),
                      rs.getString("DateOfBirth"),
                      rs.getInt("ActorID"));
        list.add(a);
      }
      db.closeIt(rs);
      return list;
    } catch (Exception e) {
      System.err.println("Getting all Actors: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  public List<Account> getAccountsFromDatabase(){
    ArrayList<Account> list = new ArrayList<Account>();
    ResultSet rs = db.executeQuery("SELECT * FROM accounts");
    try{
      Account a=null;
      while(rs.next()){
      m=new Movies(rs.getString("Username"),
                          rs.getString("Password"),
                           rs.getString("Email"),
                           rs.getInt("AdminStatus"),
                           rs.getInt("AccountID"));
      //  m.setID(rs.getInt("MunicipalityID"));
        list.add(a);
      }
      db.closeIt(rs);
      return list;
    }catch(Exception e){
      System.err.println("Getting all Accounts: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  //String review_text, int grade, int user_id, int movie_id
  public List<Review> getReviewsFromDatabase(){
    ArrayList<Review> list = new ArrayList<Review>();
    ResultSet rs = db.executeQuery("SELECT * FROM reviews");
    try{
      Review r=null;
      while(rs.next()){
      r=new Review(rs.getString("ReviewText"),
                          rs.getInt("Grade"),
                           rs.getInt("AccountID"),
                           rs.getInt("MovieID"),
                           rs.getInt("ReviewID"));
      //  m.setID(rs.getInt("MunicipalityID"));
        list.add(r);
      }
      db.closeIt(rs);
      return list;
    }catch(Exception e){
      System.err.println("Getting all Reviews: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  /*
  *
  * Metoder för att bara hämta ID-nr för tabeller (behövs?)
  */

  public List<Integer> getMovieIdNumbers(){
    ArrayList<Integer> list = new ArrayList<Integer>();
    ResultSet rs = db.executeQuery("SELECT MovieID from movies");
    try {
      while (rs.next()) {
        list.add(rs.getInt("MovieID"));
      }
    }catch (Exception e) {
      System.err.println("Getting movie IDs: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  public List<Integer> getActorIdNumbers(){
    ArrayList<Integer> list = new ArrayList<Integer>();
    ResultSet rs = db.executeQuery("SELECT ActorID from actors");
    try {
      while (rs.next()) {
        list.add(rs.getInt("ActorID"));
      }
      return list;
    }catch (Exception e) {
      System.err.println("Getting actor IDs: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  public List<Integer> getReviewIdNumbers(){
    ArrayList<Integer> list = new ArrayList<Integer>();
    ResultSet rs = db.executeQuery("SELECT ReviewID from reviews");
    try {
      while (rs.next()) {
        list.add(rs.getInt("ReviewID"));
      }
      return list;
    }catch (Exception e) {
      System.err.println("Getting review IDs: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  public List<Integer> getAccountIdNumbers(){
    ArrayList<Integer> list = new ArrayList<Integer>();
    ResultSet rs = db.executeQuery("SELECT AccountID from accounts");
    try {
      while (rs.next()) {
        list.add(rs.getInt("AccountID"));
      }
      return list;
    }catch (Exception e) {
      System.err.println("Getting account IDs: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }
  /*
  *
  * Metoder för att hämta kombinerade värden från tabeller
  */

  //(String review_text, int grade, int user_id, int movie_id, int id_nr)
  public HashMap<Integer, Review> getReviewsForMovie(int movie_id){
    HashMap<Integer, Review> reviews = new HashMap<Integer, Review>();
    ResultSet rs = db.executeQuery("SELECT * FROM reviews WHERE MovieID="+movie_id);
    try {
      int account_id = 0;
      while (rs.next()) {
        account_id=rs.getInt("AccountID");
        Review review = new Review(rs.getString("ReviewText"), rs.getInt("Grade"),
                                    rs.getString("AccountID"), movie_id, rs.getString("ReviewID"));
        reviews.put(account_id, review);
      }
      return reviews;
    }catch (Exception e) {
      System.err.println("Getting reviews for movie: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

  public String getCastForMovie(int id_nr){
    //HashMap<Integer, Actors> cast = new HashMap<Integer, Actors>();
    ResultSet rs = db.executeQuery("SELECT a.Name, c.CharacterName FROM actors a, casts c WHERE c.MovieID="+id_nr);
    try {
      String cast = "";
      while (rs.next()) {
        cast = cast  + rs.getString("Name") + " - " + rs.getString("CharacterName") + "\n";
      }
      return cast;
    }catch (Exception e) {
      System.err.println("Error getting cast for movie from database: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }

   /*
   *
   * Metoder för att uppdatera värden i filmer
   *
   */
   public void updateMovieTitle(Movies m, String new_title){
     int movie_id = m.id_nr();
     String SQL="UPDATE movies SET Title='"+new_title+
       "' WHERE MovieID="+movie_id;
     if (db.executeUpdate(SQL) > 1) {
       System.out.println("Error when changing year.");
       db.rollback();
     }
     else {
       System.out.println("Movie's year changed");
       db.commit();
     }
   }
  public void updateMovieYear(Movies m, int new_year){
    int movie_id = m.id_nr();
    String SQL="'UPDATE movies SET ProductionYear="+new_year+
      " WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing year.");
      db.rollback();
    }
    else {
      System.out.println("Movie's year changed");
      db.commit();
    }
  }
  public void updateMoviePlot(Movies m, String new_plot){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET Plot='"+new_plot+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing plot.");
      db.rollback();
    }
    else {
      System.out.println("Movie's plot changed");
      db.commit();
    }
  }
  public void updateMovieGenre(Movies m, String new_genre){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET Genre='"+new_genre+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing genre.");
      db.rollback();
    }
    else {
      System.out.println("Movie's genre changed");
      db.commit();
    }
  }
  public void updateMovieLanguage(Movies m, String new_language){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET Language='"+new_language+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing language.");
      db.rollback();
    }
    else {
      System.out.println("Movie's language changed");
      db.commit();
    }
  }
  public void updateMovieDirector(Movies m, String new_director){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET Director='"+new_director+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing director.");
      db.rollback();
    }
    else {
      System.out.println("Movie's director changed");
      db.commit();
    }
  }
  public void updateMovieScreenwriter(Movies m, String new_screenwriter){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET Screenwriter='"+new_screenwriter+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing director.");
      db.rollback();
    }
    else {
      System.out.println("Movie's director changed");
      db.commit();
    }
  }
  public void updateMovieReleaseDate(Movies m, String new_release_date){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET ReleaseDate='"+new_release_date+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing release date.");
      db.rollback();
    }
    else {
      System.out.println("Movie's release date changed");
      db.commit();
    }
  }
  public void updateMovieAgeLimit(Movies m, String new_age_limit){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET AgeLimit='"+new_age_limit+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing age limit.");
      db.rollback();
    }
    else {
      System.out.println("Movie's age limit changed");
      db.commit();
    }
  }
  public void updateMovieProductionCompany(Movies m, String new_production_company){
    int movie_id = m.id_nr();
    String SQL="UPDATE movies SET ProductionCompany='"+new_release_date+
      "' WHERE MovieID="+movie_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing production company.");
      db.rollback();
    }
    else {
      System.out.println("Movie's production company changed");
      db.commit();
    }
  }

public void updateMovieAddActorToCast(Movies m, Actors a){
  String SQL = "INSERT INTO ";
}
  /*
  *
  * Metoder för att uppdatera värden hos skådespelare
  *
  */
  public void updateActorName(Actors a, String new_name){
    int actor_id = a.id_nr();
    String SQL="UPDATE actors SET Name='"+new_name+
      "' WHERE ActorID="+actor_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing name");
      db.rollback();
    }
    else {
      System.out.println("Actor's name changed");
      db.commit();
    }
  }
  public void updateActorBirthplace(Actors a, String new_birthplace){
    int actor_id = a.id_nr();
    String SQL="UPDATE actors SET Birthplace='"+new_birthplace+
      "' WHERE ActorID="+actor_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing birthplace");
      db.rollback();
    }
    else {
      System.out.println("Actor's birthplace changed");
      db.commit();
    }
  }
  public void updateActorDateOfBirth(Actors a, String new_date_of_birth){
    int actor_id = a.id_nr();
    String SQL="UPDATE actors SET DateOfBirth='"+new_date_of_birth+
      "' WHERE ActorID="+actor_id;
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when date of birth name");
      db.rollback();
    }
    else {
      System.out.println("Actor's date of birth changed");
      db.commit();
    }
  }
  /*
  *
  * Metoder för att uppdatera värden hos recensioner
  *
  */
  public void updateReviewText(Review r, String new_review_text){
    int review_id = r.id_nr();
    String SQL = "UPDATE reviews SET ReviewText = '"+new_review_text+"' WHERE ReviewID="+review_id+"";
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing review text");
      db.rollback();
    }
    else {
      System.out.println("Review text changed");
      db.commit();
    }
  }
  public void updateReviewGrade(Review r, int new_review_grade){
    int review_id = r.id_nr();
    String SQL = "UPDATE reviews SET Grade = "+new_review_grade+" WHERE ReviewID="+review_id+"";
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing review grade");
      db.rollback();
    }
    else {
      System.out.println("Review grade changed");
      db.commit();
    }
  }
  /*
  *
  * Metoder för att uppdatera värden hos konton
  *
  */
  public void updateAccountUsername(Account a, String new_username){
    int account_id = a.getID();
    String SQL = "UPDATE accounts SET Username='"+new_username+"' WHERE AccountID="+account_id+"";
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing account username");
      db.rollback();
    }
    else {
      System.out.println("Account username changed");
      db.commit();
    }
  }
  public void updateAccountPassword(Account a, String new_password){
    int account_id = a.getID();
    String SQL = "UPDATE accounts SET Password='"+new_password+"' WHERE AccountID="+account_id+"";
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing account password");
      db.rollback();
    }
    else {
      System.out.println("Account password changed");
      db.commit();
    }
  }
  public void updateAccountEmail(Account a, String new_email){
    int account_id = a.getID();
    String SQL = "UPDATE accounts SET Email='"+new_email+"' WHERE AccountID="+account_id+"";
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing account email");
      db.rollback();
    }
    else {
      System.out.println("Account email changed");
      db.commit();
    }
  }
  /*
  *
  * Metoder för att ta bort objekt från databasen
  *
  */
  public void deleteActor(Actors a){
    int id_nr = a.id_nr();
    String SQL = "DELETE FROM actors WHERE ActorID="+id_nr;
    if (db.executeUpdate(SQL) > 1) {
       System.out.println("Error when deleting actor.");
       db.rollback();
      }
   else {
       System.out.println("Actor deleted");
       db.commit();
      }
  }

  public void deleteMovie(Movies m){
    int id_nr = m.id_nr();
    String SQL="DELETE FROM movies WHERE MovieID="+id_nr;
    /*
    System.out.println(db.executeUpdate(SQL) +
    " rows deleted");
    */
      if (db.executeUpdate(SQL) > 1) {
         System.out.println("Error when deleting movie.");
         db.rollback();
        }
     else {
         System.out.println("Movie deleted");
         db.commit();
        }
  }

  public void deleteReview(Review r){
    int id_nr = r.id_nr();
    String SQL="DELETE FROM reviews WHERE ReviewID="+id_nr;
    /*
    System.out.println(db.executeUpdate(SQL) +
    " rows deleted");
    */
      if (db.executeUpdate(SQL) > 1) {
         System.out.println("Error when deleting review.");
         db.rollback();
        }
     else {
         System.out.println("Review deleted");
         db.commit();
        }
  }

  public void deleteAccount(Account a){
    int id_nr = a.getID();
    String SQL="DELETE FROM accounts WHERE AccountID="+id_nr;
    /*
    System.out.println(db.executeUpdate(SQL) +
    " rows deleted");
    */
      if (db.executeUpdate(SQL) > 1) {
         System.out.println("Error when deleting account.");
         db.rollback();
        }
     else {
         System.out.println("Account deleted");
         db.commit();
        }
  }
  /*
  *
  * Metoder för att lägga till objekt i databasen
  *
  */
  public void addMovieToDatabase(Movies m){
    String title = m.title();
    String genre = m.genre();
    String language = m.language();
    String plot = m.plot();
    String director = m.director();
    String scriptwriter = m.scriptwriter();
    String release_dates = m.release_dates();
    String age_limit = m.age_limit();
    String produdction_company = m.prodcompany();
    int year = m.year();
    int id_nr = m.id_nr();
    //int cast_id = m.cast_id();

    String SQL="INSERT INTO movies (Title, ProductionYear, Genre, Language, Director, Screenwriter, ReleaseDate, AgeLimit, ProductionCompany, MovieID) VALUES('"+title+"', "+year+", '"+genre+"', '"+language+"', '"+director+"', '"+scriptwriter+"', '"+release_dates+"', '"+age_limit+"', '"+production_company+"', "+id_nr+")";
    System.out.println(db.executeUpdate(SQL)+
    " rows inserted");

  }

  public void addActorToDatabase(Actors a){
    String name = a.name();
    String birthplace = a.birthplace();
    String date_of_birth = a.date_of_birth();
    int id_nr = a.id_nr();

    String SQL = "INSERT INTO actors (Name, Birthplace, DateOfBirth, ActorID) VALUES('"+name+"', '"+birthplace+"', '"+date_of_birth+"', "+id_nr+")";
    System.out.println(db.executeUpdate(SQL) + " actor inserted");
  }

  public void addAccountToDatabase(Account a){
    String username = a.getUsername();
    String password = a.getPassword();
    String email = a.getEmail();
    int id_nr = a.getID();
    boolean admin_status = a.getAdminStatus();

    String SQL = "INSERT INTO accounts (Username, Password, Email, AdminStatus, AccountID) VALUES ('"+username+"', '"+ password+"', '"+email+"', "+(admin_status?1:0)+", "+id_nr+")";
    System.out.println(db.executeUpdate(SQL) + " account added");
  }

  public void addReviewToDatabase(Review r, Movies m, Account a){
    String review_text = r.review_text();
    int grade = r.grade();
    int review_id = r.id_nr();
    int account_id = a.getID();
    int movie_id = m.id_nr();

    String SQL = "INSERT INTO reviews (MovieID, AccountID, ReviewID, ReviewText, Grade) VALUES("+movie_id+", "+account_id+", "+review_id+", '"+review_text+"', "+grade+")";
    System.out.println(db.executeUpdate(SQL) + " review added");
  }
  /*
  *
  * Metoder för att hämta resultat av sökningar
  *
  */
  public String getSearchResultsForMovieTitle(String title){
    ResultSet rs = db.executeQuery("SELECT Title, ProductionYear, MovieID FROM movies WHERE Title LIKE '"+title+"'");
    try{
      String results = "";
      while (rs.next()){
        results = results + rs.getString("Title") + ", (" + rs.getString("ProductionYear") + ") - ID Nr: " + rs.getInt("MovieID") + "\n";
      }
      //tm.setID(rs.getInt("movie_id"));
    }catch(Exception e){
      System.err.println("Getting movie search results: " + e.getMessage());
    }finally{
      if (results.equals("")) {
        results = "No matches found";
      }
      db.closeIt(rs);
      return results;
    }
    return null;
  }

  public String getSearchResultsForActorName(String name){
    ResultSet rs = db.executeQuery("SELECT Name, ActorID FROM actors WHERE Name LIKE '"+name+"'");
    try{
      String results = "";
      while (rs.next()){
        results = results + rs.getString("Name") + " - ID Nr. " + rs.getInt("ActorID") + "" + "\n";
      }
      //tm.setID(rs.getInt("movie_id"));
    }catch(Exception e){
      System.err.println("Getting actor search results: " + e.getMessage());
    }finally{
      if (results.equals("")) {
        results = "No matches found";
      }
      db.closeIt(rs);
      return results;
    }
    return null;
  }


}
