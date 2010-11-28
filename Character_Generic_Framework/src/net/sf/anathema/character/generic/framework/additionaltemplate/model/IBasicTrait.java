package net.sf.anathema.character.generic.framework.additionaltemplate.model;

public interface IBasicTrait {

  public int getCreationValue();

  public int getExperiencedValue();

  public int getAbsoluteMinValue();

  public int getCreationCalculationValue();

  public int getExperiencedCalculationValue();

  public boolean isLowerable();

  public int getCalculationValue();

  public int getZeroCalculationValue();
}