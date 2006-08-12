package net.sf.anathema.character.library.trait.aggregated;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.visitor.IAggregatedTrait;

public class AggregatedTraitTemplate implements ITraitTemplate {

  private final ITraitTemplate template;
  private final String subname;
  private final int startValue;
  private final ITraitType traitType;

  public AggregatedTraitTemplate(ITraitTemplate template, ITraitType traitType, String subname, int startValue) {
    this.template = template;
    this.traitType = traitType;
    this.subname = subname;
    this.startValue = startValue;
  }

  public ITraitLimitation getLimitation() {
    return template.getLimitation();
  }

  public LowerableState getLowerableState() {
    return template.getLowerableState();
  }

  public int getStartValue() {
    return startValue;
  }

  public int getZeroLevelValue() {
    return template.getZeroLevelValue();
  }

  public boolean isRequiredFavored() {
    return template.isRequiredFavored();
  }

  public int getMinimumValue(ILimitationContext collection) {
    IAggregatedTrait trait = (IAggregatedTrait) collection.getTrait(traitType);
    int necessaryMinimumValue = template.getMinimumValue(collection);
    for(ISubTrait subTrait : trait.getSubTraits().getSubTraits()) {
      if (subTrait.getName().equals(subname)) {
        continue;
      }
      if (subTrait.getCurrentValue() >= necessaryMinimumValue) {
        return 0;
      }
    }
    return necessaryMinimumValue;
  }
}