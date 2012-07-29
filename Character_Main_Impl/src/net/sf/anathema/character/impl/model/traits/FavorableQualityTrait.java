package net.sf.anathema.character.impl.model.traits;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.NullTraitFavorization;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.exaltedengine.Attribute;
import net.sf.anathema.exaltedengine.NumericValue;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public class FavorableQualityTrait implements IFavorableTrait, IDefaultTrait {
  private final Attribute quality;
  private int initialValue;

  public FavorableQualityTrait(Attribute quality) {
    this.quality = quality;
    this.initialValue = getCurrentValue();
  }

  @Override
  public ITraitFavorization getFavorization() {
    return new NullTraitFavorization();
  }

  @Override
  public boolean isCasteOrFavored() {
    return false;
  }

  @Override
  public int getInitialValue() {
    return initialValue;
  }

  @Override
  public int getMaximalValue() {
    return initialValue;
  }

  @Override
  public void addCreationPointListener(IIntValueChangedListener listener) {
  
  }

  @Override
  public void removeCreationPointListener(IIntValueChangedListener listener) {
  
  }

  @Override
  public void addCurrentValueListener(IIntValueChangedListener listener) {
  
  }

  @Override
  public void removeCurrentValueListener(IIntValueChangedListener listener) {
  
  }

  @Override
  public void accept(ITraitVisitor visitor) {
    visitor.visitDefaultTrait(this);
  }

  @Override
  public ITraitType getType() {
    for (AttributeType attributeType : AttributeType.values()) {
      if (quality.isCalled(new Name(attributeType.name()))) {
        return attributeType;
      }
    }
    throw new RuntimeException("Unknown Trait Type");
  }

  @Override
  public int getCurrentValue() {
    int currentValue = 0;
    while(quality.isGreaterThan(new NumericValue(currentValue))) {
      currentValue++;
    }
    return currentValue;
  }

  @Override
  public int getMinimalValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getCalculationMinValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void resetCurrentValue() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setModifiedCreationRange(int newInitialValue, int newUpperValue) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addRangeListener(IChangeListener listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void resetCreationValue() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void resetExperiencedValue() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setCreationValue(int value) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setExperiencedValue(int value) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getCreationValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getExperiencedValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getAbsoluteMinValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getCreationCalculationValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getExperiencedCalculationValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean isLowerable() {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getCalculationValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getZeroCalculationValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void applyCapModifier(int modifier) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getModifiedMaximalValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getUnmodifiedMaximalValue() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setUncheckedExperiencedValue(int value) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setCurrentValue(int value) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
