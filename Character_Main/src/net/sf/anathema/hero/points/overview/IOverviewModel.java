package net.sf.anathema.hero.points.overview;

import net.sf.anathema.lib.util.Identifier;

public interface IOverviewModel extends Identifier {

  void accept(IOverviewModelVisitor visitor);

  String getCategoryId();
}
