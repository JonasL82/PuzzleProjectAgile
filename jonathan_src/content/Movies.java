package content;
import java.util.ArrayList;
import java.util.HashMap;
import account.Account;
import people.Actors;
import content.CastListing;
import random.*;
import review.Review;
import mdb_util.database_utils.MyMovies;
public class Movies{


  String title, genre, language, plot, director, scriptwriter, production_company;
  String release_dates;
  String age_limit;
  int year;
  int id_nr;
  ArrayList<CastListing> cast;
  HashMap<Integer, Review> reviews;
  MyMovies database = new MyMovies();

    public Movies(String title, String genre, String language, String plot,
                  String director, String scriptwriter, String release_dates,
                  String age_limit, String production_company,
                   int year, int id_nr){
        //super(title,genre,language,plot,director,scriptwriter,release_dates,age_limit,year);
        //System.out.println("super called");

        this.title = title;
        this.genre = genre;
        this.language = language;
        this.plot = plot;
        this.director = director;
        this.scriptwriter = scriptwriter;
        this.release_dates = release_dates;
        this.age_limit = age_limit;
        this.production_company = production_company;
        this.year = year;
        this.id_nr = id_nr; //RandGen.RandNum();
        this.cast = database.getCastForMovie(id_nr);
        reviews = database.getReviewsForMovie(id_nr);

    }

    //Returfunktioner
    public String title(){
      return title;
    }
    public String genre(){
      return genre;
    }
    public String language(){
      return language;
    }
    public String plot(){
      return plot;
    }
    public String director(){
      return director;
    }
    public String scriptwriter(){
      return scriptwriter;
    }
    public int id_nr(){
      return id_nr;
    }

    public String release_dates(){
      return release_dates;
    }
    public String age_limit(){
      return age_limit;
    }
    public int year(){
      return year;
    }
    public String production_company(){
      return production_company;
    }
    public HashMap<Integer, Review> reviews(){
      return reviews;
    }
    public ArrayList<CastListing> cast(){
      return cast;
    }

    //Tilläggsfunktioner
    public void addReview(Account account, Review review){
      reviews.put(account.getID(), review);
    }

    public void addActorToCast(Actors a, String character_name){ //Ändra
      database.updateMovieAddActorToCast(this.id_nr, a.id_nr(), character_name);
      this.cast = updateCastFromDatabase(this.cast);
    }


    //Ändringsfunktioner
    public void changeTitle(String title){
      this.title = title;
    }
    public void changeYear(int new_year){
      this.year = new_year;
    }
    public void changePlot(String plot){
      this.plot = plot;
    }
    public void changeGenre(String genre){
      this.genre = genre;
    }
    public void changeLanguage(String language){
      this.language = language;
    }
    public void changeDirector(String director){
      this.director = director;
    }
    public void changeScriptwriter(String scriptwriter){
      this.scriptwriter = scriptwriter;
    }
    public void changeReleaseDate(String release_dates){
      this.release_dates = release_dates;
    }
    public void changeAgeLimit(String age_limit){
      this.age_limit = age_limit;
    }
    public ArrayList<CastListing> updateCastFromDatabase(ArrayList<CastListing> original_cast){
      if (original_cast.size()>0) {
        this.cast.clear();
      }
      ArrayList<CastListing> new_cast = database.getCastForMovie(this.id_nr);
      return new_cast;
    }
    public String printCast(){
      String cast_list = "";
      for (int i = 0; i<cast.size(); i++) {
        cast_list = cast_list + cast.get(i).toString() + "\n";
      }
      return cast_list;
    }
    @Override
    public String toString(){ //Ändra

      return "Title: " + title + "\n" + "Production year: " + year + "\n" + "Genre: " + genre + "\n" +
      "Language: " + language + "\n" + "Plot: " + plot + "\n" + "Directed by: " + director + "\n"
      + "Written by: " + scriptwriter + "\n" + "Release: " + release_dates +
       "\n" + "Age Limit: " + age_limit + "\n" + "ID Nr.: " + id_nr + "\n" + "Cast: " + (cast.size()>0?printCast():"None listed") + "------";
/*
      return "Title: " + title + "\n" + "Production year: " + year + "\n" + "Genre: " + genre + "\n" +
      "Language: " + language + "\n" + "Plot: " + plot + "\n" + "Directed by: " + director + "\n"
      + "Written by: " + scriptwriter + "\n" + "Release: " + release_dates +
       "\n" + "Age Limit: " + age_limit + "\n" + "ID Nr.: " + id_nr + "\n"
        + "------";
        */
    }
}
