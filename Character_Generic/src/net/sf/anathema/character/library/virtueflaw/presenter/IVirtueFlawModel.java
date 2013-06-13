package net.sf.anathema.character.library.virtueflaw.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public interface IVirtueFlawModel extends IAdditionalModel {

  boolean isVirtueFlawChangable();

  void addVirtueChangeListener(VirtueChangeListener listener);

  IVirtueFlaw getVirtueFlaw();

  ITraitType[] getFlawVirtueTypes();

  void addVirtueFlawChangableListener(IBooleanValueChangedListener listener);
}