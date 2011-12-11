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

  public int getCreationValue() {
    return creationValue;
  }

  public int getExperiencedValue() {
    return experiencedValue;
  }

  public int getAbsoluteMinValue() {
    return 0;
  }

  public int getCreationCalculationValue() {
    return creationValue;
  }

  public int getExperiencedCalculationValue() {
    return experiencedValue;
  }

  public boolean isLowerable() {
    return false;
  }

  public void resetCreationValue() {
    creationValue = 0;
  }

  public void resetExperiencedValue() {
    experiencedValue = creationValue;
  }

  public void setCreationValue(int value) {
    creationValue = value;
  }

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

  public int getCalculationValue() {
    return 0;
  }

  public int getZeroCalculationValue() {
    return 0;
  }
}