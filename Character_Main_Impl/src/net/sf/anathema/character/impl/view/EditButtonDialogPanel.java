package net.sf.anathema.character.impl.view;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class EditButtonDialogPanel implements IGridDialogPanel {
  private final IGridDialogPanel dialogPanel = new DefaultGridDialogPanel();
  private final Map<Integer, JPanel> editPanelsByRow = new HashMap<Integer, JPanel>();
  private int currentRow = 0;

  public void add(final IDialogComponent component) {
    final JPanel editPanel = new JPanel(new GridLayout());
    editPanelsByRow.put(currentRow++, editPanel);
    dialogPanel.add(new IDialogComponent() {
      public void fillInto(JPanel panel, int columnCount) {
        component.fillInto(panel, columnCount);
        panel.add(editPanel);
      }

      public int getColumnCount() {
        return component.getColumnCount() + 1;
      }
    });
  }

  public void addVerticalSpacing(int height) {
    dialogPanel.addVerticalSpacing(height);
  }

  public JPanel getContent() {
    return dialogPanel.getContent();
  }

  public void addEditAction(SmartAction action, int row) {
    JPanel panel = editPanelsByRow.get(row);
    JButton button = new JButton(action);
    if (action.getName() == null && action.getIcon() != null) {
      button.setPreferredSize(IconToggleButton.getPreferredSize(action.getIcon()));
    }
    panel.add(button);
  }
}