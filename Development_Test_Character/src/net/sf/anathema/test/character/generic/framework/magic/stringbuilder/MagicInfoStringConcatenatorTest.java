package net.sf.anathema.test.character.generic.framework.magic.stringbuilder;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.MagicInfoStringConcatenator;
import net.sf.anathema.lib.testing.BasicTestCase;

public class MagicInfoStringConcatenatorTest extends BasicTestCase {

  private MagicInfoStringConcatenator concatenator;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    DummyResources resources = new DummyResources();
    resources.putString("Charms.Cost.None", "None"); //$NON-NLS-1$//$NON-NLS-2$
    concatenator = new MagicInfoStringConcatenator(resources);
  }

  public void testNoCost() throws Exception {
    String displayString = concatenator.buildCostString("", "", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    assertEquals("None", displayString); //$NON-NLS-1$
  }

  public void testOneValueOnly() throws Exception {
    String expected = "Cost"; //$NON-NLS-1$
    String displayString;
    displayString = concatenator.buildCostString(expected, "", "", "");//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    assertEquals(expected, displayString);
    displayString = concatenator.buildCostString("", expected, "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    assertEquals(expected, displayString);
    displayString = concatenator.buildCostString("", "", expected, ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    assertEquals(expected, displayString);
    displayString = concatenator.buildCostString("", "", "", expected); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    assertEquals(expected, displayString);
  }

  public void testTwoValues() throws Exception {
    String expected = "Cost"; //$NON-NLS-1$
    String displayString;
    displayString = concatenator.buildCostString(expected, expected, "", "");//$NON-NLS-1$ //$NON-NLS-2$
    assertEquals(expected + ", " + expected, displayString); //$NON-NLS-1$
    displayString = concatenator.buildCostString("", expected, "", expected); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals(expected + ", " + expected, displayString); //$NON-NLS-1$
  }
}