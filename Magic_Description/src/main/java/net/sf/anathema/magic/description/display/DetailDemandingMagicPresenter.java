package net.sf.anathema.magic.description.display;

import net.sf.anathema.lib.gui.SubPresenter;

public interface DetailDemandingMagicPresenter extends SubPresenter{

  void addShowDetailListener(ShowMagicDetailListener listener);
}
