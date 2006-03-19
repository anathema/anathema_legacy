package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.framework.magic.view.IMultiLearnableCharmView;

public interface ISpecialCharmViewManager<T extends IMultiLearnableCharmView> {

  void setSpecialCharmViewVisible(ICharmTreeView view, T charmView, boolean visible);
}