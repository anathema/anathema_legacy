package net.sf.anathema.framework.view.util;

import net.sf.anathema.framework.view.util.collapsible.CollapsiblePane;
import net.sf.anathema.framework.view.util.collapsible.CollapsiblePaneMap;
import net.sf.anathema.framework.view.util.collapsible.ExpandOnlyThisPaneListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
    expandFirstPane(pane);
  }

  private void expandFirstPane(CollapsiblePane pane) {
    if (collapsiblePanesById.size() == 1) {
      pane.expand();
    }
  }

  private CollapsiblePane addPaneForTitle(String title) {
    CollapsiblePane collapsiblePane = new CollapsiblePane();
    collapsiblePanesById.put(title, collapsiblePane);
    container.add(collapsiblePane.getComponent());
    return collapsiblePane;
  }

  private void addButtonForTitle(String title, final CollapsiblePane collapsiblePane) {
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
