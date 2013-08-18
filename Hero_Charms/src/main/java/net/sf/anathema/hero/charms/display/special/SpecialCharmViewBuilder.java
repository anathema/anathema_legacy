package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.platform.tree.display.SpecialNodeView;

public interface SpecialCharmViewBuilder {

  SpecialNodeView getResult();

  void reset();

  boolean hasResult();

  void buildFor(ISpecialCharm charm);
}
