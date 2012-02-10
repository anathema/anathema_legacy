package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.model.CharmIdGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharmIdGenerator_Test {

  @Test
  public void testSimpleId() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Name"); //$NON-NLS-1$
    assertEquals("Solar.Name", id); //$NON-NLS-1$
  }

  @Test
  public void testIdWithBlanks() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Name with blanks"); //$NON-NLS-1$
    assertEquals("Solar.Namewithblanks", id); //$NON-NLS-1$
  }

  @Test
  public void testIdWithThe() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Name the Next"); //$NON-NLS-1$
    assertEquals("Solar.NameNext", id); //$NON-NLS-1$
  }

  @Test
  public void testIdWithFakeThe() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Namet he Next"); //$NON-NLS-1$
    assertEquals("Solar.NametheNext", id); //$NON-NLS-1$
  }

  @Test
  public void testIdWithOf() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Name of None"); //$NON-NLS-1$
    assertEquals("Solar.NameNone", id); //$NON-NLS-1$
  }

  @Test
  public void testInnerThe() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Wither or Die"); //$NON-NLS-1$
    assertEquals("Solar.WitherorDie", id); //$NON-NLS-1$
  }

  @Test
  public void testInnerOf() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Unoffered Beauty"); //$NON-NLS-1$
    assertEquals("Solar.UnofferedBeauty", id); //$NON-NLS-1$
  }

  @Test
  public void testOfThe() throws Exception {
    String id = CharmIdGenerator.createIDFromName(CharacterType.SOLAR, "Offer of the Vile Beast"); //$NON-NLS-1$
    assertEquals("Solar.OfferVileBeast", id); //$NON-NLS-1$
  }
}