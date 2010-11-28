package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.impl.traits.limitation.VirtueBasedLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public class SimpleTraitTemplate extends AbstractTraitTemplate {

  public static ITraitTemplate createEssenceLimitedTemplate(int minimumValue) {
    return createEssenceLimitedTemplate(minimumValue, minimumValue, LowerableState.Default);
  }

  public static ITraitTemplate createEssenceLimitedTemplate(int minimumValue, int startValue, LowerableState state) {
    return new SimpleTraitTemplate(minimumValue, startValue, new EssenceBasedLimitation(), state);
  }

  public static ITraitTemplate createVirtueLimitedTemplate(
      int minimumValue,
      int startValue,
      LowerableState state,
      VirtueType type) {
    return new SimpleTraitTemplate(minimumValue, startValue, new VirtueBasedLimitation(type), state);
  }

  public static ITraitTemplate createStaticLimitedTemplate(int minimumValue, int staticLimit) {
    return createStaticLimitedTemplate(minimumValue, staticLimit, LowerableState.Default);
  }

  public static ITraitTemplate createStaticLimitedTemplate(int minimumValue, int staticLimit, LowerableState state) {
    return new SimpleTraitTemplate(minimumValue, minimumValue, new StaticTraitLimitation(staticLimit), state);
  }

  private final int minimumValue;
  private final ITraitLimitation limitation;

  private SimpleTraitTemplate(int minimumValue, int startValue, ITraitLimitation limitation, LowerableState lowerable) {
    super(startValue, lowerable, startValue);
    this.minimumValue = minimumValue;
    this.limitation = limitation;
  }

  public int getMinimumValue(ILimitationContext limitationContext) {
    return minimumValue;
  }

  public ITraitLimitation getLimitation() {
    return limitation;
  }
}