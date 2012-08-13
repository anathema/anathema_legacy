package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.platform.svgtree.presenter.view.ISpecialNodeView;

public interface SpecialCharmViewBuilder extends ISpecialCharmVisitor {
  ISpecialNodeView getResult();

  void reset();

  boolean hasResult();
}
