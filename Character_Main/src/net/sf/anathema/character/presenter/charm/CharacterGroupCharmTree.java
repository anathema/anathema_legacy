package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.lib.util.IIdentificate;

public final class CharacterGroupCharmTree implements GroupCharmTree {
  private final IIdentificate cascadeType;
  private CharacterCharmModel model;

  public CharacterGroupCharmTree(CharacterCharmModel model, IIdentificate cascadeType) {
    this.cascadeType = cascadeType;
    this.model = model;
  }

  @Override
  public ICharmGroup[] getAllCharmGroups() {
    return model.getCharmConfiguration().getCharmGroups(cascadeType);
  }
}