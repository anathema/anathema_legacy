package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.hero.charms.model.ICharmGroup;
import net.sf.anathema.hero.charms.model.CharmGroupCollection;

public class CharacterGroupCollection implements CharmGroupCollection {
  private CharmDisplayModel model;

  public CharacterGroupCollection(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public ICharmGroup[] getCharmGroups() {
    return model.getCharmModel().getAllGroups();
  }
}
