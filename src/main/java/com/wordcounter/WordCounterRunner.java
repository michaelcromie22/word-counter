package com.wordcounter;
import java.util.Map;
import java.util.Scanner;

public class WordCounterRunner {
  private static final String INPUT_PROMPT = "Please enter a sentence in the format \"This is a sample sentence.\" (or 'q' to quit): ";
  private static final String QUIT_SYMBOL = "q";
  private static final String INVALID_SENTENCE_RESPONSE = "Invalid sentence. Please try again.";

  public static void main(String args[]) {
    WordCounter wordCounter = new WordCounter();
    Scanner in = new Scanner(System.in);
    boolean quit = false;
    
    System.out.println(INPUT_PROMPT);
    while (!quit && in.hasNext()) {
      String sentence = in.nextLine();
      if (QUIT_SYMBOL.equals(sentence.toLowerCase())) {
        quit = true;
      } else {
          Map<String, Integer> wordCountMap = wordCounter.countWordsInSentence(sentence);
          printWordCounts(wordCountMap);
      }
    }
    in.close();
  }
  
  private static void printWordCounts(Map<String, Integer> wordCountMap) {
    if (wordCountMap == null || wordCountMap.isEmpty()) {
      System.out.println(INVALID_SENTENCE_RESPONSE);
    } else {
      for (String word : wordCountMap.keySet()) {
        System.out.println(word + " — " + wordCountMap.get(word));
      } 
    }
    System.out.println();
    System.out.println(INPUT_PROMPT);
  }
  
}
