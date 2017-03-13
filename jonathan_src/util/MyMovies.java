package jonathan_src.utils;
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
  public List<Municipality>getAllCities(){
    ArrayList<Municipality> list = new ArrayList<Municipality>();
    ResultSet rs = db.executeQuery("SELECT * FROM municipalities");
    try{
      Municipality m=null;
      while(rs.next()){
        m=new Municipality(rs.getString("Name"),
                           rs.getString("URL"),
                           rs.getString("Server"),
                           rs.getBoolean("HTTPS"));
        m.setID(rs.getInt("MunicipalityID"));
        list.add(m);
      }
      db.closeIt(rs);
      return list;
    }catch(Exception e){
      System.err.println("Getting all municipalities: " + e.getMessage());
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
  public void updateCity(Municipality m){
    int id = m.id();
    String SQL="UPDATE municipalities SET HTTPS="+
      (m.supportsHTTPS()?"1":"0") +
      " WHERE MunicipalityID="+id;
    System.out.println(db.executeUpdate(SQL)
                       + " rows updated");
  }
  /**
   * Deletes a Municipality from the storage.
   * @param m A reference to the Municipality to be deleted.
   *
   */
  public void deleteCity(Municipality m){
    int id = m.id();
    String SQL="DELETE FROM municipalities"+
      " WHERE MunicipalityID="+id;
    System.out.println(db.executeUpdate(SQL) +
                       " rows deleted");
    // What if m.id() returns 0? Think about a solution!
  }
  /**
   * Inserts m into the database and sets the id of m to the
   * MunicipalityID it gets.
   * @param m The Municipality to add to the storage (database)
   */
  public void addCity(Municipality m){
    //int id=m.id();
    String name=m.name();
    String url=m.url();
    String server=m.server();
    boolean https=m.supportsHTTPS();
    String SQL="INSERT INTO municipalities"+
      "(Name,URL,Server,HTTPS)" +
      " VALUES('"+name+"', "+
      "'"+url+"', " +
      "'" + server + "', 0)";
    System.out.println(db.executeUpdate(SQL)+
                       " rows inserted");

    int id=0;
    /* m doesn't have an ID yet. Let's find it... */
    /*
      Alternative method to get the last rowid:
      implement select last_insert_rowid(); in the executeUpdate()
      method. That requires a total re-design of the method and
      the semantics. Better to add a executeInsert method that
      returns the last_insert_rowid() if successful or 0 otherwise.
    */

    ResultSet rs = db.executeQuery("SELECT MunicipalityID"+
                                   " FROM municipalities"+
                                   " WHERE Name='"+name+"'");
    try{
      rs.next();
      m.setID(rs.getInt("MunicipalityID"));
    }catch(Exception e){
      System.err.println("Getting ID: " + e.getMessage());
    }finally{
      db.closeIt(rs);
    }
  }
  /**
   * Fetches a Municipality by its name from the storage (database).
   * @param name The name of the Municipality to be fetched.
   * @return The named Municipality as a new Municipality object, or null if none could be fetched.
   */
  public Municipality getByName(String name){
    String SQL="SELECT * FROM municipalities WHERE name='"+name+"'";

    System.out.println("DEBUG: SQL: " + SQL);
    ResultSet rs = db.executeQuery(SQL);
    Municipality m = null;
    try{
      if(rs.next()){
        m = new Municipality(rs.getString("Name"),
                             rs.getString("URL"),
                             rs.getString("Server"),
                             rs.getBoolean("HTTPS"));
        m.setID(rs.getInt("MunicipalityID"));
      }
      return m;
    }catch(Exception e){
      System.err.println("getByName: " + e.getMessage());
    }finally{
      db.closeIt(rs);
    }
    return null;
  }
  /**
   * Updates the stored HTTPS for a named city. The actual SQL statement
   * being sent to the database is printed to std out as a debug message.
   * @param name The name of the stored city we want to update the HTTPS for
   * @param https The new boolean value for the named city
   * @return The number of rows affected as an int
   */
  public int updateHTTPSbyName(String name, boolean https){
    String SQL="UPDATE municipalities SET HTTPS="+(https?"1":"0")+" WHERE name='"+name+"'";
    System.out.println("DEBUG: SQL: " + SQL);
    int rows = db.executeUpdate(SQL);
    return rows;
  }

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
