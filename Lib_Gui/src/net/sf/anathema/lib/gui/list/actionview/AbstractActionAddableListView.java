package net.sf.anathema.lib.gui.list.actionview;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.getComponentSpacing;

public abstract class AbstractActionAddableListView<T> implements IActionAddableListView<T> {
  private final JPanel buttonPanel = new JPanel(new GridLayout(
      1,
      0,
      getComponentSpacing(),
      getComponentSpacing()));
  private JPanel content;

  private final JLabel titleLabel;

  public AbstractActionAddableListView(String title) {
    titleLabel = title != null ? new JLabel(title) : null;
  }

  @Override
  public void addAction(Action action) {
    buttonPanel.add(new JButton(action));
  }

  private JPanel createContent() {
    JPanel panel = new JPanel(new MigLayout(new LC().insets("0").fill().wrapAfter(1)));
    if (titleLabel != null) {
      panel.add(titleLabel);
    }
    panel.add(
        isScrollable() ? new JScrollPane(getDisplayComponent()) : getDisplayComponent(),
        new CC().grow().push());
    panel.add(buttonPanel);
    return panel;
  }

  protected abstract boolean isScrollable();

  protected abstract JComponent getDisplayComponent();

  @Override
  public JComponent getComponent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  @Override
  public void setListTitle(String title) {
    titleLabel.setText(title);
  }

  @Override
  public void refreshView() {
    getDisplayComponent().repaint();
  }
}