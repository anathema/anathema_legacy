package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmAttribute;
import net.sf.anathema.character.main.magic.model.charmtree.MartialArtsCharmTree;
import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.testing.dummy.template.DummyCharmTemplate;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class MartialArtsCharmTreeTest {

  @Test(expected = IllegalStateException.class)
  public void throwsHelpfulExceptionForCharmThatIsNoMartialArtsCharm() throws Exception {
    DummyCharm charm = new DummyCharm("MyID");
    charm.addKeyword(new CharmAttribute("Celestial", false));
    DummyCharmTemplate template = spy(new DummyCharmTemplate());
    when(template.getMartialArtsCharms()).thenReturn(new Charm[]{charm});
    new MartialArtsCharmTree(template).isLearnable(charm);
  }
}
