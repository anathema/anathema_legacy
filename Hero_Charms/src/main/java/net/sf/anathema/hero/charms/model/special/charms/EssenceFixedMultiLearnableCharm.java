package net.sf.anathema.hero.charms.model.special.charms;

import net.sf.anathema.character.main.magic.charm.special.LearnRangeContext;
import net.sf.anathema.character.main.traits.EssenceTemplate;
import net.sf.anathema.character.main.traits.types.OtherTraitType;

public class EssenceFixedMultiLearnableCharm extends TraitDependentMultiLearnableCharm {

  public EssenceFixedMultiLearnableCharm(String charmId) {
    super(charmId, EssenceTemplate.SYSTEM_ESSENCE_MAX, OtherTraitType.Essence);
  }

  @Override
  public int getMinimumLearnCount(LearnRangeContext learnRangeContext) {
    return getMaximumLearnCount(learnRangeContext);
  }
}