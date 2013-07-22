package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.platform.tree.display.ContentFactory;
import net.sf.anathema.platform.tree.display.ISpecialNodeView;

public interface SpecialCharmViewContainer {

  void setSpecialCharmViewVisible(ISpecialNodeView charmView, boolean visible);

  void registerSpecialType(Class contentClass, ContentFactory factory);
}