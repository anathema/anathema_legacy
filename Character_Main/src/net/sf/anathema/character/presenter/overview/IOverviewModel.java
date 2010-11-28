package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.util.IIdentificate;

public interface IOverviewModel extends IIdentificate {

  void accept(IOverviewModelVisitor visitor);

  String getCategoryId();

}
