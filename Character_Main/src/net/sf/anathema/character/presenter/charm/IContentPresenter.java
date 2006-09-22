package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.framework.presenter.view.ITabContent;
import net.sf.anathema.lib.gui.IPresenter;

public interface IContentPresenter extends IPresenter {

  public ITabContent getTabContent();
}