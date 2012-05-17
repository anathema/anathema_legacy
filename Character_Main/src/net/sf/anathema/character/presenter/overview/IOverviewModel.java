package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.util.Identified;

public interface IOverviewModel extends Identified {

  void accept(IOverviewModelVisitor visitor);

  String getCategoryId();

}
