package review;

import account.*;
import content.*;
import random.*;
import java.util.Scanner;
public class Review{

  String review_text;
  int user_id; //ID-nr för användaren som skapade recensionen
  byte grade;
  int id_nr;
  String username;
  int movie_id;

  public Review(String review_text, byte grade, int user_id, String username, int movie_id){
    this.review_text = review_text;
    this.grade = grade;
    this.user_id = user_id;
    this.username = username;
    this.movie_id = movie_id;
    this.id_nr = RandGen.RandNum();
  }
  /*
  //FLYTTAD TILL ACCOUNT
  public Review createReview(int user_id){
    Scanner sc = new Scanner(System.in);
    //boolean length_OK = false;
    do{
      System.out.println("Enter your review text (max 240 characters):");
      String review_text = sc.nextLine();
    }while (review_text.length()>240 && review_text.length()<0);
    /*
    while (length_OK == false){
      if (review_text.length()<=240){ //DO WHILE review_text.length()>=241
        length_OK = true;
      }
      else {
        review_text = enterReviewText();
      }
    }
    do{
      System.out.println("Enter your grade (1-5)");
      byte grade = sc.nextByte();
    }while (grade>5 && grade<0);

    Review review = new Review(review_text, grade, user_id);
    return review;
  }
  */


/*
  public Review createReview(String review_text){

  }
*/
/*
  public String enterReviewText(){
    Scanner sc = new Scanner(System.in);
    String review_text;
    System.out.println("Enter your review: ");
    review_text = sc.nextLine();
  }
  */
  /*
  public byte enterGrade(){
    Scanner sc = new Scanner(System.in);
    int grade = sc.nextInt();
    if (grade < 6 && grade > 0){
      return (byte)grade;
    }
    else{
      System.out.println("Invalid entry");
      grade = enterGrade();
    }
    return 0;
  }
  */
  public String review_text(){
    return review_text;
  }

  public byte grade(){
    return grade;
  }

  public int user_id(){
    return user_id;
  }
  @Override
  public String toString(){
    String stars = "";
    for (int i = 0; i<grade; i++){
      stars = stars + "*";
    }
    return ("By: " + username + "\n" + "Text: " + review_text + "\n" + "Grade: " + stars +
            "\n" + "User's ID nr: " + user_id);

  }
}
