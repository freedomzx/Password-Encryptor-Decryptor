import java.lang.Math;

public class passwordGenerator
{
  public static String generate(int length){
    if(length > 64){
      System.out.println("Too long, 64 characters or less please!");
      System.exit(0);
    }
    else if(length < 6){
      System.out.println("Too short, 6 characters or more please!");
      System.exit(0);
    }
    String toReturn = "";
    for(int i = 0; i < length; i++){
      int generated = (int)(Math.random() * ((126 - 33) + 1)) + 33;
      char insertThis = (char) generated;
      toReturn = toReturn + insertThis;
    }
    return toReturn;
  }
}