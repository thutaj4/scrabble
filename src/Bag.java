import java.util.ArrayList;
import java.util.Collections;

public class Bag implements Cloneable{
  private String bagString[];
  public static ArrayList<String> bag, trayLetters;
  
  public Bag() {
    bag = new ArrayList<String>();
    bagString = new String[] { "E", "E", "E", "E", "E", "E", "E", "E", "E",
      "E", "E", "E", "A", "A", "A", "A", "A", "A", "A", "A", "A",
      "I", "I", "I", "I", "I", "I", "I", "I", "I", "O", "O", "O",
      "O", "O", "O", "O", "O", "N", "N", "N", "N", "N", "N", "R",
      "R", "R", "R", "R", "R", "T", "T", "T", "T", "T", "T", "L",
      "L", "L", "L", "S", "S", "S", "S", "U", "U", "U", "U", "D",
      "D", "D", "D", "G", "G", "G", "B", "B", "C", "C", "M", "M",
      "P", "P", "F", "F", "H", "H", "V", "V", "W", "W", "Y", "Y",
      "K", "J", "X", "Q", "Z", "\0", "\0" };
    Collections.addAll(bag, bagString);
    Collections.shuffle(bag);
  }
  
  public ArrayList<String> getNewTray() {
    trayLetters = new ArrayList<String>();
    for (int k = 0; k < 7; k++) {
      if (bag.get(bag.size() - 1) != null)// if bag isn't empty
      {
        trayLetters.add(bag.get(bag.size() - 1));
        bag.remove(bag.size() - 1);
      }
    }
    return trayLetters;
  }
  
  public String getLastChar() {
    if (bag.size() == 0) {
      return null;
    } else {
      // return last element then delete from end of ArrayList
      return bag.remove(bag.size() - 1);
    }
  }
  
  public int getBagSize() {
    return bag.size();
  }
}