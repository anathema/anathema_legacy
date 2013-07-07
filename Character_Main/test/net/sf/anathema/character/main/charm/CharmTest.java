package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.main.magic.Charm;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.testing.dummy.DummyCharmData;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CharmTest {

  @Test
  public void testParentCharmsNotOverwritten() throws Exception {
    DummyCharmData data = new DummyCharmData();
    DummyCharm dummy = new DummyCharm("OtherDummy");
    data.setParentCharms(new ICharm[]{dummy});
    Charm charm = new Charm(data);
    charm.extractParentCharms(new HashMap<String, Charm>());
    assertEquals(1, charm.getParentCharms().size());
    assertEquals(dummy, charm.getParentCharms().toArray(new ICharm[1])[0]);
  }
}