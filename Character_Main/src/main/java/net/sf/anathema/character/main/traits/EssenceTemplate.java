package net.sf.anathema.character.main.traits;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.hero.model.Hero;

public class EssenceTemplate extends AbstractTraitTemplate {

  public static final int SYSTEM_ESSENCE_MAX = 10;

  public static ITraitTemplate createExaltTemplate() {
    return new EssenceTemplate(2, 2, SYSTEM_ESSENCE_MAX);
  }

  private final ITraitLimitation limitation;

  private EssenceTemplate(int startValue, int zeroValue, int maxValue) {
    super(startValue, ModificationType.RaiseOnly, zeroValue);
    this.limitation = new StaticTraitLimitation(maxValue);
  }

  @Override
  public int getMinimumValue(Hero hero) {
    return getStartValue();
  }

  @Override
  public ITraitLimitation getLimitation() {
    return limitation;
  }
}