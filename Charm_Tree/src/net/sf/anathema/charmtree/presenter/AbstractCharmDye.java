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
    if (!groupInformer.hasGroupSelected()) {
      return;
    }
    ICharmGroup currentGroup = groupInformer.getCurrentGroup();
    for (ICharm charm : currentGroup.getAllCharms()) {
      setCharmVisuals(charm);
      colorExternalPrerequisites(charm);
    }
  }

  private void colorExternalPrerequisites(ICharm charm) {
    for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
      if (isPartOfCurrentGroup(prerequisite)) {
        return;
      }
      setCharmVisuals(prerequisite);
    }
  }

  private boolean isPartOfCurrentGroup(ICharm charm) {
    return charm.getGroupId().equals(groupInformer.getCurrentGroup().getId());
  }
}