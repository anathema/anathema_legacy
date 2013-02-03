package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;

public class EssenceTemplate extends AbstractTraitTemplate {

  public static final int SYSTEM_ESSENCE_MAX = 10;

  public static ITraitTemplate createExaltTemplate() {
    return new EssenceTemplate(2, 2, SYSTEM_ESSENCE_MAX);
  }

  private final ITraitLimitation limitation;

  private EssenceTemplate(int startValue, int zeroValue, int maxValue) {
    super(startValue, LowerableState.Default, zeroValue);
    this.limitation = new StaticTraitLimitation(maxValue);
  }

  @Override
  public int getMinimumValue(ILimitationContext limitationContext) {
    return getStartValue();
  }

  @Override
  public ITraitLimitation getLimitation() {
    return limitation;
  }
}