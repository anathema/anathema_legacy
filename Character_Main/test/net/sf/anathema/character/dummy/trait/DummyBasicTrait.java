package net.sf.anathema.character.dummy.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IModifiableBasicTrait;
import net.sf.anathema.lib.data.Range;

public class DummyBasicTrait implements IModifiableBasicTrait {

  private int experiencedValue;
  private int creationValue;

  public DummyBasicTrait(int creationValue, int experiencedValue) {
    this.creationValue = creationValue;
    this.experiencedValue = experiencedValue;
  }

  @Override
  public int getCreationValue() {
    return creationValue;
  }

  @Override
  public int getExperiencedValue() {
    return experiencedValue;
  }

  @Override
  public int getAbsoluteMinValue() {
    return 0;
  }

  @Override
  public int getCreationCalculationValue() {
    return creationValue;
  }

  @Override
  public int getExperiencedCalculationValue() {
    return experiencedValue;
  }

  @Override
  public boolean isLowerable() {
    return false;
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
    creationValue = value;
  }

  @Override
  public void setExperiencedValue(int value) {
    experiencedValue = value;
  }

  public Range getCreationPointRange() {
    return new Range(0, 5);
  }

  public int getCreationMaxValue() {
    return 0;
  }

  public int getAbsoluteMaxValue() {
    return getCreationPointRange().getUpperBound();
  }

  @Override
  public int getCalculationValue() {
    return 0;
  }

  @Override
  public int getZeroCalculationValue() {
    return 0;
  }
}