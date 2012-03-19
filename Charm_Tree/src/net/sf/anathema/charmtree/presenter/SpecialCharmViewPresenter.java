package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.lib.gui.Presenter;

public interface SpecialCharmViewPresenter extends Presenter {

  void resetSpecialViewsAndTooltipsWhenCursorLeavesCharmArea();

  void showSpecialViews();
}
