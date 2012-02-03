package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.charmtree.presenter.view.CharmGroupInformer;

public class DelegatingCharmGroupInformer implements CharmGroupInformer {
  private CharmGroupInformer informer;

  public void setInformer(CharmGroupInformer informer) {
    this.informer = informer;
  }

  @Override
  public ICharmGroup getCurrentGroup() {
    return informer.getCurrentGroup();
  }
}
