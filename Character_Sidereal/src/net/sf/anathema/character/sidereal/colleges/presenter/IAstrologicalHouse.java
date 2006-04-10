package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface IAstrologicalHouse extends IIdentificate {

  public IFavorableTrait[] getColleges();

  public void addChangeListener(IChangeListener houseChangeListener);
}