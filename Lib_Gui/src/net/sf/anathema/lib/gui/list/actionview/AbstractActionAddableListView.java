package net.sf.anathema.lib.gui.list.actionview;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.getComponentSpacing;

public abstract class AbstractActionAddableListView<T> implements IActionAddableListView<T> {
  private final JPanel buttonPanel = new JPanel(new GridLayout(1, 0, getComponentSpacing(), getComponentSpacing()));
  private JPanel content;

  @Override
  public void addAction(Action action) {
    buttonPanel.add(new JButton(action));
  }

  public JComponent getComponent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  @Override
  public void refreshView() {
    getDisplayComponent().repaint();
  }

  protected abstract JComponent getDisplayComponent();

  protected abstract boolean isScrollable();

  private JPanel createContent() {
    JPanel panel = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
    JComponent component = isScrollable() ? new JScrollPane(getDisplayComponent()) : getDisplayComponent();
    panel.add(component, new CC().grow().push());
    panel.add(buttonPanel);
    return panel;
  }
}