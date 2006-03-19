package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class TraitDependentMultiLearnableCharm extends AbstractMultiLearnableCharm {

  private final int absoluteLearnLimit;
  private final ITraitType traitType;

  public TraitDependentMultiLearnableCharm(String charmId, int absoluteLearnLimit, ITraitType traitType) {
    super(charmId);
    this.absoluteLearnLimit = absoluteLearnLimit;
    this.traitType = traitType;
  }

  public int getAbsoluteLearnLimit() {
    return absoluteLearnLimit;
  }

  public int getMaximumLearnCount(IGenericTraitCollection traitCollection) {
    IGenericTrait trait = traitCollection.getTrait(traitType);
    return trait.getCurrentValue();
  }
}