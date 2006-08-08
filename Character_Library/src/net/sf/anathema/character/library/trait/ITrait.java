package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.library.trait.specialty.ISpecialtiesContainer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface ITrait extends IBasicTrait, IModifiableGenericTrait {

  public int getInitialValue();

  public int getMinimalValue();

  public int getMaximalValue();

  public void addCreationPointListener(IIntValueChangedListener listener);

  public void removeCreationPointListener(IIntValueChangedListener listener);

  public void addCurrentValueListener(IIntValueChangedListener listener);

  public void removeCurrentValueListener(IIntValueChangedListener listener);

  public void resetCurrentValue();

  public int getCalculationValue();

  public int getZeroCalculationValue();

  public boolean isCreationLearned();

  public void setModifiedCreationRange(int newInitialValue, int newUpperValue);

  public void addRangeListener(IChangeListener listener);

  public ISpecialtiesContainer createSpecialtiesContainer();
}