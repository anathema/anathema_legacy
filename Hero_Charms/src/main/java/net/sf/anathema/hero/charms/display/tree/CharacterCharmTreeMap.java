package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.lib.util.Identifier;

public class CharacterCharmTreeMap implements CharmTreeMap {
  private CharmDisplayModel charmModel;

  public CharacterCharmTreeMap(CharmDisplayModel charmModel) {
    this.charmModel = charmModel;
  }

  @Override
  public GroupCharmTree getCharmTree(Identifier type) {
    return new CharacterGroupCharmTree(charmModel, type);
  }
}