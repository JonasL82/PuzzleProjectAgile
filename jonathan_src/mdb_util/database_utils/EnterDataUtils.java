package mdb_util.database_utils;
import java.util.Scanner;
import java.awt.*; //För KeyEvent
public class EnterDataUtils{

  private String id;
  //id = title of the movie
  private String name;
  //name = genre of the movie
  private String url;
  //url = rating of the movie
  private String server;
  //server = Director of the movie
  /**
   *@param id = title of the movie
   * @param name The genre of the movie
   * @param url The rated of the movie(åldersgräns)
   * @param server The Director this movie has.
   */
  public EnterDataUtils(String name, String url,
                      String server, String id){
    this.id=id;
    this.name=name;
    this.url=url;
    this.server=server;

  }

  /**
   * Returns the id of this Municipality. The ID could mean an internal id for an application creating a record of municipalities for instance.
   * @return the id of this Municipality as an int (or 0 if it's not been set)
   */
  public String id(){return this.id;}

  /**
   * Returns the name of this Municipality
   * @return The name of the municipality as a String
   *
   */
  public String name(){return this.name;}

  /**
   * Returns the URL of this Municipality
   * @return The URL of the municipality as a String
   *
   */
  public String url(){return this.url;}

  /**
   * Returns the web server name of this Municipality
   * @return The web server name of the municipality as a String
   *
   */
  public String server(){return this.server;}

  /**
   * Returns a boolean representing whether this municipality supports HTTPS for its web server
   * @return true if this Municipality supports HTTPS, otherwise false
   *
   */

  @Override
  public String toString(){
    return name + " | " + url + " | " +
      server + " | " +  id;
  }

}


  /* private EnterDataUtils(){ '// Jonathans kod'

  }

  public static int menuChoice(String original_entry){
    int first_valid_entry = 0;
    boolean found_valid_entry = false;
    return first_valid_entry;
  }
    */
