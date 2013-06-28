package net.sf.anathema.character.generic.impl.traits;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.hero.model.Hero;

public abstract class AbstractTraitTemplate implements ITraitTemplate {
  protected final LowerableState lowerable;
  protected final int startValue;
  private final int zeroValue;

  public AbstractTraitTemplate(int startValue, LowerableState lowerable, int zeroValue) {
    this.startValue = startValue;
    this.lowerable = lowerable;
    this.zeroValue = zeroValue;
  }

  @Override
  public final int getStartValue() {
    return startValue;
  }

  @Override
  public final LowerableState getLowerableState() {
    return lowerable;
  }

  @Override
  public final int getZeroLevelValue() {
    return zeroValue;
  }

  @Override
  public boolean isRequiredFavored() {
    return false;
  }

  @Override
  public int getCalculationMinValue(Hero hero, TraitType type) {
    return getMinimumValue(hero);
  }

}