package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;

public class TraitDependentMultiLearnableCharm extends AbstractMultiLearnableCharm {

  private final int absoluteLearnLimit;
  private final ITraitType traitType;
  private final int countModifier;
  
  public TraitDependentMultiLearnableCharm(String charmId, int absoluteLearnLimit, ITraitType traitType)
  {
	  this(charmId, absoluteLearnLimit, traitType, 0);
  }

  public TraitDependentMultiLearnableCharm(String charmId, int absoluteLearnLimit, ITraitType traitType, int modifier) {
    super(charmId);
    this.absoluteLearnLimit = absoluteLearnLimit;
    this.traitType = traitType;
    this.countModifier = modifier;
  }

  public int getAbsoluteLearnLimit() {
    return absoluteLearnLimit;
  }

  public int getMaximumLearnCount(IGenericTraitCollection traitCollection) {
    IGenericTrait trait = traitCollection.getTrait(traitType);
    int count = trait.getCurrentValue();
    count += countModifier;
    count = Math.max(count, 0);
    count = Math.min(count, absoluteLearnLimit);
    return count;
  }
}