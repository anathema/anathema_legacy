package net.sf.anathema.framework.view.util;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.framework.view.util.collapsible.CollapsiblePane;
import net.sf.anathema.framework.view.util.collapsible.CollapsiblePaneMap;
import net.sf.anathema.framework.view.util.collapsible.ExpandOnlyThisPaneListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OptionalViewBar {
  private JPanel panel = new JPanel(new BorderLayout());
  private JPanel container = new JPanel(new FlowLayout(SwingConstants.CENTER, 0, 0));
  private JPanel buttonBar = new JPanel(new VerticalFlowLayout());
  private Map<String, CollapsiblePane> collapsiblePanesById = new HashMap<String, CollapsiblePane>();

  public OptionalViewBar() {
    container.setBorder(new EmptyBorder(0, 0, 0, 0));
    panel.add(buttonBar, BorderLayout.CENTER);
    panel.add(container, BorderLayout.WEST);
  }

  public void setView(String title, JComponent component) {
    boolean isNewId = !collapsiblePanesById.containsKey(title);
    if (isNewId) {
      CollapsiblePane collapsiblePane = addPaneForTitle(title);
      addButtonForTitle(title, collapsiblePane);
    }
    CollapsiblePane pane = collapsiblePanesById.get(title);
    pane.setContent(component);
  }

  private CollapsiblePane addPaneForTitle(String title) {
    CollapsiblePane collapsiblePane = new CollapsiblePane();
    collapsiblePanesById.put(title, collapsiblePane);
    container.add(collapsiblePane.getComponent());
    return collapsiblePane;
  }

  private void addButtonForTitle(String title, CollapsiblePane collapsiblePane) {
    JButton button = new JButton(title);
    ExpandOnlyThisPaneListener listener = new ExpandOnlyThisPaneListener(collapsiblePane, new CollapsiblePaneMap(collapsiblePanesById));
    button.addActionListener(listener);
    button.setUI(new TopDownButtonUI());
    buttonBar.add(button);
  }

  public JComponent getComponent() {
    return panel;
  }
}
