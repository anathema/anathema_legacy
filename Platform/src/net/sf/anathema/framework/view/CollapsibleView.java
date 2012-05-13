package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.toolbar.ToolBarButton;
import net.sf.anathema.lib.gui.IView;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXTitledPanel;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.BorderLayout;

public class CollapsibleView implements IView {

  private final JPanel panel = new JPanel(new BorderLayout());
  private final JPanel collapsible = new JPanel(new BorderLayout());
  private final JXCollapsiblePane collapsiblePane = new JXCollapsiblePane(JXCollapsiblePane.Direction.DOWN);
  private final JXTitledPanel titledPanel = new JXTitledPanel();

  public CollapsibleView() {
    collapsiblePane.add(collapsible, BorderLayout.CENTER);
    collapsiblePane.setAnimated(false);
    collapsiblePane.setCollapsed(true);
    collapsible.add(titledPanel, BorderLayout.CENTER);
    panel.add(collapsiblePane, BorderLayout.SOUTH);
    titledPanel.setRightDecoration(createCloseButton());
  }

  private JComponent createCloseButton() {
    ToolBarButton toolBarButton = new ToolBarButton();
    Action closeAction = getToggleAction();
    // TODO: ImageIcon verwenden
    Icon icon = UIManager.getIcon("Tree.expandedIcon");
    closeAction.putValue(JXCollapsiblePane.COLLAPSE_ICON, icon);
    closeAction.putValue(Action.NAME, null);
    toolBarButton.setAction(closeAction);
    return toolBarButton;
  }

  private Action getToggleAction() {
    return collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
  }

  public void setMainContent(JComponent content) {
    panel.add(content, BorderLayout.CENTER);
  }

  public void setCollapsibleContainer(JComponent content) {
    titledPanel.setContentContainer(content);
  }
  
  public void setCollapsibleTitle(String title) {
    titledPanel.setTitle(title);
  }

  public void expandCollapsible() {
    collapsiblePane.setCollapsed(false);
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }
}
