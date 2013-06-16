package net.sf.anathema.character.main.testing.dummy.trait;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.favorable.NullTraitFavorization;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public class DummyTrait implements Trait {

  public static DummyTrait createLearnTrait(TraitType type, int creationValue, int experiencedValue) {
    DummyTrait trait = new DummyTrait(type);
    trait.setCreationValue(creationValue);
    trait.setExperiencedValue(experiencedValue);
    trait.setCurrentValue(experiencedValue);
    return trait;
  }

  private int currentValue;
  private final TraitType type;
  private int creationValue;
  private int experiencedValue;

  public DummyTrait(TraitType type) {
    this(type, 0);
  }

  public DummyTrait(TraitType type, int value) {
    this.type = type;
    this.currentValue = value;
  }

  @Override
  public void setCurrentValue(int value) {
    this.currentValue = value;
  }

  @Override
  public TraitType getType() {
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
    return 0;
  }

  @Override
  public int getModifiedMaximalValue() {
    return 0;
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
  public void resetCreationValue() {
    creationValue = 0;
  }

  @Override
  public void resetExperiencedValue() {
    experiencedValue = creationValue;
  }

  @Override
  public void setCreationValue(int value) {
    this.creationValue = value;
  }

  @Override
  public void setExperiencedValue(int value) {
    this.experiencedValue = value;
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
    return creationValue;
  }

  @Override
  public int getCreationCalculationValue() {
    return creationValue;
  }

  @Override
  public int getCreationValue() {
    return creationValue;
  }

  @Override
  public int getExperiencedCalculationValue() {
    return experiencedValue;
  }

  @Override
  public int getExperiencedValue() {
    return experiencedValue;
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