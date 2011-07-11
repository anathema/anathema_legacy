package net.sf.anathema.character.generic.impl.traits.alternate;

import net.sf.anathema.character.generic.impl.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.generic.impl.traits.range.AlternateRequirementTraitMinimum;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;

public class AlternateRequirementTraitTemplate extends AlternateRequirementTraitMinimum implements ITraitTemplate {

  private final int startValue;
  private final int zeroValue;
  private final ITraitLimitation limitation = new EssenceBasedLimitation();

  public AlternateRequirementTraitTemplate(
      ITraitRequirement requirement,
      TraitRequirementCollection collection,
      int startValue,
      int zeroValue) {
    super(requirement, collection);
    this.startValue = startValue;
    this.zeroValue = zeroValue;
  }

  public int getZeroLevelValue() {
    return zeroValue;
  }

  public int getStartValue() {
    return startValue;
  }

  public LowerableState getLowerableState() {
    return LowerableState.Default;
  }

  public ITraitLimitation getLimitation() {
    return limitation;
  }

  public boolean isRequiredFavored() {
    return false;
  }
  
  public String getTag()
  {
	  return null;
  }
}