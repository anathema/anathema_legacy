package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;

public class GroupedCharmIdMap implements ICharmIdMap {

  private final ICharmIdMap[] trees;

  public GroupedCharmIdMap(ICharmIdMap[] charmTrees) {
    this.trees = charmTrees;
  }

  @Override
  public ICharm getCharmById(String charmId) {
    for (ICharmIdMap tree : trees) {
      ICharm charm = tree.getCharmById(charmId);
      if (charm != null) {
        return charm;
      }
    }
    return null;
  }
}