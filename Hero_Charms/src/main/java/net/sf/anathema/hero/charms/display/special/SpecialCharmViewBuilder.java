package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.platform.tree.presenter.view.ISpecialNodeView;

public interface SpecialCharmViewBuilder {

  ISpecialNodeView getResult();

  void reset();

  boolean hasResult();

  void buildFor(ISpecialCharm charm);
}
