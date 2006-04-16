package net.sf.anathema.character.generic.impl.magic.test;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.util.IIdentificate;

public class MartialArtsUtilitiesTest extends BasicTestCase {

  public void testIsMartialArtsCharm() {
    ICharm charm = new DummyMartialArtsCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("MartialArts"); //$NON-NLS-1$
      }
    };
    assertTrue(MartialArtsUtilities.isMartialArtsCharm(charm));
  }

  public void testIsFormCharm() throws Exception {
    ICharm charm = new DummyMartialArtsCharm("Dummy") { //$NON-NLS-1$
      @Override
      public boolean hasAttribute(IIdentificate attribute) {
        return attribute.getId().equals("Form"); //$NON-NLS-1$
      }
    };
    assertTrue(MartialArtsUtilities.isFormCharm(charm));
  }
}