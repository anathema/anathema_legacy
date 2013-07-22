package net.sf.anathema.hero.charms.model.special;

import com.google.common.base.Predicate;
import net.sf.anathema.platform.tree.display.TreeView;

public interface SpecialCharmList {
  void add(ISpecialCharm charm);

  void showViews();

  void setVisibilityPredicate(Predicate<String> predicate);

  void operateOn(TreeView treeView);
}
