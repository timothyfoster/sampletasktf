package com.arvoia.sampletask.utils;

/**
 * This class is used to keep track of each letter in the cipher-alphabet and its corresponding index (or shift in the
 * case of the key).
 *
 * @author Tim Foster
 */
public class LetterIndexPair {

  private final char letter;
  private final int index;

  /**
   * This constructor accepts an English alphabet character and an index to build a letter-index pair. Invalid input
   * will cause an {@code IllegalArgumentException} to be thrown
   *
   * @param letter the lowercase English alphabet character which is being tracked.
   * @param index  the corresponding position or shift for this letter.
   * @throws IllegalArgumentException if the provided letter is not from the lowercase English alphabet, or
   *                                  if the provided index is less than 1.
   */
  LetterIndexPair(final char letter, final int index) {
    if (!Character.isLowerCase(letter)) {
      throw new IllegalArgumentException("Only lower case English letters are accepted");
    }

    if (index < 1) {
      throw new IllegalArgumentException("Index must be greater than 1");
    }

    this.letter = letter;
    this.index = index;
  }

  /**
   * Standard getter to return the stored letter.
   *
   * @return the stored letter.
   */
  char getLetter() {
    return letter;
  }

  /**
   * Standard getter to return the stored index.
   *
   * @return the index of the stored letter.
   */
  int getIndex() {
    return index;
  }

  /**
   * Simple method to produce a nice string representation of the letter / index pair for printing purposes.
   *
   * @return a nicely formatted string.
   */
  public String toString() {
    return "(" + letter + ", " + index + ")";
  }
}
