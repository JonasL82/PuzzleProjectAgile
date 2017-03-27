package mdb_util.database_utils;
public class TestMovie{
  String title, prodyear, genre, rated, prodcompany, plot, premierdate, director;
  int movie_id;

  public TestMovie(String title, String prodyear, String genre, String rated,
                String prodcompany, String plot, String premierdate, String director){
    this.title = title;
    this.prodyear = prodyear;
    this.genre = genre;
    this. rated = rated;
    this.prodcompany = prodcompany;
    this.plot = plot;
    this.premierdate = premierdate;
    this.director = director;
    this.movie_id = 0;
  }

  public String title(){
    return title;
  }

  public String prodyear(){
    return prodyear;
  }

  public String genre(){
    return genre;
  }

  public String rated(){
    return rated;
  }

  public String prodcompany(){
    return prodcompany;
  }

  public String plot(){
    return plot;
  }

  public String premierdate(){
    return premierdate;
  }

  public String director(){
    return director;
  }

  public void newProdyear(String new_year){
    this.prodyear = new_year;
  }

  public void setID(int movie_id){
    this.movie_id = movie_id;
  }

}
