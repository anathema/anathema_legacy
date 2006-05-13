package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;

public interface ISimpleSpecialsEntryModel extends ISimpleSpecialsModel {

  public void reset();

  public void setDefenseValue(int newValue);

  public void setSpeed(int newValue);

  public TurnType[] getTurnTypes();

  public void setTurnType(TurnType newValue);
}
