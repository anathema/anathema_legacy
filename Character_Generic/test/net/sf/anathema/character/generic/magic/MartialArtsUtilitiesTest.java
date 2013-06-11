package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.util.Identified;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MartialArtsUtilitiesTest {

  @Test
  public void testIsMartialArtsCharm() {
    ICharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identified attribute) {
        return attribute.getId().equals("MartialArts");
      }
    };
    assertTrue(MartialArtsUtilities.isMartialArtsCharm(charm));
  }

  @Test
  public void testIsFormCharm() throws Exception {
    ICharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identified attribute) {
        return attribute.getId().equals("Form");
      }
    };
    assertTrue(MartialArtsUtilities.isFormCharm(charm));
  }

  @Test
  public void testGetMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identified attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial");
      }
    };
    assertEquals(MartialArtsLevel.Terrestrial, MartialArtsUtilities.getLevel(charm));
  }

  @Test
  public void testHasMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identified attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial");
      }
    };
    assertTrue(MartialArtsUtilities.hasLevel(MartialArtsLevel.Terrestrial, charm));
  }
}