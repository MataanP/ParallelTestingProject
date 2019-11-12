public class StringTracker{

  private String targetWord;
  private int tracker;
  private int maxLen;

  public StringTracker(String word){
    targetWord = word;
    maxLen = targetWord.length();
    tracker = -1;
  }

  public void increment(){
    tracker +=1;
  }

  public boolean isEqualMaxLen(){
    return tracker==maxLen;
  }

  public boolean isEqual(int num){
    return num == tracker;
  }

  public void resetTracker(){
      tracker = -1;
  }

  public char charAt(){
    return targetWord.charAt(tracker);
  }
}
