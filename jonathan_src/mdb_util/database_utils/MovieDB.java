package mdb_util.database_utils;
import java.util.List;
import java.util.ArrayList;
import content.Movies;
import account.Account;
import people.Actors;
public interface MovieDB{

  public void setAutoCommit(boolean autoCommit);

  public void commit();

  public void rollback();

  public ArrayList<Movies> getMovieDatabase();

  public ArrayList<Account> getAccountsFromDatabase();

  public ArrayList<Actors> getActorsFromDatabase();

  public void updateMovieYear(Movies m, int new_year);

  public void deleteMovie(Movies m);

  public void addMovieToDatabase(Movies m);

  public Account getStandardAccount();

}
