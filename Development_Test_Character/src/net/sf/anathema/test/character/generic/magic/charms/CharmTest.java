package net.sf.anathema.test.character.generic.magic.charms;

import java.util.HashMap;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.dummy.character.magic.DummyCharmData;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharmTest extends BasicTestCase {

  public void testParentCharmsNotOverwritten() throws Exception {
    DummyCharmData data = new DummyCharmData();
    DummyCharm dummy = new DummyCharm("OtherDummy"); //$NON-NLS-1$
    data.setParentCharms(new ICharm[] { dummy });
    Charm charm = new Charm(data);
    charm.extractParentCharms(new HashMap<String, Charm>(), ExaltedRuleSet.CoreRules);
    assertEquals(1, charm.getParentCharms().size());
    assertEquals(dummy, charm.getParentCharms().toArray(new ICharm[1])[0]);
  }
}