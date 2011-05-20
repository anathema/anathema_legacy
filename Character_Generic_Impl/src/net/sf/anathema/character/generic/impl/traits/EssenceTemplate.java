package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;

public class EssenceTemplate extends AbstractTraitTemplate {

  public static final int DB_ESSENCE_MAX = 7;
  public static final int SYSTEM_ESSENCE_MAX = 10;

  public static ITraitTemplate createDbTemplate() {
    return createDbTemplate(2);
  }

  public static ITraitTemplate createDbTemplate(int startValue) {
    return new EssenceTemplate(startValue, 2, DB_ESSENCE_MAX);
  }

  public static ITraitTemplate createExaltTemplate() {
    return new EssenceTemplate(2, 2, SYSTEM_ESSENCE_MAX);
  }

  public static ITraitTemplate createMortalTemplate(int maxValue) {
    return new EssenceTemplate(1, 1, maxValue);
  }

  private final ITraitLimitation limitation;

  private EssenceTemplate(int startValue, int zeroValue, int maxValue) {
    super(startValue, LowerableState.Default, zeroValue);
    this.limitation = new StaticTraitLimitation(maxValue);
  }

  public int getMinimumValue(ILimitationContext limitationContext) {
    return getStartValue();
  }

  public ITraitLimitation getLimitation() {
    return limitation;
  }
}