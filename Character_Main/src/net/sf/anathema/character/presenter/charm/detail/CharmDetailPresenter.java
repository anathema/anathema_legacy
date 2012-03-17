package net.sf.anathema.character.presenter.charm.detail;

import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.IView;

public interface CharmDetailPresenter extends IPresenter {

  CharmDetailModel getModel();

  String getDetailTitle();

  IView getView();
}
