package jonathan_src.utils;
import java.util.List;
/**
 * An interface representing a municipality database of some kind.
 * This interface defines the operations on Municipalities in the database as well as
 * methods for retrieving a {@link java.util.List}&lt;{@link db.app.Municipality}&gt; of all Municipality objects from the database and
 * a method for retrieving one db.app.Municipality by its name.
 */
public interface MovieDB{

  /* Add the three new abstract methods here */
    public void setAutoCommit(boolean autoCommit);

    public void commit();

    public void rollback();
  /**
   * Returns a java.util.List of all municipalities as db.app.Municipality objects.
   * @return a java.util.List of all municipalities as db.app.Municipality objects.
   *
   */
  public List<Municipality>getAllCities();
  /**
   * Updates a municipality using its ID as condition for the DB query and updates all fields according to their current status.
   * @param m The Municipality to be updated
   */
  public void updateCity(Municipality m);
  /**
   * Deletes a Municipality according using its ID as condition for the query to the database.
   * @param m The Municipality to be updated
   */
  public void deleteCity(Municipality m);

  /**
   * Adds a Municipality to the database and sets its ID to the newly created records ID
   * @param m The Municipality to be updated
   */
  public void addCity(Municipality m);

  /**
   * Returns a reference to a new Municipality, which is fetched by name from the database.
   * @param name A String with the name of the municipality to fetch.
   * @return The named Municipality as a reference to a new Municipality object created from the database/storage.
   */
  public Municipality getByName(String name);

  /**
   * Updates a municipality's https status in the database, using its name for condition in the query.
   * @return The number of changed entries as an int.
   */
  public int updateHTTPSbyName(String name, boolean https);

  // We won't use this safe method in this lab, since we do want to
  // try to handle SQL injections ourselves. Leave this commented out.
  /* public int updateHTTPSbyNameSafe(String name, boolean https); */
}
