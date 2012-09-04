package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
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
    for (ICharm charm : getAllCharmsFromCurrentGroup()) {
      colorCharm(charm);
      colorExternalCharmPrerequisites(charm);
      colorNonCharmPrerequisite(charm);
    }
  }

  private void colorNonCharmPrerequisite(ICharm charm) {
    for (IndirectCharmRequirement requirement : charm.getIndirectRequirements()) {
      setPrerequisiteVisuals(requirement);
    }
  }

  private void colorExternalCharmPrerequisites(ICharm charm) {
    for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
      if (isPartOfCurrentGroup(prerequisite)) {
        return;
      }
      colorCharm(prerequisite);
    }
  }

  private boolean isPartOfCurrentGroup(ICharm charm) {
    return charm.getGroupId().equals(groupInformer.getCurrentGroup().getId());
  }

  protected ICharm[] getAllCharmsFromCurrentGroup() {
    return groupInformer.getCurrentGroup().getAllCharms();
  }

  protected abstract void setPrerequisiteVisuals(IndirectCharmRequirement requirement);
}