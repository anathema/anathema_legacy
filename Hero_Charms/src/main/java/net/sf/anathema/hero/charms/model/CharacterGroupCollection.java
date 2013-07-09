package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;

public class CharacterGroupCollection implements CharmGroupCollection {
  private CharacterCharmModel model;

  public CharacterGroupCollection(CharacterCharmModel model) {
    this.model = model;
  }

  @Override
  public ICharmGroup[] getCharmGroups() {
    return model.getCharmConfiguration().getAllGroups();
  }
}
