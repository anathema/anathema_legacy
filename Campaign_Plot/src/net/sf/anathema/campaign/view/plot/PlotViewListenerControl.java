package net.sf.anathema.campaign.view.plot;

import net.sf.anathema.campaign.presenter.view.IPlotViewListener;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class PlotViewListenerControl {

  private final List<IPlotViewListener> plotViewListeners = new ArrayList<>();

  public void fireAddRequested(DefaultMutableTreeNode node) {
    for (IPlotViewListener listener : new ArrayList<>(plotViewListeners)) {
      listener.contentAddedRequested(node);
    }
  }

  public void fireRemoveRequested(DefaultMutableTreeNode node) {
    for (IPlotViewListener listener : new ArrayList<>(plotViewListeners)) {
      listener.removeRequested(node);
    }
  }

  public void fireMoveToRequested(DefaultMutableTreeNode node, int index) {
    for (IPlotViewListener listener : new ArrayList<>(plotViewListeners)) {
      listener.moveToRequested(node, index);
    }
  }

  public void fireMoveToRequested(DefaultMutableTreeNode dragTarget, DefaultMutableTreeNode node, int index) {
    for (IPlotViewListener listener : new ArrayList<>(plotViewListeners)) {
      listener.moveToRequested(dragTarget, node, index);
    }
  }

  public void fireSelectionChangedTo(DefaultMutableTreeNode node) {
    for (IPlotViewListener listener : new ArrayList<>(plotViewListeners)) {
      listener.selectionChangedTo(node);
    }
  }

  public void addPlotViewListener(IPlotViewListener listener) {
    plotViewListeners.add(listener);
  }
}