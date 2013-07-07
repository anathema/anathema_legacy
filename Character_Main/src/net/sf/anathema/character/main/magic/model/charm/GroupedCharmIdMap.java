package net.sf.anathema.character.main.magic.model.charm;

import java.util.Collection;

public class GroupedCharmIdMap implements CharmIdMap {

  private final Collection<? extends CharmIdMap> trees;

  public GroupedCharmIdMap(Collection<? extends CharmIdMap> trees) {
    this.trees = trees;
  }

  @Override
  public ICharm getCharmById(String charmId) {
    for (CharmIdMap tree : trees) {
      ICharm charm = tree.getCharmById(charmId);
      if (charm != null) {
        return charm;
      }
    }
    return null;
  }
}