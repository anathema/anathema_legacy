package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.GroupCharmTree;
import net.sf.anathema.hero.charms.model.CharacterCharmModel;
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
