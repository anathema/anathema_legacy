package net.sf.anathema.character.generic.magic;

import net.sf.anathema.character.generic.dummy.DummyCharmData;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CharmTest {

  @Test
  public void testParentCharmsNotOverwritten() throws Exception {
    DummyCharmData data = new DummyCharmData();
    DummyCharm dummy = new DummyCharm("OtherDummy"); //$NON-NLS-1$
    data.setParentCharms(new ICharm[] { dummy });
    Charm charm = new Charm(data);
    charm.extractParentCharms(new HashMap<String, Charm>());
    assertEquals(1, charm.getParentCharms().size());
    assertEquals(dummy, charm.getParentCharms().toArray(new ICharm[1])[0]);
  }
}