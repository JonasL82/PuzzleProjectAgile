public class Media {

  String title,genre,language,plot,director,scriptwriter,cast;
  String  release_dates;
  byte age_limit;
  short year;
  int id_nr;

  public Media(String title,String genre,String language,String plot,
  String director,String scriptwriter, String cast,
  String release_dates,byte age_limit,short year){
      this.title = title;
      this.genre = genre;
      this.language = language;
      this.plot = plot;
      this.director = director;
      this.scriptwriter = scriptwriter;
      this.cast = cast;
      this.release_dates = release_dates;
      this.age_limit = age_limit;
      this.year = year;
      this.id_nr = RandGen.RandNum();
  }

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
  public String cast(){
    return cast;
  }
  public String release_dates(){
    return release_dates;
  }
  public byte age_limit(){
    return age_limit;
  }
  public short year(){
    return year;
  }
}
