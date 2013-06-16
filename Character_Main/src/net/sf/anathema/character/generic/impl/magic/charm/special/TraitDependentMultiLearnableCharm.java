package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.LearnRangeContext;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;

public class TraitDependentMultiLearnableCharm extends AbstractMultiLearnableCharm {

  private final int absoluteLearnLimit;
  private final TraitType traitType;
  private final int countModifier;

  public TraitDependentMultiLearnableCharm(String charmId, int absoluteLearnLimit, TraitType traitType) {
    this(charmId, absoluteLearnLimit, traitType, 0);
  }

  public TraitDependentMultiLearnableCharm(String charmId, int absoluteLearnLimit, TraitType traitType, int modifier) {
    super(charmId);
    this.absoluteLearnLimit = absoluteLearnLimit;
    this.traitType = traitType;
    this.countModifier = modifier;
  }

  public int getModifier() {
    return countModifier;
  }

  public TraitType getTraitType() {
    return traitType;
  }

  @Override
  public int getAbsoluteLearnLimit() {
    return absoluteLearnLimit;
  }

  @Override
  public int getMaximumLearnCount(LearnRangeContext context) {
    GenericTrait trait = context.getTrait(traitType);
    int count = trait.getCurrentValue();
    count += countModifier;
    count = Math.max(count, 0);
    count = Math.min(count, absoluteLearnLimit);
    return count;
  }

  public String toString() {
    return "[" + getCharmId() + ";" + traitType + (countModifier != 0 ? ";" + countModifier : "") + "]";
  }
}