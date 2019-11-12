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

  public IOreader(String fileName, Map<String, Integer> values){
    try{
      FileReader fr = new FileReader(fileName);
      br = new BufferedReader(fr, buffLen);
      this.fileName = fileName;
      this.values = values;
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
    for (int i = 0; i<charsToRead; i++){
      if (buff[i] == '\n'){
        counter++;
      }
    }
    return counter;
  }
}
