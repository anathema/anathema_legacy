package net.sf.anathema.platform.svgtree.presenter.view;

public interface ISpecialCharmViewManager<T extends ISVGSpecialCharmView> {

  void setSpecialCharmViewVisible(ICharmTreeView view, T charmView, boolean visible);
}