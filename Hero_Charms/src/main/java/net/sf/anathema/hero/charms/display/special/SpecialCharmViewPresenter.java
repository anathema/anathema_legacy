package net.sf.anathema.hero.charms.display.special;

import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.platform.tree.display.TreeView;

public interface SpecialCharmViewPresenter extends Presenter {

  void showSpecialViews();

  void operateOn(TreeView treeView);
}