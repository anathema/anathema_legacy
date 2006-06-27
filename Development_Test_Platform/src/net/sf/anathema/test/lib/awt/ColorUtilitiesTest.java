package net.sf.anathema.test.lib.awt;

import java.awt.Color;

import net.sf.anathema.lib.awt.ColorUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public class ColorUtilitiesTest extends BasicTestCase {

  public void testHexConversion() {
    String hexString = new String(ColorUtilities.convertColorToHexString(Color.RED));
    assertEquals("#ff0000", hexString); //$NON-NLS-1$
  }
}