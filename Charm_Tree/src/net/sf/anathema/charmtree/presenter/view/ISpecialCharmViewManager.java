package net.sf.anathema.charmtree.presenter.view;

public interface ISpecialCharmViewManager<T extends ISVGSpecialCharmView> {

  void setSpecialCharmViewVisible(ICharmTreeView view, T charmView, boolean visible);
}