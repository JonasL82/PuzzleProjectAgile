package people;
import java.util.ArrayList;
import java.util.Random;
import content.Movies;
import random.RandGen;

public class Actors {

    String name, birthplace, date_of_birth;
    ArrayList<Movies> performances;
    //HashMap movies performances???
    int id_nr;

    public Actors(String name, String birthplace, String date_of_birth){
        this.name = name;
        this.birthplace = birthplace;
        this.date_of_birth = date_of_birth;
        this.performances = new ArrayList<Movies>(); //tom arraylist <>
        this.id_nr = RandGen.RandNum();
    }

    public Actors(String name, String birthplace, String date_of_birth, int id_nr){
      this.name = name;
      this.birthplace = birthplace;
      this.date_of_birth = date_of_birth;
      this.id_nr = id_nr;
      this.performances = new ArrayList<Movies>();
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
    public int id_nr(){
      return id_nr;
    }

    public void changeName(String name){
      this.name = name;
    }
    public void changeBirthplace(String birthplace){
      this.birthplace = birthplace;
    }
    public void changeDateOfBirth(String date_of_birth){
      this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString(){
      return "Name: " + name + "\n" + "Birthplace: " + birthplace
      + "\n" + "Birthdate: " + date_of_birth + "\n" + "ID: " + id_nr + "\n"
       + "------";
    }
}
