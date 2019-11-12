import java.util.Map;
import java.util.HashMap;
import java.time.*;
public class IOParallel {

  public static void main(String [] args){
    if (args.length <1){
      System.out.println("Usage: javac filename <wordToFind>");
      System.exit(1);
    }
    String targetWord = args[0];



    //String [] files =  new String []{"mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt"};


    String [] files =  new String []{"mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt","mobydick.txt","warAndPeace.txt","eragon.txt","hobbit.txt","toKillAMockingbird.txt"};
    
    /*Parallel*/
    Thread [] threads = new Thread [files.length];
    Map<String, Integer> values = new HashMap<String, Integer>();
    int i = 0;
    Instant start = Instant.now();
    for (String file:files){
      IOreader newReader = new IOreader(file, values, targetWord);
      Thread t = new Thread(newReader);

      t.start();
      threads[i] = t;
      i++;
    }

    for (Thread thread:threads){
      try{
        thread.join();
      }
      catch (InterruptedException e){
        e.printStackTrace();
        System.exit(1);
      }
    }

    int sumLines = 0;

    for (String file:files){
      sumLines += values.get(file);
    }
    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();
    System.out.println(sumLines);
    System.out.println("parallel took "+timeElapsed/1000.0);

    /*
      Serialized reading of all the files
    */
    Map<String, Integer> values2 = new HashMap<String, Integer>();
    Instant start2 = Instant.now();
    for (String file:files){
      IOreader newReader = new IOreader(file, values2, targetWord);
      newReader.run();
    }
    sumLines = 0;
    for (String file:files){
      sumLines += values2.get(file);
    }
    Instant finish2 = Instant.now();
    long timeElapsed2 = Duration.between(start2, finish2).toMillis();
    System.out.println(sumLines);
    System.out.println("serial took "+timeElapsed2/1000.0);
  }
}
