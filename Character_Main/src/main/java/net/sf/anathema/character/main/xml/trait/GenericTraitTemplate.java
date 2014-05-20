package net.sf.anathema.character.main.xml.trait;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.ModificationType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class GenericTraitTemplate extends ReflectionCloneableObject<IClonableTraitTemplate> implements IClonableTraitTemplate {

  private Integer minimumValue;
  private Integer startValue = 0;
  private ModificationType modificationType;
  private ITraitLimitation limitation;
  private boolean isRequiredFavored;

  @Override
  public ITraitLimitation getLimitation() {
    return limitation;
  }

  @Override
  public ModificationType getModificationType() {
    return modificationType;
  }

  @Override
  public int getStartValue() {
    return startValue;
  }

  @Override
  public int getMinimumValue(Hero hero) {
    return minimumValue;
  }

  public final void setLimitation(ITraitLimitation limitation) {
    this.limitation = limitation;
  }

  public final void setModificationType(ModificationType modificationType) {
    this.modificationType = modificationType;
  }

  public final void setMinimumValue(int minimumValue) {
    this.minimumValue = minimumValue;
  }

  public final void setStartValue(int startValue) {
    if (startValue > this.startValue) {
      this.startValue = startValue;
    }
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
}