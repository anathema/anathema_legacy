package net.sf.anathema.character.main.magic.model.charm;

import java.util.Collection;

public class GroupedCharmIdMap implements CharmIdMap {

  private final Collection<? extends CharmIdMap> trees;

  public GroupedCharmIdMap(Collection<? extends CharmIdMap> trees) {
    this.trees = trees;
  }

  @Override
  public Charm getCharmById(String charmId) {
    for (CharmIdMap tree : trees) {
      Charm charm = tree.getCharmById(charmId);
      if (charm != null) {
        return charm;
      }
    }
    return null;
  }
}