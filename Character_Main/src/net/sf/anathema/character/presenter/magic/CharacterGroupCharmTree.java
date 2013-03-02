package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.lib.util.Identified;

public final class CharacterGroupCharmTree implements GroupCharmTree {
  private final Identified cascadeType;
  private CharacterCharmModel model;

  public CharacterGroupCharmTree(CharacterCharmModel model, Identified cascadeType) {
    this.cascadeType = cascadeType;
    this.model = model;
  }

  @Override
  public ICharmGroup[] getAllCharmGroups() {
    return model.getCharmConfiguration().getCharmGroups(cascadeType);
  }
}
