package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.presenter.magic.CharmColoringStrategy;
import net.sf.anathema.charmtree.view.CharmGroupInformer;

public class ConfigurableCharmDye implements CharmDye {

  private final CharmGroupInformer groupInformer;
  private final CharmColoringStrategy dye;

  public ConfigurableCharmDye(CharmGroupInformer informer, CharmColoringStrategy dye) {
    this.groupInformer = informer;
    this.dye = dye;
  }

  public void colorCharm(ICharm charm) {
    dye.colorCharm(charm);
  }

  public void setCharmVisuals() {
    if (!groupInformer.hasGroupSelected()) {
      return;
    }
    for (ICharm charm : getAllCharmsFromCurrentGroup()) {
      dye.colorCharm(charm);
      colorExternalCharmPrerequisites(charm);
      colorNonCharmPrerequisite(charm);
    }
  }

  private void colorNonCharmPrerequisite(ICharm charm) {
    for (IndirectCharmRequirement requirement : charm.getIndirectRequirements()) {
      dye.setPrerequisiteVisuals(requirement);
    }
  }

  private void colorExternalCharmPrerequisites(ICharm charm) {
    for (ICharm prerequisite : charm.getRenderingPrerequisiteCharms()) {
      if (isPartOfCurrentGroup(prerequisite)) {
        return;
      }
      dye.colorCharm(prerequisite);
    }
  }

  private boolean isPartOfCurrentGroup(ICharm charm) {
    return charm.getGroupId().equals(groupInformer.getCurrentGroup().getId());
  }

  protected ICharm[] getAllCharmsFromCurrentGroup() {
    return groupInformer.getCurrentGroup().getAllCharms();
  }
}