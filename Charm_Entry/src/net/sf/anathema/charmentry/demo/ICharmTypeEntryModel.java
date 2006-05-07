package net.sf.anathema.charmentry.demo;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.charmentry.model.IReflexiveSpecialsEntryModel;
import net.sf.anathema.charmentry.presenter.ISimpleSpecialsEntryModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ICharmTypeEntryModel {

  public CharmType[] getCharmTypes();

  public void setCharmType(CharmType type);

  public ISimpleSpecialsEntryModel getSimpleSpecialsModel();

  public IReflexiveSpecialsEntryModel getReflexiveSpecialsModel();

  public void addModelListener(IChangeListener inputListener);

  public void setSpecialModelEnabled(boolean enabled);

  public boolean isSpecialModelAvailable();

  public CharmType getCharmType();
}
