package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;

public interface ICascadeViewFactory {

  public ICascadeView createCascadeView(ICharmTreeViewProperties viewProperties);
}