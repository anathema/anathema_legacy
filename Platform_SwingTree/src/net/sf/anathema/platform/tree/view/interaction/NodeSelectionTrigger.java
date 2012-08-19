package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.NodeToggleListener;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

public class NodeSelectionTrigger implements Closure {
  private final Cascade cascade;
  private final NodeInteractionListener interactionListener;
  private final ToggleListener listener = new ToggleListener();

  public NodeSelectionTrigger(Cascade cascade, NodeInteractionListener interactionListener) {
    this.cascade = cascade;
    this.interactionListener = interactionListener;
  }

  @Override
  public void execute(InteractiveGraphicsElement polygon) {
    cascade.addToggleListener(listener);
    polygon.toggle();
    cascade.removeToggleListener(listener);
  }

  private class ToggleListener implements NodeToggleListener {
    @Override
    public void toggled(String id) {
      interactionListener.nodeSelected(id);
    }
  }
}