package net.sf.anathema.charmentry.presenter;

import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ISimpleSpecialsEntryModel extends ISimpleSpecialsModel {

  public void reset();

  public void setDefenseValue(int newValue);

  public void setSpeed(int newValue);

  public void addChangeListener(IChangeListener listener);
}
