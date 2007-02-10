package net.sf.anathema.cascades.module;

import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.platform.svgtree.presenter.view.ICharmTreeViewProperties;

public interface ICascadeViewFactory {

  public ICascadeView createCascadeView(ICharmTreeViewProperties viewProperties);
}