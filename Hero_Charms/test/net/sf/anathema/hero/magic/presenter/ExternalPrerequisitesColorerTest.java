package net.sf.anathema.hero.magic.presenter;

import net.sf.anathema.character.main.magic.charmtree.presenter.ExternalPrerequisitesColorer;
import net.sf.anathema.character.main.magic.charms.CharmGroup;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;
import net.sf.anathema.character.main.dummy.DummyCharm;
import net.sf.anathema.character.main.testing.dummy.DummyMundaneCharacterType;
import net.sf.anathema.character.main.presenter.magic.CharmColoring;
import net.sf.anathema.character.main.magic.charmtree.view.CharmGroupInformer;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ExternalPrerequisitesColorerTest {

  private final CharmColoring coloring = mock(CharmColoring.class);
  private final CharmGroupInformer informer = mock(CharmGroupInformer.class);

  @Test
  public void doesNotColorParentCharmFromCurrentGroup() throws Exception {
    DummyCharm parent = createParentCharmFromGroup("CurrentGroup");
    ICharm child = createChildCharm(parent);
    ICharmGroup currentGroup = createGroupWithCharms(parent, child);
    selectGroup(currentGroup);
    colorAllPrerequisitesOfChild(child);
    verifyZeroInteractions(coloring);
  }

  private void colorAllPrerequisitesOfChild(ICharm child) {
    ExternalPrerequisitesColorer colorer = new ExternalPrerequisitesColorer(informer, coloring);
    colorer.color(child);
  }

  private void selectGroup(ICharmGroup currentGroup) {
    when(informer.getCurrentGroup()).thenReturn(currentGroup);
  }

  private ICharmGroup createGroupWithCharms(DummyCharm parent, ICharm child) {
    return new CharmGroup(new DummyMundaneCharacterType(), "CurrentGroup", new ICharm[]{parent, child}, false);
  }

  private DummyCharm createParentCharmFromGroup(String group) {
    DummyCharm parent = new DummyCharm("parent");
    parent.setGroupId(group);
    parent.setCharacterType(new DummyMundaneCharacterType());
    return parent;
  }

  private ICharm createChildCharm(DummyCharm parent) {
    return new DummyCharm("child", parent);
  }
}
