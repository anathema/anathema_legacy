package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import junit.framework.TestCase;
import net.sf.anathema.charmtree.builder.stringbuilder.MagicInfoStringConcatenator;
import net.sf.anathema.lib.dummy.DummyResources;

public class MagicInfoStringConcatenatorTest extends TestCase {

  private MagicInfoStringConcatenator concatenator;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("Charms.Cost.None", "None");
    concatenator = new MagicInfoStringConcatenator(resources);
  }

  public void testNoCost() throws Exception {
    String displayString = concatenator.buildCostString("", "", "", "");
    assertEquals("None", displayString);
  }

  public void testOneValueOnly() throws Exception {
    String expected = "Cost";
    String displayString;
    displayString = concatenator.buildCostString(expected, "", "", "");
    assertEquals(expected, displayString);
    displayString = concatenator.buildCostString("", expected, "", "");
    assertEquals(expected, displayString);
    displayString = concatenator.buildCostString("", "", expected, "");
    assertEquals(expected, displayString);
    displayString = concatenator.buildCostString("", "", "", expected);
    assertEquals(expected, displayString);
  }

  public void testTwoValues() throws Exception {
    String expected = "Cost";
    String displayString;
    displayString = concatenator.buildCostString(expected, expected, "", "");
    assertEquals(expected + ", " + expected, displayString);
    displayString = concatenator.buildCostString("", expected, "", expected);
    assertEquals(expected + ", " + expected, displayString);
  }
}