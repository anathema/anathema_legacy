package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.IBasicTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface ITrait extends IBasicTrait, IGenericTrait {

  public int getMinimalValue();

  public int getInitialValue();

  public int getMaximalValue();

  public void addCreationPointListener(IIntValueChangedListener listener);

  public void removeCreationPointListener(IIntValueChangedListener listener);

  public void addCurrentValueListener(IIntValueChangedListener listener);

  public void removeCurrentValueListener(IIntValueChangedListener listener);

  public int getCalculationValue();

  public int getZeroCalculationValue();

  public boolean isCreationLearned();

  public ISubTraitContainer createSpecialtiesContainer();
  
  public void accept(ITraitVisitor visitor);
}