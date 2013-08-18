package net.sf.anathema.framework.view.util;

import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class OptionalViewBar {
  private JPanel buttonBar = new JPanel(new VerticalFlowLayout());
  private List<String> registeredIds = new ArrayList<>();

  public void setView(String title, OptionalView view) {
    boolean isNewId = !registeredIds.contains(title);
    if (isNewId) {
      addButtonForTitle(title, view);
      registeredIds.add(title);
    }
  }

  private void addButtonForTitle(String title, final OptionalView view) {
    JButton button = new JButton(new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        view.toggle();
      }
    });
    button.setText(title);
    button.setUI(new TopDownButtonUI());
    buttonBar.add(button);
  }

  public JComponent getComponent() {
    return buttonBar;
  }
}
