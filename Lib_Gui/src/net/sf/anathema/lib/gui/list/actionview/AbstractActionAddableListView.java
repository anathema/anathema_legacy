package net.sf.anathema.lib.gui.list.actionview;

import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.util.LayoutUtilities;

public abstract class AbstractActionAddableListView<T> implements IActionAddableListView<T> {
  private final JPanel buttonPanel = new JPanel(new GridLayout(
      1,
      0,
      LayoutUtilities.getComponentSpacing(),
      LayoutUtilities.getComponentSpacing()));
  private JPanel content;

  private final JLabel titleLabel = new JLabel();

  public AbstractActionAddableListView(String title) {
    titleLabel.setText(title);
  }

  public void addAction(Action action) {
    buttonPanel.add(new JButton(action));
  }

  private JPanel createContent() {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    panel.add(titleLabel);
    panel.add(
        isScrollable() ? new JScrollPane(getDisplayComponent()) : getDisplayComponent(),
        GridDialogLayoutData.FILL_BOTH);
    panel.add(buttonPanel);
    return panel;
  }

  protected abstract boolean isScrollable();

  protected abstract JComponent getDisplayComponent();

  public JComponent getContent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  public void setListTitle(String title) {
    titleLabel.setText(title);
  }

  public void refreshView() {
    getDisplayComponent().repaint();
  }
}
