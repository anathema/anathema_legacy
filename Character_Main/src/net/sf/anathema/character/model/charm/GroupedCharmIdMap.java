package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;

import java.util.Arrays;
import java.util.Collection;

public class GroupedCharmIdMap implements ICharmIdMap {

  private final Collection<? extends ICharmIdMap> trees;

  public GroupedCharmIdMap(ICharmIdMap[] charmTrees) {
    this(Arrays.asList(charmTrees));
  }

  public GroupedCharmIdMap(Collection<? extends ICharmIdMap> trees) {
    this.trees = trees;
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