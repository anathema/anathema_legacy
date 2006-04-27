package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IReflexiveSpecialsEntryModel extends IReflexiveSpecialsModel {

  public void addChangeListener(IChangeListener listener);

  public boolean isSplitEnabled();

  public void setSplitEnabled(boolean splitEnabled);

  public void setStep(int newValue);

  public void setDefenseStep(int newValue);

  public void reset();

}
