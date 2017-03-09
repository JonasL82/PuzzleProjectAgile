public class TestRandom{
  public static void main(String[] args){

    Actors a = new Actors("a","b","c");
    for(int i=0; i<10; i++){
      System.out.println(RandGen.RandNum());
    }
    
  }
}
