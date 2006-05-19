package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.framework.magic.view.ISpecialCharmView;

public interface ISpecialCharmViewManager<T extends ISpecialCharmView> {

  void setSpecialCharmViewVisible(ICharmTreeView view, T charmView, boolean visible);
}