package net.sf.anathema.hero.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.hero.magic.model.CharacterCharmModel;

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
