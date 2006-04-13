package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.util.IIdentificate;

public interface IValueModel<E> extends IIdentificate {

  public E getValue();
}