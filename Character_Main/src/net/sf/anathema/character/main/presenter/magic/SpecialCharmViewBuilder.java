package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.platform.tree.presenter.view.ISpecialNodeView;

public interface SpecialCharmViewBuilder {
  ISpecialNodeView getResult();

  void reset();

  boolean hasResult();

  void buildFor(ISpecialCharm charm);
}
