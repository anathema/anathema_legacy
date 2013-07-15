package net.sf.anathema.magic.description.display;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.gui.Presenter;

public interface MagicDetailPresenter extends Presenter {

  MagicDetailModel getModel();

  String getDetailTitle();

  IView getView();
}
