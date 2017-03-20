package utils.database_utils;
import java.util.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * An implementation of MunicipalityDB that uses the DBUtils to connect to an SQLite database.
 */
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

   //String title, String prodyear, String genre, String rated, String prodcompany, String plot, String premierdate, String director
  public List<TestMovie> getMovieDatabase(){
    ArrayList<TestMovie> list = new ArrayList<TestMovie>();
    ResultSet rs = db.executeQuery("SELECT * FROM MovieDatabase");
    try{
      TestMovie tm=null;
      while(rs.next()){
      tm=new TestMovie(rs.getString("Title"),
                          rs.getString("Prodyear"),
                           rs.getString("Genre"),
                           rs.getString("Rated"),
                           rs.getString("Prodcompany"),
                           rs.getString("Plot"),
                           rs.getString("Premierdate"),
                           rs.getString("Director"));
      //  m.setID(rs.getInt("MunicipalityID"));
        list.add(tm);
      }
      db.closeIt(rs);
      return list;
    }catch(Exception e){
      System.err.println("Getting all Movies: " + e.getMessage());
      db.closeIt(rs);
    }
    return null;
  }
  /**
   * Updates a Municipality in the storage. This method uses the ID of the Municipality when updating the storage.
   * This version of the method only updates the HTTPS field of the Municipality, in the storage. Later
   * versions may updated all fields.
   * @param m The municipality to be updated.
   *
   */
  public void updateMovieYear(TestMovie tm, String new_year){
    db.executeQuery("BEGIN TRANSACTION");
    String title = tm.title();
    String SQL="UPDATE MovieDatabase SET Prodyear='"+new_year+
      "' WHERE Title='"+title+"'";
    if (db.executeUpdate(SQL) > 1) {
      System.out.println("Error when changing year.");
      db.rollback();
    }
    else {
      System.out.println("Movie's year changed");
      db.commit();
    }
  }
  /**
   * Deletes a Municipality from the storage.
   * @param m A reference to the Municipality to be deleted.
   *
   */
  public void deleteMovie(TestMovie tm, String delete_title){
    db.executeQuery("BEGIN TRANSACTION");
    String title = tm.title();
    String SQL="DELETE FROM MovieDatabase WHERE Title='"+title+"'";
    System.out.println(db.executeUpdate(SQL) +
                       " rows deleted");
      if (db.executeUpdate(SQL) > 1) {
         System.out.println("Error when deleting.");
         db.rollback();
        }
     else {
         System.out.println("Movie deleted");
         db.commit();
        }
    // What if m.id() returns 0? Think about a solution!
  }
  /**
   * Inserts m into the database and sets the id of m to the
   * MunicipalityID it gets.
   * @param m The Municipality to add to the storage (database)
   */
  public void addMovie(TestMovie tm){
    String title = tm.title();
    String genre = tm.genre();
    String rated = tm.rated();
    String prodcompany = tm.prodcompany();
    String plot = tm.plot();
    String prodyear = tm.prodyear();
    String premierdate = tm.premierdate();
    String director = tm.director();
    /*
    (rs.getString("Title"),
                        rs.getString("Prodyear"),
                         rs.getString("Genre"),
                         rs.getString("Rated"),
                         rs.getString("Prodcompany"),
                         rs.getString("Plot"),
                         rs.getString("Premierdate"),
                         rs.getString("Director"));
    */
    //int id=m.id();
    /*
    String name=db.name();
    String url=db.url();
    String server=db.server();
    boolean https=db.supportsHTTPS();
    */
    String SQL="INSERT INTO movies (Title, Genre, Rated, Prodcompany, Plot, Prodyear, Premierdate, Director) VALUES('"+
                title+"', '"+genre+"', '"+rated+"', '"+prodcompany+"', '"+plot+"', '"+prodyear+"', '"+premierdate+"', '"+director+"')";
    System.out.println(db.executeUpdate(SQL)+
                       " rows inserted");
    /* m doesn't have an ID yet. Let's find it... */
    /*
      Alternative method to get the last rowid:
      implement select last_insert_rowid(); in the executeUpdate()
      method. That requires a total re-design of the method and
      the semantics. Better to add a executeInsert method that
      returns the last_insert_rowid() if successful or 0 otherwise.
    */

    //Här avslutade vi den 15 mars. FORTSÄTT HÄR!!!!!



  }

  public String getTitleProdyear(String title){
    ResultSet rs = db.executeQuery("SELECT Title, Prodyear FROM movies WHERE Title LIKE '"+title+"'");
    String results = "";
    try{
      rs.next();
      while (rs.next()){
        results = results + rs.getString("Title") + ", (" + rs.getString("Prodyear") + ")" + "\n";
      }
      //tm.setID(rs.getInt("movie_id"));
    }catch(Exception e){
      System.err.println("Getting ID: " + e.getMessage());
    }finally{
      db.closeIt(rs);
    }
    return null;
  }
  /**
   * Fetches a Municipality by its name from the storage (database).
   * @param name The name of the Municipality to be fetched.
   * @return The named Municipality as a new Municipality object, or null if none could be fetched.
   */

  /*
  public MovieDB getByName(String name){
    String SQL="SELECT * FROM MovieDatabase WHERE name='"+name+"'";

    System.out.println("DEBUG: SQL: " + SQL);
    ResultSet rs = db.executeQuery(SQL);
    MovieDB m = null;
    try{
      if(rs.next()){
        m = new MovieDB(rs.getString("name"),
                             rs.getString("server"),
                             rs.getString("url"),
                             rs.getString("id"));
      //  m.setID(rs.getInt("id"));
      }
      return m;
    }catch(Exception e){
      System.err.println("getByName: " + e.getMessage());
    }finally{
      db.closeIt(rs);
    }
    return null;
  }
  */
  /**
   * Updates the stored HTTPS for a named city. The actual SQL statement
   * being sent to the database is printed to std out as a debug message.
   * @param name The name of the stored city we want to update the HTTPS for
   * @param https The new boolean value for the named city
   * @return The number of rows affected as an int
   */

  /*
  public int updateHTTPSbyName(String name, boolean https){
    String SQL="UPDATE MovieDatabase SET HTTPS="+ " WHERE name='"+name+"'";
    System.out.println("DEBUG: SQL: " + SQL);
    int rows = db.executeUpdate(SQL);
    return rows;
  }
  */
  /*
  // We don't want to use this safe method, since we
  // want to try to handle SQL injections ourselves...
  public int updateHTTPSbyNameSafe(String name, boolean https){
  String sql = "UPDATE municipalities SET HTTPS=? WHERE name= ?";
  int result=0;
  try{
  PreparedStatement pStm = db.preparedStatement(sql);
  pStm.setInt(1, (https?1:0));
  pStm.setString(2,name);
  result=pStm.executeUpdate();
  return result;
  }catch(Exception e){
  System.err.println("Error creating prepared stm: "+e.getMessage());
  return -1;
  }
  }
  */
}
