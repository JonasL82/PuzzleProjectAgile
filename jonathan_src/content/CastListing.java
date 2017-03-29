package content;
import content.Movies;
import people.Actors;
import mdb_util.database_utils.*;
import java.util.ArrayList;
public class CastListing{
  Actors actor;
  String character_name;
  final String splitter = " - ";
  MyMovies database = new MyMovies();

  public CastListing(Actors actor, String character_name){
    this.actor = actor;
    this.character_name = character_name;
  }

  public Actors actor(){
    return actor;
  }

  public String character_name(){
    return character_name;
  }

  @Override
  public String toString(){
    return actor.name() + splitter + character_name;
  }
}
