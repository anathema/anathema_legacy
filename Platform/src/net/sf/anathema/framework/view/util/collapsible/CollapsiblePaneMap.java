package net.sf.anathema.framework.view.util.collapsible;

import java.util.Map;

public class CollapsiblePaneMap {

  private final Map<String, CollapsiblePane> collapsiblePanesById;

  public CollapsiblePaneMap(Map<String, CollapsiblePane> collapsiblePanesById) {
    this.collapsiblePanesById = collapsiblePanesById;
  }


  public void collapseAllBut(CollapsiblePane collapsiblePane) {
    for (CollapsiblePane pane : collapsiblePanesById.values())  {
      collapseIfNot(pane, collapsiblePane);
    }
  }

  private void collapseIfNot(CollapsiblePane paneToCollapse, CollapsiblePane collapsiblePane) {
    if (paneToCollapse != collapsiblePane) {
      paneToCollapse.collapse();
    }
  }

}
