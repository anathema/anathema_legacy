package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.magic.charms.GroupCharmTree;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;
import net.sf.anathema.lib.util.Identifier;

public final class CharacterGroupCharmTree implements GroupCharmTree {
  private final Identifier cascadeType;
  private CharacterCharmModel model;

  public CharacterGroupCharmTree(CharacterCharmModel model, Identifier cascadeType) {
    this.cascadeType = cascadeType;
    this.model = model;
  }

  @Override
  public ICharmGroup[] getAllCharmGroups() {
    return model.getCharmConfiguration().getCharmGroups(cascadeType);
  }
}
