package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.lib.gui.Presenter;

public interface IContentPresenter extends Presenter {

  public IViewContent getTabContent();
}
