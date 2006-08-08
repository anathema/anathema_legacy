package net.sf.anathema.character.generic.framework.additionaltemplate.model;

public interface IModifiableBasicTrait extends IBasicTrait {

  public void resetCreationValue();

  public void resetExperiencedValue();

  public void setCreationValue(int value);

  public void setExperiencedValue(int value);
}