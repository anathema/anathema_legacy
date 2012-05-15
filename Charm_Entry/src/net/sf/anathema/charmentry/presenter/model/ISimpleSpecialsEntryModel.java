package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;

public interface ISimpleSpecialsEntryModel extends ISimpleSpecialsModel {

  void reset();

  void setDefenseValue(int newValue);

  void setSpeed(int newValue);

  TurnType[] getTurnTypes();

  void setTurnType(TurnType newValue);
}
