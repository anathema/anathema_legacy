package net.sf.anathema.framework.view.util.collapsible;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpandOnlyThisPaneListener implements ActionListener {
  private final CollapsiblePane collapsiblePane;
  private CollapsiblePaneMap collapsiblePaneMap;

  public ExpandOnlyThisPaneListener(CollapsiblePane collapsiblePane, CollapsiblePaneMap collapsiblePaneMap) {
    this.collapsiblePane = collapsiblePane;
    this.collapsiblePaneMap = collapsiblePaneMap;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    collapseOthers();
    toggleMyState();
  }

  private void toggleMyState() {
    collapsiblePane.toggleCollapsedState();
  }

  private void collapseOthers() {
    collapsiblePaneMap.collapseAllBut(collapsiblePane);
  }
}
