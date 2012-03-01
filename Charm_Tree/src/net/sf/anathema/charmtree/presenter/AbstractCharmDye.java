package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;

public abstract class AbstractCharmDye implements CharmDye {

  private CharmGroupInformer groupInformer;

  public AbstractCharmDye(CharmGroupInformer informer) {
    this.groupInformer = informer;
  }

  @Override
  public void setCharmVisuals() {
    ICharmGroup currentGroup = groupInformer.getCurrentGroup();
    if (currentGroup == null) {
      return;
    }
    for (ICharm charm : currentGroup.getAllCharms()) {
      setCharmVisuals(charm);
    }
  }
}
