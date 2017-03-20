package utils.database_utils;
import java.sql.*;

public class DatabaseTest{

  private final static String DB_CONN_STR="jdbc:sqlite:MovieDatabase";
  static{
    try{
      Class.forName("org.sqlite.JDBC");
    }catch(ClassNotFoundException cnfe){
      System.err.println("Could not load driver: "+cnfe.getMessage());
    }
  }

  private static Connection con;
  public DatabaseTest(){
    getConnection();
  }
  private void getConnection(){
    try{
      con = DriverManager.getConnection(DB_CONN_STR);
    }catch(Exception e){
      System.err.println("Error getting connection to " +
      DB_CONN_STR);
    }
  }
  public boolean hasConnection(){
    return con != null;
  }
  public void testQuery(){
    if(hasConnection()){
      Statement stm = null;
      ResultSet rs  = null;
      try{
        String query="SELECT Title,Genre FROM movies LIMIT 5";
        stm = con.createStatement();
        rs = stm.executeQuery(query);
        while(rs.next()){
          System.out.println(rs.getString("Title") + " " + (rs.getString("Genre")));
        }
      }catch(SQLException sqle){
        System.err.println(sqle.getMessage());
      }finally{
        closeBoth(rs, stm);
      }
    }
  }
  private void closeIt(AutoCloseable it){
    try{
      it.close();
    }catch(Exception e){
      System.err.println("Exception closing: "+e.getMessage());
    }
  }
  private void closeBoth(AutoCloseable a1, AutoCloseable a2){
    try{
      closeIt(a1);
      closeIt(a2);
    }catch(Exception e){
      System.err.println("Exception closing: "+e.getMessage());
    }
  }

  public int deleteMovie(String name){
    String SQL = "DELETE FROM MovieDatabase" +
    " WHERE Title='" + name + "'";
    Statement stm = null;
    int rows = -1;
    try{
      stm  = con.createStatement();
      rows = stm.executeUpdate(SQL);
    }catch(Exception e){
      System.err.println("Exception deleting " + name
      + " " + e.getMessage());
    }finally{
      closeIt(stm);
      return rows;
    }
  }

  public String updateMovie(String original_title, String new_title){
    String SQL = "UPDATE movies SET Title='" + new_title +
    "' WHERE Title='" + original_title + "'";
    Statement stm = null;
    int rows = -1;
    try{
      stm  = con.createStatement();
      rows = stm.executeUpdate(SQL);
    }catch(Exception e){
      System.err.println("Exception updating Title for " + " " + e.getMessage());
    }finally{
      closeIt(stm);
     return null;
    }
  }

  public void testInsert(){
    String SQL = "INSERT INTO movies(Title, Genre, Rated, Director) " +
    "VALUES('Fight Club Test', 'GenreTest', '11', 'DirectorTest')";
    Statement stm=null;
    try{
      stm = con.createStatement();
      stm.executeUpdate(SQL);
    }catch(Exception e){
      System.err.println("Exception inserting: " + e.getMessage());
    }finally{
      closeIt(stm);
    }
  }
  public String fetchMovie(String name){
    String result = null;
    Statement stm = null;
    ResultSet rs  = null;
    String query  = "SELECT * FROM MovieDatabase WHERE Title='"+name+"'";
    try{
      stm = con.createStatement();
      rs = stm.executeQuery(query);
      if(rs.next()){
        result = rs.getString("Title")  + " - " +
        rs.getString("Genre")         + " - " +
        rs.getString("Rated")         + " - " +
        rs.getString("Director");
      }else{
        result = "No such Movie: " + name;
      }
    }catch(Exception e){
      System.err.println("Exception fetching " + name + ": " + e.getMessage());
    }finally{
      closeBoth(rs, stm);
      return result;
    }
  }
}
