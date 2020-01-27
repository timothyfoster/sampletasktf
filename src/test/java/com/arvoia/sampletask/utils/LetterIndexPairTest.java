package com.arvoia.sampletask.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class LetterIndexPairTest {

  @Test
  public void testGetLetter() {
    LetterIndexPair pair1 = new LetterIndexPair('a', 1);
    assertEquals('a', pair1.getLetter());

    LetterIndexPair pair2 = new LetterIndexPair('d', 1);
    assertEquals('d', pair2.getLetter());
  }

  @Test
  public void testGetIndex() {
    LetterIndexPair pair = new LetterIndexPair('a', 99);
    assertEquals(99, pair.getIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionThrownOnCapitalLetter() {
    new LetterIndexPair('A', 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionThrownOnSpecialChar() {
    new LetterIndexPair('}', 1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testExceptionThrownOnNegativeIndex() {
    new LetterIndexPair('a', -1);
  }

  @Test
  public void testToString() {
    LetterIndexPair pair1 = new LetterIndexPair('a', 1);
    assertEquals("(a, 1)", pair1.toString());

    LetterIndexPair pair2 = new LetterIndexPair('b', 2);
    assertEquals("(b, 2)", pair2.toString());
  }
}