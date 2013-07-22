package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.model.ICharmGroup;
import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.lib.util.Identifier;

public final class CharacterGroupCharmTree implements GroupCharmTree {
  private final Identifier cascadeType;
  private CharmDisplayModel model;

  public CharacterGroupCharmTree(CharmDisplayModel model, Identifier cascadeType) {
    this.cascadeType = cascadeType;
    this.model = model;
  }

  @Override
  public ICharmGroup[] getAllCharmGroups() {
    return model.getCharmModel().getCharmGroups(cascadeType);
  }
}
