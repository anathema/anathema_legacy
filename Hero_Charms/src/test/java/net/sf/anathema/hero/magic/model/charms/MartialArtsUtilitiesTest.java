package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.lib.util.Identifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MartialArtsUtilitiesTest {

  @Test
  public void testIsMartialArtsCharm() {
    Charm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("MartialArts");
      }
    };
    assertTrue(MartialArtsUtilities.isMartialArts(charm));
  }

  @Test
  public void testIsFormCharm() throws Exception {
    Charm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("Form");
      }
    };
    assertTrue(MartialArtsUtilities.isFormMagic(charm));
  }

  @Test
  public void testGetMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial");
      }
    };
    assertEquals(MartialArtsLevel.Terrestrial, MartialArtsUtilities.getLevel(charm));
  }

  @Test
  public void testHasMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") {
      @Override
      public boolean hasAttribute(Identifier attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial");
      }
    };
    assertTrue(MartialArtsUtilities.hasLevel(MartialArtsLevel.Terrestrial, charm));
  }
}