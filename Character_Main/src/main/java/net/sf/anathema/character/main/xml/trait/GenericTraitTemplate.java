package net.sf.anathema.character.main.xml.trait;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.LowerableState;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericTraitTemplate extends ReflectionCloneableObject<IClonableTraitTemplate> implements IClonableTraitTemplate {

  private Integer minimumValue;
  private Integer zeroLevelValue;
  private Integer startValue = 0;
  private LowerableState lowerableState;
  private ITraitLimitation limitation;
  private boolean isRequiredFavored;
  private boolean isFreebie;

  @Override
  public ITraitLimitation getLimitation() {
    return limitation;
  }

  @Override
  public LowerableState getLowerableState() {
    return lowerableState;
  }

  @Override
  public int getStartValue() {
    return startValue;
  }

  @Override
  public int getZeroLevelValue() {
    return zeroLevelValue;
  }

  @Override
  public int getMinimumValue(Hero hero) {
    return minimumValue;
  }

  public final void setLimitation(ITraitLimitation limitation) {
    this.limitation = limitation;
  }

  public final void setLowerableState(LowerableState lowerableState) {
    this.lowerableState = lowerableState;
  }

  public final void setMinimumValue(int minimumValue) {
    this.minimumValue = minimumValue;
  }

  public final void setStartValue(int startValue) {
    if (startValue > this.startValue) {
      this.startValue = startValue;
    }
  }

  public final void setZeroLevelValue(int zeroLevelValue) {
    this.zeroLevelValue = zeroLevelValue;
  }

  @Override
  public GenericTraitTemplate clone() {
    return (GenericTraitTemplate) super.clone();
  }

  public void setRequiredFavored(boolean isRequiredFavored) {
    this.isRequiredFavored = isRequiredFavored;
  }

  @Override
  public boolean isRequiredFavored() {
    return isRequiredFavored;
  }

  public void setIsFreebie(boolean value) {
    isFreebie = value;
  }

  @Override
  public int getCalculationMinValue(Hero hero, TraitType type) {
    return isFreebie ? getMinimumValue(hero) : 0;
  }
}