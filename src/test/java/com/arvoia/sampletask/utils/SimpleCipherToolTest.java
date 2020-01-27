package com.arvoia.sampletask.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class SimpleCipherToolTest {

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionThrownOnInvalidKeyWithCapitals() {
    new SimpleCipherTool("AAAA");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionThrownOnInvalidKeyWithNumbers() {
    new SimpleCipherTool("aa9");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionThrownOnInvalidKeyWithSymbols() {
    new SimpleCipherTool("!@*#");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionThrownOnInvalidKeyWithNullLength() {
    new SimpleCipherTool("");
  }

  @Test
  public void testEncipherWithSimpleKey() {
    SimpleCipherTool simpleCipherTool = new SimpleCipherTool("a");
    assertEquals("bbbbb", simpleCipherTool.cipher("aaaaa"));
    assertEquals("-+/*&", simpleCipherTool.cipher("-+/*&"));
    assertEquals("d_d", simpleCipherTool.cipher("c_c"));
  }

  @Test
  public void testEncipherWithAbcKey() {
    SimpleCipherTool simpleCipherTool = new SimpleCipherTool("abc");
    assertEquals("bcd", simpleCipherTool.cipher("aaa"));
    assertEquals("b_d", simpleCipherTool.cipher("a_a"));
    assertEquals("jln", simpleCipherTool.cipher("ijk"));
  }

  @Test
  public void testEncipherWithArvoiaKey() {
    SimpleCipherTool simpleCipherTool = new SimpleCipherTool("arvoia");
    assertEquals("ilpeb://cxcivt.rxn/uodacbrkar/tsieufusozcg",
        simpleCipherTool.cipher("https://github.com/csorbazoli/sampletasktf"));
  }

  @Test
  public void testDecipherWithSimpleKey() {
    SimpleCipherTool simpleCipherTool = new SimpleCipherTool("a");
    assertEquals("aaaaa", simpleCipherTool.decipher("bbbbb"));
    assertEquals("-+/*&", simpleCipherTool.decipher("-+/*&"));
    assertEquals("c_c", simpleCipherTool.decipher("d_d"));
  }

  @Test
  public void testDecipherWithAbcKey() {
    SimpleCipherTool simpleCipherTool = new SimpleCipherTool("abc");
    assertEquals("aaa", simpleCipherTool.decipher("bcd"));
    assertEquals("a_a", simpleCipherTool.decipher("b_d"));
    assertEquals("ijk", simpleCipherTool.decipher("jln"));
  }

  @Test
  public void testDecipherWithArvoiaKey() {
    SimpleCipherTool simpleCipherTool = new SimpleCipherTool("arvoia");
    assertEquals("https://github.com/csorbazoli/sampletasktf",
        simpleCipherTool.decipher("ilpeb://cxcivt.rxn/uodacbrkar/tsieufusozcg"));
  }
}
