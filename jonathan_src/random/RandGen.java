package random;
import java.util.Random;

public class RandGen {

  public static Random numGen =new Random();

  public static int RandNum(){
    int rand = Math.abs((10000)+numGen.nextInt(89999));

    return rand;
  }

}
