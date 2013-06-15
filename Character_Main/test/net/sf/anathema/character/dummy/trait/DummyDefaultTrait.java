package net.sf.anathema.character.dummy.trait;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.favorable.NullTraitFavorization;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public class DummyDefaultTrait implements IDefaultTrait {

  private int currentValue;
  private final ITraitType type;

  public DummyDefaultTrait(ITraitType type) {
    this(type, 0);
  }

  public DummyDefaultTrait(ITraitType type, int value) {
    this.type = type;
    this.currentValue = value;
  }

  @Override
  public void setCurrentValue(int value) {
    this.currentValue = value;
  }

  @Override
  public ITraitType getType() {
    return type;
  }

  @Override
  public int getCurrentValue() {
    return currentValue;
  }

  @Override
  public boolean isCasteOrFavored() {
    return false;
  }

  @Override
  public void addRangeListener(IChangeListener listener) {
    //not yet implemented
  }

  @Override
  public int getMinimalValue() {
    return 0;
  }

  @Override
  public void applyCapModifier(int modifier) {
    // not yet implemented
  }

  @Override
  public int getUnmodifiedMaximalValue() {
    // not yet implemented
    return 0;
  }

  @Override
  public int getModifiedMaximalValue() {
    // not yet implemented
    return 0;
  }

  public void validate() {
    // not yet implemented
  }

  @Override
  public void resetCurrentValue() {
    //not yet implemented
  }

  @Override
  public void setModifiedCreationRange(int newInitialValue, int newUpperValue) {
    //not yet implemented
  }

  @Override
  public void accept(ITraitVisitor visitor) {
    visitor.visitDefaultTrait(this);
  }

  @Override
  public void addCreationPointListener(IIntValueChangedListener listener) {
    //not yet implemented
  }

  @Override
  public void addCurrentValueListener(IIntValueChangedListener listener) {
    //not yet implemented
  }

  @Override
  public int getCalculationMinValue() {
    return 0;
  }

  @Override
  public ITraitFavorization getFavorization() {
    return new NullTraitFavorization();
  }

  @Override
  public int getInitialValue() {
    return 0;
  }

  @Override
  public int getMaximalValue() {
    return 0;
  }

  @Override
  public void removeCreationPointListener(IIntValueChangedListener listener) {
    //not yet implemented
  }

  @Override
  public void removeCurrentValueListener(IIntValueChangedListener listener) {
    //not yet implemented
  }

  @Override
  public void resetCreationValue() {
    //not yet implemented
  }

  @Override
  public void resetExperiencedValue() {
    //not yet implemented
  }

  @Override
  public void setCreationValue(int value) {
    //not yet implemented
  }

  @Override
  public void setExperiencedValue(int value) {
    //not yet implemented
  }

  @Override
  public void setUncheckedCreationValue(int value) {
    //not yet implemented
  }

  @Override
  public void setUncheckedExperiencedValue(int value) {
    //not yet implemented
  }

  @Override
  public int getAbsoluteMinValue() {
    return 0;
  }

  @Override
  public int getCalculationValue() {
    return 0;
  }

  @Override
  public int getCreationCalculationValue() {
    return 0;
  }

  @Override
  public int getCreationValue() {
    return 0;
  }

  @Override
  public int getExperiencedCalculationValue() {
    return 0;
  }

  @Override
  public int getExperiencedValue() {
    return 0;
  }

  @Override
  public int getZeroCalculationValue() {
    return 0;
  }

  @Override
  public boolean isLowerable() {
    return false;
  }
}