package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface IAstrologicalHouse extends IIdentificate {

  IFavorableDefaultTrait[] getColleges();

  void addChangeListener(IChangeListener houseChangeListener);
}