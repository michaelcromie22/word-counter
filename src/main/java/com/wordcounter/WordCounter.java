package com.wordcounter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

class WordCounter {

  protected Map<String, Integer> countWordsInSentence(String sentence) {
    if (!isValidSentence(sentence)) {
      return Collections.emptyMap();
    }

    Map<String, Integer> wordCount = new LinkedHashMap<>();

    String[] words = convertSentenceToWords(sentence);
    for (String word : words) {
      if (!word.isEmpty()) {
        if (wordCount.containsKey(word)) {
          wordCount.put(word, wordCount.get(word) + 1);
        } else {
          wordCount.put(word, 1);
        }
      }
    }
    return wordCount;
  }

  private boolean isValidSentence(String sentence) {
    String trimmedSentence = sentence != null ? sentence.replaceAll(" ", "") : null;
    return trimmedSentence != null && trimmedSentence.length() > 2 && trimmedSentence.startsWith("\"")
        && trimmedSentence.endsWith("\"");
  }

  private String[] convertSentenceToWords(String sentence) {
    return sentence.toLowerCase()
        // replace ellipses and em dashes with a space
        .replaceAll("[...—]", " ")
        // remove all quotations having single quotation marks (e.g 'This is a
        // quotation.')
        .replaceAll("(?<![a-z])\\'", "").replaceAll("\\'(?![a-z])", "")
        // remove all punctuation that is neither a hyphen nor an apostrophe
        .replaceAll("[^a-z'\\s\\-]", "")
        .trim().split("\\s+");
  }

}
