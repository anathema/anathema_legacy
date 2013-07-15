package net.sf.anathema.character.main.traits;

public interface LearnTrait {

  int getCreationValue();

  int getExperiencedValue();

  int getAbsoluteMinValue();

  int getCreationCalculationValue();

  int getExperiencedCalculationValue();

  boolean isLowerable();

  int getCalculationValue();

  int getZeroCalculationValue();

  void resetCreationValue();

  void resetExperiencedValue();

  void setCreationValue(int value);

  void setExperiencedValue(int value);
}