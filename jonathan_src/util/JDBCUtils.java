package jonathan_src.utils;
import java.sql.*;
import java.io.File;

public class JDBCUtils{

  private final static String DB_CONN_STR="jdbc:sqlite:MovieDatabase";
    private final static String DB_FILE="MovieDatabase"; // Used for debugging
    static{
      try{
        Class.forName("org.sqlite.JDBC");
      }catch(ClassNotFoundException cnfe){
        System.err.println("Could not load driver: "+cnfe.getMessage());
      }
    }
    private static Connection con;
    private static DBUtils instance = new DBUtils();
    private DBUtils(){
      getConnection();
    }
    /**
    * Singleton method to get the instance of this class.
    * @return The only instance of this class
    */
    public static DBUtils getInstance(){
      return instance;
    }

    private void getConnection(){
      try{
        con = DriverManager.getConnection(DB_CONN_STR);
        System.out.println("DEBUG: We have a connection to " + DB_CONN_STR);
        // Check if the database file is emtpy.
        // Remember, if the database file didn't exist,
        // sqlite3 will create an empty file for you.
        File f = new File(DB_FILE);
        if(f.length()==0L){
          System.err.println("The database " +
          DB_FILE +
          " is empty. Did you create the database before running Main?");
        }
      }catch(Exception e){
        System.err.println("Error getting connection to " +
        DB_CONN_STR);
      }
    }
    /**
    * Checks that the instance has a Connection to the database.
    * @return true if the instance has a connection, false otherwise
    */
    public boolean hasConnection(){
      return con != null;
    }

    private void error(String msg){ System.err.println(msg); }


    /**
    * Executes a query (from the argument sql, which is a String) and returns a ResultSet.
    * @return A ResultSet from the query
    * @param sql The String representing the SQL query to be executed
    */
    public ResultSet executeQuery(String sql){
      Statement stm=null;
      if(hasConnection()){
        try{
          stm=con.createStatement();
          return stm.executeQuery(sql);
        }catch(Exception e){
          error("executeQuery: " + e.getMessage());
          closeIt(stm);
        }
      }
      return null;
    }
    /**
    * Executes an SQL update statement, provided as the String argument
    * @param sql The SQL update statement as a String
    * @return An int representing how many rows were updated or -1 if the statement fails
    *
    */
    public int executeUpdate(String sql){
      Statement stm=null;
      if(hasConnection()){
        try{
          stm=con.createStatement();
          return stm.executeUpdate(sql);
        }catch(Exception e){
          error("executeUpdate: "+e.getMessage());
        }finally{
          closeIt(stm);
        }
      }
      return -1;
    }
    /**
    * Closes an AutoCloseable, for instance a ResultSet or a Statement.
    * @param it The AutoCloseable to be closed
    */
    public void closeIt(AutoCloseable it){
      try{
        it.close();
      }catch(Exception e){
        error("Exception closing: "+e.getMessage());
      }
    }
    /**
    * Not used in Handin03. Only exists here for reference.
    */
    public PreparedStatement preparedStatement(String sql){
      try{
        return con.prepareStatement(sql);
      }catch(Exception e){
        System.err.println("DBUtils: Error getting prepared stm: " + e.getMessage());
        e.printStackTrace();
        return null;
      }

    }

    public void setAutoCommit(boolean autoCommit){
      if(hasConnection()){
        try{
          con.setAutoCommit(autoCommit);
        }catch(SQLException sqle){
          System.err.println("Error setting autocommit to " + autoCommit);
          System.err.println(sqle);
        }
      }
      //    con.setAutoCommit(autoCommit);
    }

    public void commit(){
      try{
        if(!con.getAutoCommit()){
          System.out.println("Issuing commit");
          con.commit();
        }
      }catch(SQLException sqle){
          System.err.print("Error issuing commit: ");
          System.err.println(sqle);
        }
      }


    public void rollback(){
      try{
        if(!con.getAutoCommit()){
          System.out.println("Issuing rollback");
          con.rollback();
        }
      }catch(SQLException sqle){
        System.err.print("Error issuing rollback: ");
        System.err.println(sqle);
      }
    }

  }
}
