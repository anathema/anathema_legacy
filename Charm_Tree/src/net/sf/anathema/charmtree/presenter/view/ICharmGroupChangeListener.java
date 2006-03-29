package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmGroupChangeListener {

  public void valueChanged(Object charmGroup, IIdentificate type, IExaltedEdition edition);
}