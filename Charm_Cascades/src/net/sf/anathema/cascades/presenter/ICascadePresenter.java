package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICascadePresenter {

  public ICharmTree getCharmTree(IIdentificate type);
}