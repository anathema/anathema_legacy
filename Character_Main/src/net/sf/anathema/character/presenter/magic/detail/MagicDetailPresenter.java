package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.IView;

public interface MagicDetailPresenter extends IPresenter {

  MagicDetailModel getModel();

  String getDetailTitle();

  IView getView();
}
