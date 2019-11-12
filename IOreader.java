import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class IOreader implements Runnable{

  public static final int buffLen = 10000;
  private BufferedReader br;
  private Map<String, Integer> values;
  private String fileName;
  private StringTracker tracker;
  private char [] symbols;

  public IOreader(String fileName, Map<String, Integer> values, String word){
    try{
      FileReader fr = new FileReader(fileName);
      br = new BufferedReader(fr, buffLen);
      this.fileName = fileName;
      this.values = values;
      tracker = new StringTracker(word);
      symbols = new char [] {';', ':', '!', ',', '.', '(', ')', '?', '\''};
    }
    catch ( IOException e){
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void run(){
    char [] buff = new char [buffLen];
    int count = 0;
    //loop
    boolean flag = true;
    while (flag){
      try{
        int charsRead = br.read(buff,0,buffLen);
        int c = read(buff,charsRead);
        count += c;
        if (charsRead == -1){
          flag = false;
        }
      }
      catch (IOException e){
        e.printStackTrace();
        System.exit(1);
      }
    }
    values.put(fileName,new Integer(count));
  }

  public int read(char [] buff,int charsToRead){
    //iterate over the buffer and count new line characters
    int counter = 0;
    if (charsToRead==-1){
      return counter;
    }
    char currChar;
    for (int i = 0; i<charsToRead; i++){
      currChar = buff[i];
      if (tracker.isEqual(-1)){
        if (Character.compare(currChar, ' ')==0)
        {
          tracker.increment();
        }
      }
      else if (tracker.isEqualMaxLen()){
        if(endOfWord(currChar)){
          counter +=1;
          tracker.resetTracker();
        }
      }
      else if (currChar == tracker.charAt())
      {
        tracker.increment();
      }
      else{
        tracker.resetTracker();
      }
    }
    return counter;
  }

  public boolean endOfWord(char wordEnder){
    for (char symbol :symbols){
      if (symbol == wordEnder){
        return true;
      }
    }
    return false;
  }
}
