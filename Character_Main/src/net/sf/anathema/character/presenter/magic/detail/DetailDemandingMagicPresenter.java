package net.sf.anathema.character.presenter.magic.detail;

import net.sf.anathema.lib.gui.SubPresenter;

public interface DetailDemandingMagicPresenter extends SubPresenter {

  void addShowDetailListener(final ShowMagicDetailListener listener);
}
