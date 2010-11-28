package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;

public class AlternateMinimumsTraitTemplate extends AbstractTraitTemplate {

  private final int defaultMinimum = 0;
  private final int demandedMinimum;
  private final ValuedTraitType alternativeType;
  private final ITraitLimitation limitation = new EssenceBasedLimitation();

  public AlternateMinimumsTraitTemplate(int startValue, ValuedTraitType alternativeType, int demandedMinimum) {
    super(startValue, LowerableState.Default, 0);
    this.alternativeType = alternativeType;
    this.demandedMinimum = demandedMinimum;
  }

  public ITraitLimitation getLimitation() {
    return limitation;
  }

  public int getMinimumValue(ILimitationContext context) {
    ITraitType alternateType = alternativeType.getType();
    IGenericTrait trait = context.getTraitCollection().getTrait(alternateType);
    if (trait.getCurrentValue() < alternativeType.getCurrentValue()) {
      return demandedMinimum;
    }
    return defaultMinimum;
  }
}