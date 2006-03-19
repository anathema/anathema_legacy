package net.sf.anathema.character.sidereal.colleges.presenter;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.lib.util.IIdentificate;

public interface IAstrologicalHouse extends IIdentificate {

  public IFavorableTrait[] getColleges();

  public void addChangeListener(ChangeListener houseChangeListener);
}