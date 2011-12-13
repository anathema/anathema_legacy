package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.util.IIdentificate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MartialArtsUtilitiesTest {

  @Test
  public void testIsMartialArtsCharm() {
    ICharm charm = new DummyCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("MartialArts"); //$NON-NLS-1$
      }
    };
    assertTrue(MartialArtsUtilities.isMartialArtsCharm(charm));
  }

  @Test
  public void testIsFormCharm() throws Exception {
    ICharm charm = new DummyCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("Form"); //$NON-NLS-1$
      }
    };
    assertTrue(MartialArtsUtilities.isFormCharm(charm));
  }

  @Test
  public void testGetMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    };
    assertEquals(MartialArtsLevel.Terrestrial, MartialArtsUtilities.getLevel(charm));
  }

  @Test
  public void testHasMartialArtsLevel() throws Exception {
    DummyCharm charm = new DummyCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("MartialArts") || attribute.getId().equals("Terrestrial"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    };
    assertTrue(MartialArtsUtilities.hasLevel(MartialArtsLevel.Terrestrial, charm));
  }
}