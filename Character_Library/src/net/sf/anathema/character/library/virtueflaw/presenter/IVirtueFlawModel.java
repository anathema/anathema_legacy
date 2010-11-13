package net.sf.anathema.character.library.virtueflaw.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public interface IVirtueFlawModel extends IAdditionalModel {
  public boolean isVirtueFlawChangable();

  public void addVirtueChangeListener(VirtueChangeListener listener);

  public IVirtueFlaw getVirtueFlaw();

  public ITraitType[] getFlawVirtueTypes();

  public void addVirtueFlawChangableListener(IBooleanValueChangedListener listener);
}