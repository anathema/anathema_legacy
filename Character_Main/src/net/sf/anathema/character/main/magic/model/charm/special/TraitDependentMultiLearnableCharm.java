package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;

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
    ValuedTraitType trait = context.getTrait(traitType);
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