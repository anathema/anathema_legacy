package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.LearnTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.lib.control.IChangeListener;

public interface IDefaultTrait extends ITrait, LearnTrait {

  void applyCapModifier(int modifier);

  int getModifiedMaximalValue();

  int getUnmodifiedMaximalValue();

  void setUncheckedCreationValue(int value);

  void setUncheckedExperiencedValue(int value);

  void setCurrentValue(int value);

  int getMinimalValue();

  int getCalculationMinValue();

  void resetCurrentValue();

  void setModifiedCreationRange(int newInitialValue, int newUpperValue);

  void addRangeListener(IChangeListener listener);
}