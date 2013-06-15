package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.lib.util.Identifier;

public interface IOverviewModel extends Identifier {

  void accept(IOverviewModelVisitor visitor);

  String getCategoryId();

}
