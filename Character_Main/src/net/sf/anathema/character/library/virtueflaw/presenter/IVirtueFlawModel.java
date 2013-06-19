package net.sf.anathema.character.library.virtueflaw.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public interface IVirtueFlawModel extends IAdditionalModel {

  boolean isVirtueFlawChangable();

  IVirtueFlaw getVirtueFlaw();

  TraitType[] getFlawVirtueTypes();

  void addVirtueFlawChangableListener(IBooleanValueChangedListener listener);
}