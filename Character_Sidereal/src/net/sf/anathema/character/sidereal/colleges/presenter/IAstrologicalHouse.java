package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.character.library.trait.IFavorableDefaultTrait;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identified;

public interface IAstrologicalHouse extends Identified {

  IFavorableDefaultTrait[] getColleges();

  void addChangeListener(IChangeListener houseChangeListener);
}