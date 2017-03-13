package jonathan_src.utils;
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
          System.out.println(rs.getString("Title") + " " + (rs.getString("Genre"));
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

  public int deleteCity(String name){
    String SQL = "DELETE FROM municipalities" +
      " WHERE Name='" + name + "'";
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

  public int updateHTTPS(String name, boolean https){
    String SQL = "UPDATE municipalities SET HTTPS=" +
      (https?"1":"0") +
      " WHERE Name='" + name + "'";
    Statement stm = null;
    int rows = -1;
    try{
      stm  = con.createStatement();
      rows = stm.executeUpdate(SQL);
    }catch(Exception e){
      System.err.println("Exception updating https for " + name
                         + " " + e.getMessage());
    }finally{
      closeIt(stm);
      return rows;
    }
  }

  public void testInsert(){
    String SQL = "INSERT INTO municipalities(Name, URL, HTTPS, Server) " +
      "VALUES('Ingalunda stad', 'http://ingalunda.se', 0, 'IIS')";
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
  public String fetchCity(String name){
    String result = null;
    Statement stm = null;
    ResultSet rs  = null;
    String query  = "SELECT * FROM municipalities WHERE Name='"+name+"'";
    try{
      stm = con.createStatement();
      rs = stm.executeQuery(query);
      if(rs.next()){
        result = rs.getString("Name") + " - " +
          rs.getString("URL")       + " - " +
          (rs.getBoolean("HTTPS")?"https support":"only http") + " - " +
          rs.getString("Server");
      }else{
        result = "No such city: " + name;
      }
    }catch(Exception e){
      System.err.println("Exception fetching " + name + ": " + e.getMessage());
    }finally{
      closeBoth(rs, stm);
      return result;
    }
  }
}
