package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.magic.cache.CharmProvider;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charmtree.MartialArtsCharmTree;
import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttributeImpl;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MartialArtsCharmTreeTest {

  @Test(expected = IllegalStateException.class)
  public void throwsHelpfulExceptionForCharmThatIsNoMartialArtsCharm() throws Exception {
    DummyCharm charm = new DummyCharm("MyID");
    charm.addKeyword(new MagicAttributeImpl("Celestial", false));
    CharmProvider charmProvider = mock(CharmProvider.class);
    when(charmProvider.getMartialArtsCharms()).thenReturn(new Charm[]{charm});
    new MartialArtsCharmTree(charmProvider, MartialArtsLevel.Mortal).isLearnable(charm);
  }
}
