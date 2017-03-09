import java.util.ArrayList;
import java.util.Random;

public class Actors {

    String name, birthplace, date_of_birth;
    ArrayList<Movies> performances;
    int id_nr;

    public Actors(String name, String birthplace, String date_of_birth){
        this.name = name;
        this.birthplace = birthplace;
        this.date_of_birth = date_of_birth;
        this.performances = new ArrayList<Movies>(); //tom arraylist <>
        this.id_nr = RandGen.RandNum();
    }

    public String name(){
      return name;
    }
    public String birthplace(){
      return birthplace;
    }
    public String date_of_birth(){
      return date_of_birth;
    }
}
