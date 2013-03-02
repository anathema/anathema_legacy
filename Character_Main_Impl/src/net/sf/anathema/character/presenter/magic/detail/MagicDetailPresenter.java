package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.IView;

public interface MagicDetailPresenter extends Presenter {

  MagicDetailModel getModel();

  String getDetailTitle();

  IView getView();
}
