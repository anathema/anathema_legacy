package net.sf.anathema.character.presenter.magic.charm;

import net.sf.anathema.character.presenter.magic.detail.ShowMagicDetailListener;
import net.sf.anathema.platform.svgtree.presenter.view.CharmInteractionListener;

public class ShowDetailInteractionListener implements CharmInteractionListener {
  private final ShowMagicDetailListener listener;

  public ShowDetailInteractionListener(ShowMagicDetailListener listener) {
    this.listener = listener;
  }

  @Override
  public void nodeSelected(String nodeId) {
    // nothing to do
  }

  @Override
  public void nodeDetailsDemanded(String nodeId) {
    listener.showDetail(nodeId);
  }
}
