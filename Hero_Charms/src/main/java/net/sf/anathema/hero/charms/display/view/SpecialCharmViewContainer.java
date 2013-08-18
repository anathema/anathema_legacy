package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.hero.charms.display.special.ToggleButtonSpecialNodeView;
import net.sf.anathema.platform.tree.display.CategorizedSpecialNodeView;
import net.sf.anathema.platform.tree.display.ContentFactory;

public interface SpecialCharmViewContainer {

  void registerSpecialType(Class contentClass, ContentFactory factory);

  ToggleButtonSpecialNodeView createToggleButtonSpecialView();

  CategorizedSpecialNodeView createCategorizedSpecialView();
}