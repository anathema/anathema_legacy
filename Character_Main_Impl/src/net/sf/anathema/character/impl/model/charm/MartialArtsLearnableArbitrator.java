package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;

public class MartialArtsLearnableArbitrator implements ICharmLearnableArbitrator {

  private final ICharmTree martialArtsTree;

  public MartialArtsLearnableArbitrator(ICharmTree martialArtsTree) {
    this.martialArtsTree = martialArtsTree;
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    if (!MartialArtsUtilities.isMartialArtsCharm(charm)) {
      return true;
    }
    return martialArtsTree.isLearnableCharm(charm);
  }
}