package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;

public class MartialArtsCharmTree extends CharmTree {

  private final MartialArtsLevel standardLevel;

  public MartialArtsCharmTree(ICharmTemplate charmTemplate) {
    super(charmTemplate.getMartialArtsCharms());
    this.standardLevel = charmTemplate.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    return MartialArtsUtilities.getLevel(charm).compareTo(standardLevel) <= 1;
  }
}