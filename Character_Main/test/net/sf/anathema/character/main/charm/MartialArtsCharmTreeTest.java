package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.main.magic.CharmAttribute;
import net.sf.anathema.character.main.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.main.magic.ICharm;
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
    when(template.getMartialArtsCharms()).thenReturn(new ICharm[]{charm});
    new MartialArtsCharmTree(template).isLearnable(charm);
  }
}
