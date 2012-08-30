package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.GenericTraitProvider;
import net.sf.anathema.character.generic.traits.ITraitType;

public class EssenceFixedMultiLearnableCharm extends TraitDependentMultiLearnableCharm {

  public EssenceFixedMultiLearnableCharm(String charmId, int absoluteLearnLimit, ITraitType traitType) {
    super(charmId, absoluteLearnLimit, traitType);
  }

  @Override
  public int getMinimumLearnCount(GenericTraitProvider traitCollection) {
    return getMaximumLearnCount(traitCollection);
  }

}
