package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.character.main.magic.charm.special.charms.ISpecialCharm;
import net.sf.anathema.platform.tree.display.ISpecialNodeView;

public interface SpecialCharmViewBuilder {

  ISpecialNodeView getResult();

  void reset();

  boolean hasResult();

  void buildFor(ISpecialCharm charm);
}
