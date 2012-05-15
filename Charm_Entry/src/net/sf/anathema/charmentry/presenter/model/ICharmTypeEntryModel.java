package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.control.IChangeListener;

public interface ICharmTypeEntryModel {

  CharmType[] getCharmTypes();

  void setCharmType(CharmType type);

  ISimpleSpecialsEntryModel getSimpleSpecialsModel();

  IReflexiveSpecialsEntryModel getReflexiveSpecialsModel();

  void addModelListener(IChangeListener inputListener);

  void setSpecialModelEnabled(boolean enabled);

  boolean isSpecialModelAvailable();

  CharmType getCharmType();

  boolean isSpecialModelEnabled();
}
