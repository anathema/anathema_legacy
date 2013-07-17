package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.charm.Charm;

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