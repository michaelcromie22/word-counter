package com.wordcounter;

import java.util.Map;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;

public class WordCounterTest {
  
  WordCounter wordCounter = new WordCounter();

  @Test
  public void testNoWordsReturnedForNullSentence() {
    String sentence = null;
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(0));
  }
  
  @Test
  public void testNoWordsReturnedForEmptyString() {
    String sentence = "";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(0));
  }
  
  @Test
  public void testNoWordsReturnedForEmptyStringWithQuotationMarks() {
    String sentence = "\"\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(0));
  }
  
  @Test
  public void testNoWordsReturnedForSentenceNotContainedWithinQuotationMarks() {
    String sentence = "Hello.";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(0));
  }
  
  @Test
  public void testNoWordsReturnedForSentenceContainingOnlySpaces() {
    String sentence = "\"     \"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(0));
  }
  
  @Test
  public void testNoWordsReturnedForEmptySentenceWithOnlyPunctuationAndNumbers() {
    String sentence = "\"\'\\|?!12345\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(0));
  }
  
  @Test
  public void testOneWordWithCountOfOneReturnedForSentenceContainingOneWordWithOneOccurrence() {
    String sentence = "\"Hello.\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 1));
  }
  
  @Test
  public void testOneWordWithCountOfOneReturnedForSentenceContainingOneWordWithOneOccurrenceWithWhitespaceBeforeAndAfterTheSentence() {
    String sentence = "   \"Hello.\"   ";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 1));
  }
  
  @Test
  public void testOneWordWithCountOfOneReturnedForSentenceContainingOneWordWithOneOccurrenceWithWhitespaceWithinTheSentence() {
    String sentence = "\"   Hello.   \"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 1));
  }

  @Test
  public void testOneWordWithCountOfOneReturnedForSentenceContainingOneWordWithOneOccurrencePlusPunctuationAndNumbers() {
    String sentence = "\"\'\\|?!123Hello456\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 1));
  }
  
  @Test
  public void testTwoWordsWithCountOfOneReturnedForSentenceContainingTwoWordsWithOneOccurrenceEach() {
    String sentence = "\"Hello there.\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(2));
    assertThat(wordCount, hasEntry("hello", 1));
    assertThat(wordCount, hasEntry("there", 1));
  }
  
  @Test
  public void testOneWordWithCountOfThreeReturnedForSentenceContainingOneWordWithThreeOccurrencesOfDifferentCases() {
    String sentence = "\"Hello, hello! HELLO!\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 3));
  }
  
  @Test
  public void testOneWordWithCountOfTwoReturnedForSentenceContainingOneWordWithTwoOccurrencesWithSingleQuotationMarks() {
    String sentence = "\"Hello 'Hello'.\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 2));
  }
  
  @Test
  public void testOneWordWithCountOfTwoReturnedForSentenceContainingOneWordWithTwoOccurrencesWithDoubleQuotationMarks() {
    String sentence = "\"Hello \"Hello\".\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 2));
  }

  @Test
  public void testOneWordWithCountOfTwoReturnedForSentenceContainingOneWordWithTwoOccurrencesWithEllipsis() {
    String sentence = "\"Hello...hello?\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 2));
  }
  
  @Test
  public void testOneWordWithCountOfTwoReturnedForSentenceContainingOneWordWithTwoOccurrencesWithEmDash() {
    String sentence = "\"Hello—hello!\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(1));
    assertThat(wordCount, hasEntry("hello", 2));
  }
  
  @Test
  public void testCorrectCountsForSentenceContainingWordHavingAnApostropheAndItsComponentWords() {
    String sentence = "\"It is a nice day, isn't it?\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(6));
    assertThat(wordCount, hasEntry("it", 2));
    assertThat(wordCount, hasEntry("is", 1));
    assertThat(wordCount, hasEntry("a", 1));
    assertThat(wordCount, hasEntry("nice", 1));
    assertThat(wordCount, hasEntry("day", 1));
    assertThat(wordCount, hasEntry("isn't", 1));
  }
  
  @Test
  public void testCorrectCountsForSentenceContainingDuplicateWordsHavingAnApostropheWithSpeechMarks() {
    String sentence = "\"'I can't go, I just can't!' said Michael.\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(6));
    assertThat(wordCount, hasEntry("i", 2));
    assertThat(wordCount, hasEntry("can't", 2));
    assertThat(wordCount, hasEntry("go", 1));
    assertThat(wordCount, hasEntry("just", 1));
    assertThat(wordCount, hasEntry("said", 1));
    assertThat(wordCount, hasEntry("michael", 1));
  }
  
  @Test
  public void testCorrectCountsForSentenceContainingMultipleWordsHavingAHyphen() {
    String sentence = "\"Is that ice-cream sugar-free?\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(4));
    assertThat(wordCount, hasEntry("is", 1));
    assertThat(wordCount, hasEntry("that", 1));
    assertThat(wordCount, hasEntry("ice-cream", 1));
    assertThat(wordCount, hasEntry("sugar-free", 1));
  }
  
  @Test
  public void testCorrectCountsForSentenceContainingDuplicateWordsHavingAHyphen() {
    String sentence = "\"Sugar-free ice-cream isn't really ice-cream.\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(4));
    assertThat(wordCount, hasEntry("isn't", 1));
    assertThat(wordCount, hasEntry("really", 1));
    assertThat(wordCount, hasEntry("ice-cream", 2));
    assertThat(wordCount, hasEntry("sugar-free", 1));
  }
  
  @Test
  public void testCorrectCountsForSentenceContainingHyphenatedWordAndItsComponentWords() {
    String sentence = "\"Ice-cream is ice and cream.\"";
    Map<String, Integer> wordCount = wordCounter.countWordsInSentence(sentence);
    assertThat(wordCount.size(), equalTo(5));
    assertThat(wordCount, hasEntry("ice", 1));
    assertThat(wordCount, hasEntry("cream", 1));
    assertThat(wordCount, hasEntry("ice-cream", 1));
    assertThat(wordCount, hasEntry("is", 1));
    assertThat(wordCount, hasEntry("and", 1));
  }
  
}
