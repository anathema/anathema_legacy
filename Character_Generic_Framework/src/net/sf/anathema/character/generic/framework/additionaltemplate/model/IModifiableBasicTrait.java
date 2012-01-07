package net.sf.anathema.character.generic.framework.additionaltemplate.model;

public interface IModifiableBasicTrait extends IBasicTrait {

  void resetCreationValue();

  void resetExperiencedValue();

  void setCreationValue(int value);

  void setExperiencedValue(int value);
}