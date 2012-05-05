package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import org.jmock.example.announcer.Announcer;

public class AggregatingInteractionListener implements NodeInteractionListener {
  private final Announcer<NodeInteractionListener> interactionListeners = Announcer.to(NodeInteractionListener.class);

  @Override
  public void nodeSelected(String nodeId) {
    interactionListeners.announce().nodeSelected(nodeId);
  }

  @Override
  public void nodeDetailsDemanded(String nodeId) {
    interactionListeners.announce().nodeDetailsDemanded(nodeId);

  }

  public void addNodeInteractionListener(NodeInteractionListener listener) {
    interactionListeners.addListener(listener);
  }
}
