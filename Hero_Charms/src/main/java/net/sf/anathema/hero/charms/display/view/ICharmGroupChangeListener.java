package net.sf.anathema.hero.charms.display.view;

import net.sf.anathema.platform.tree.display.TreeView;

public interface ICharmGroupChangeListener {

  void valueChanged(Object charmGroup, Object type);

  void operateOn(TreeView treeView);
}