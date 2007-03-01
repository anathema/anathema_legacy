package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;

public class MartialArtsCharmTree extends CharmTree {

  private final MartialArtsLevel standardLevel;

  public MartialArtsCharmTree(ICharmTemplate charmTemplate, IExaltedRuleSet rules) {
    super(charmTemplate.getMartialArtsCharms(rules));
    this.standardLevel = charmTemplate.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public boolean isLearnableCharm(ICharm charm) {
    return MartialArtsUtilities.getLevel(charm).compareTo(standardLevel) <= 1;
  }
}