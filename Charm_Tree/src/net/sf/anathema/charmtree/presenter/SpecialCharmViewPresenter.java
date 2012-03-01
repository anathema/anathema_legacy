package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.lib.gui.IPresenter;

public interface SpecialCharmViewPresenter extends IPresenter {

  void resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();

  void showSpecialViews();
}