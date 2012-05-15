package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;

public interface IReflexiveSpecialsEntryModel extends IReflexiveSpecialsModel {

  void setSplitEnabled(boolean splitEnabled);

  void setStep(Integer newValue);

  void setDefenseStep(Integer newValue);
}