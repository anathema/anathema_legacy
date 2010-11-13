package net.sf.anathema.test.character.generic.magic.charms;

import net.sf.anathema.character.generic.impl.magic.CharmUtilities;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharmUtilitiesTest extends BasicTestCase {

  public void testSimpleId() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Name"); //$NON-NLS-1$
    assertEquals("Solar.Name", id); //$NON-NLS-1$
  }

  public void testIdWithBlanks() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Name with blanks"); //$NON-NLS-1$
    assertEquals("Solar.Namewithblanks", id); //$NON-NLS-1$
  }

  public void testIdWithThe() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Name the Next"); //$NON-NLS-1$
    assertEquals("Solar.NameNext", id); //$NON-NLS-1$
  }

  public void testIdWithFakeThe() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Namet he Next"); //$NON-NLS-1$
    assertEquals("Solar.NametheNext", id); //$NON-NLS-1$
  }

  public void testIdWithOf() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Name of None"); //$NON-NLS-1$
    assertEquals("Solar.NameNone", id); //$NON-NLS-1$
  }

  public void testInnerThe() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Wither or Die"); //$NON-NLS-1$
    assertEquals("Solar.WitherorDie", id); //$NON-NLS-1$
  }

  public void testInnerOf() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Unoffered Beauty"); //$NON-NLS-1$
    assertEquals("Solar.UnofferedBeauty", id); //$NON-NLS-1$
  }

  public void testOfThe() throws Exception {
    String id = CharmUtilities.createIDFromName(CharacterType.SOLAR, "Offer of the Vile Beast"); //$NON-NLS-1$
    assertEquals("Solar.OfferVileBeast", id); //$NON-NLS-1$
  }
}