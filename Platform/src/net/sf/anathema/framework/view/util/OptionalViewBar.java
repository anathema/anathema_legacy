package net.sf.anathema.framework.view.util;

import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class OptionalViewBar {
  private JPanel buttonBar = new JPanel(new VerticalFlowLayout());
  private List<String> registeredIds = new ArrayList<String>();

  public void setView(String title, JXCollapsiblePane pane) {
    boolean isNewId = !registeredIds.contains(title);
    if (isNewId) {
      addButtonForTitle(title, pane);
      registeredIds.add(title);
    }
  }

  private void addButtonForTitle(String title, JXCollapsiblePane collapsiblePane) {
    JButton button = new JButton(collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
    button.setText(title);
    button.setUI(new TopDownButtonUI());
    buttonBar.add(button);
  }

  public JComponent getComponent() {
    return buttonBar;
  }
}
