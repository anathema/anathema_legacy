package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.platform.tree.display.ContentFactory;

public interface SpecialCharmViewContainer {

  void registerSpecialType(Class contentClass, ContentFactory factory);
}