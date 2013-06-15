package net.sf.anathema.character.library.trait.visitor;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.LearnTrait;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.lib.control.IChangeListener;

public interface IDefaultTrait extends ITrait, LearnTrait, IModifiableCapTrait {

  void setCurrentValue(int value);

  int getMinimalValue();

  int getCalculationMinValue();

  void resetCurrentValue();

  void setModifiedCreationRange(int newInitialValue, int newUpperValue);

  void addRangeListener(IChangeListener listener);
}