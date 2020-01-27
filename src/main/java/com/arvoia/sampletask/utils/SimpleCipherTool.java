package com.arvoia.sampletask.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class can be used to encipher or decipher a string by applying an encryption key.
 *
 * <p>Only lowercase English alphabet characters will be enciphered or deciphered.</p>
 *
 * <p>Encryption and decryption are achieved by means of shifting each character up or down a number of places
 * in the alphabet according to the provided key.</p>
 *
 * @author Tim Foster
 */
public class SimpleCipherTool {

  private final List<LetterIndexPair> alphabetList;
  private final List<LetterIndexPair> keyList;

  /**
   * This constructor accepts an encryption key which will be used during the enciphering and deciphering processes.
   * It will initialise the {@code LetterIndexPair} list representation of both the key and English alphabet.
   *
   * @param keyword the keyword which will be used to either encipher or decipher a string.
   */
  public SimpleCipherTool(String keyword) {
    if (keyword.length() <= 0) {
      throw new IllegalArgumentException("Key must contain at least 1 character");
    }

    if (!keyword.matches("[a-z]+")) {
      throw new IllegalArgumentException("Key must contain only lowercase English alphabet characters");
    }

    alphabetList = initAlphabetList();
    keyList = initKeyList(keyword, alphabetList);
  }

  /**
   * This method will encipher a plaintext string using the key provided in the constructor.
   *
   * @param text the plaintext string to encipher.
   * @return the enciphered string.
   */
  public String cipher(String text) {
    int keyIndex = 0;
    StringBuilder cipheredText = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      char plaintextChar = text.charAt(i);
      if (Character.isLowerCase(plaintextChar)) {
        int plaintextCharPos = getPairByLetter(alphabetList, plaintextChar).getIndex();
        int ciphertextCharPos = getShiftedUpPosition(plaintextCharPos, keyList.get(keyIndex).getIndex());
        char ciphertextChar = getPairByIndex(alphabetList, ciphertextCharPos).getLetter();
        cipheredText.append(ciphertextChar);
      } else {
        cipheredText.append(plaintextChar);
      }
      if (keyIndex++ >= keyList.size() - 1) {
        keyIndex = 0;
      }
    }

    return cipheredText.toString();
  }

  /**
   * This method will decipher an encrypted string using the key provided in the constructor.
   *
   * @param cipheredText the encrypted string which will be decrypted.
   * @return the plaintext version of the encrypted string.
   */
  public String decipher(String cipheredText) {
    int keyIndex = 0;
    StringBuilder plaintext = new StringBuilder();
    for (int i = 0; i < cipheredText.length(); i++) {
      char cipherChar = cipheredText.charAt(i);
      if (Character.isLowerCase(cipherChar)) {
        int cipherCharPos = getPairByLetter(alphabetList, cipherChar).getIndex();
        int plaintextCharPos = getShiftedDownPosition(cipherCharPos, keyList.get(keyIndex).getIndex());
        char plaintextChar = getPairByIndex(alphabetList, plaintextCharPos).getLetter();
        plaintext.append(plaintextChar);
      } else {
        plaintext.append(cipherChar);
      }
      if (keyIndex++ >= keyList.size() - 1) {
        keyIndex = 0;
      }
    }

    return plaintext.toString();
  }

  /**
   * Used to initialise the {@code alphabetList} with a list of {@code LetterIndexPairs} representing the English
   * lowercase alphabet.
   *
   * @return a list of {@code LetterIndexPairs} consisting of the lowercase English alphabet.
   */
  private List<LetterIndexPair> initAlphabetList() {
    int indexCounter = 1;
    List<LetterIndexPair> tmpList = new ArrayList<>();
    for (char letter = 'a'; letter <= 'z'; letter++) {
      LetterIndexPair pair = new LetterIndexPair(letter, indexCounter++);
      tmpList.add(pair);
    }
    return tmpList;
  }

  /**
   * Used to initialise the {@code keyList} with a list of {@code LetterIndexPairs} representing the shift of each
   * letter in the provided key.
   *
   * @param key          the key used to generate the list of {@code LetterIndexPairs}.
   * @param alphabetList the alphabet used to get the position of each element for the {@code keyList}.
   * @return a list of {@code LetterIndexPairs} consisting of the letter-index pairs generated from the key.
   */
  private List<LetterIndexPair> initKeyList(final String key, final List<LetterIndexPair> alphabetList) {
    List<LetterIndexPair> tmpList = new ArrayList<>();
    for (int i = 0; i < key.length(); i++) {
      char currentChar = key.charAt(i);
      LetterIndexPair pair = getPairByLetter(alphabetList, currentChar);
      tmpList.add(pair);
    }
    return tmpList;
  }

  /**
   * Shifts the the position of a character up by the specified number of places, wrapping round to the front of the
   * {@code alphabetList} if the shifted position exceeds the size of the list. This method is used to encipher a
   * character.
   *
   * @param currentPos the index of the character to be shifted.
   * @param upShift    the amount of places to shift up by.
   * @return the new shifted index of the character.
   */
  private int getShiftedUpPosition(final int currentPos, final int upShift) {
    int shiftedPos = currentPos + upShift;
    if (shiftedPos >= 1 && shiftedPos <= alphabetList.size()) {
      return shiftedPos;
    } else {
      return shiftedPos - alphabetList.size();
    }
  }

  /**
   * Shifts the the position of a character down by the specified number of places, wrapping round to the back of the
   * {@code alphabetList} if the shifted index is 0 or less. This method is used to decipher a character.
   *
   * @param currentPos the index of the character to be shifted.
   * @param downShift  the amount of places to shift down by.
   * @return the new shifted index of the character.
   */
  private int getShiftedDownPosition(final int currentPos, final int downShift) {
    int shiftedPos = currentPos - downShift;
    if (shiftedPos >= 1 && shiftedPos <= alphabetList.size()) {
      return shiftedPos;
    } else {
      return shiftedPos + alphabetList.size();
    }
  }

  /**
   * Searches the specified list for an instance of a {@code LetterIndexPair} which has a matching letter {@code c}
   * and returns the pair if it exists or null otherwise.
   *
   * @param list the list to be searched.
   * @param c    the letter to be searched for.
   * @return the {@code LetterIndexPair} containing the letter if it exists in the list, null otherwise.
   */
  private LetterIndexPair getPairByLetter(final List<LetterIndexPair> list, final Character c) {
    Optional<LetterIndexPair> pair = list
        .stream()
        .filter(p -> p.getLetter() == c)
        .findAny();

    return pair.orElseThrow(IllegalStateException::new);
  }

  /**
   * Searches the specified list for an instance of a {@code LetterIndexPair} which has a matching index {@code index}
   * and returns the pair if it exists or null otherwise.
   *
   * @param list  the list to be searched.
   * @param index the index to be searched for.
   * @return the {@code LetterIndexPair} containing the index if it exists in the list, null otherwise.
   */
  private LetterIndexPair getPairByIndex(final List<LetterIndexPair> list, final Integer index) {
    Optional<LetterIndexPair> pair = list
        .stream()
        .filter(p -> Objects.equals(p.getIndex(), index))
        .findAny();

    return pair.orElseThrow(IllegalStateException::new);
  }
}
