package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.toolbar.ToolBarButton;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXTitledPanel;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.BorderLayout;

public class CollapsiblePane {

  private final JPanel panel = new JPanel(new BorderLayout());
  private final JPanel collapsible = new JPanel(new BorderLayout());
  private final JXCollapsiblePane collapsiblePane = new JXCollapsiblePane(JXCollapsiblePane.Direction.DOWN);

  public CollapsiblePane() {
    collapsiblePane.add(collapsible, BorderLayout.CENTER);
    collapsiblePane.setAnimated(true);
    collapsiblePane.setCollapsed(true);
    panel.add(collapsiblePane, BorderLayout.SOUTH);
  }

  private JComponent createCloseButton() {
    ToolBarButton toolBarButton = new ToolBarButton();
    Action closeAction = collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
    // TODO: ImageIcon verwenden
    Icon icon = UIManager.getIcon("Tree.expandedIcon");
    closeAction.putValue(JXCollapsiblePane.COLLAPSE_ICON, icon);
    closeAction.putValue(Action.NAME, null);
    toolBarButton.setAction(closeAction);
    return toolBarButton;
  }

  public void setMainContent(JComponent content) {
    panel.add(content, BorderLayout.CENTER);
  }

  public void setCollapsibleContent(String title, JComponent content) {
    JXTitledPanel titledPanel = new JXTitledPanel(title);
    titledPanel.getContentContainer().add(content);
    titledPanel.setRightDecoration(createCloseButton());
    collapsible.add(titledPanel, BorderLayout.CENTER);
  }

  public JComponent getContent() {
    return panel;
  }
}
