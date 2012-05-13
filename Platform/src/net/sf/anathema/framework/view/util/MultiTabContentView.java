package net.sf.anathema.framework.view.util;

import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.lib.gui.IView;
import org.jdesktop.swingx.JXCollapsiblePane;

import javax.swing.*;
import java.awt.*;

public class MultiTabContentView implements IMultiContentView {

  private final TabbedView tabbedView;
  private JPanel content;
  private JPanel rightSide = new JPanel(new BorderLayout());
  private JPanel additionalToolBar = new JPanel();
  private JXCollapsiblePane additionalViewPane = new JXCollapsiblePane(JXCollapsiblePane.Direction.RIGHT);

  public MultiTabContentView() {
    this(TabDirection.Left);
  }

  public MultiTabContentView(TabDirection direction) {
    this.tabbedView = new TabbedView(direction);
    rightSide.add(additionalToolBar, BorderLayout.EAST);
    rightSide.add(additionalViewPane, BorderLayout.CENTER);
    additionalViewPane.setCollapsed(true);
  }

  @Override
  public final JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new BorderLayout());
      content.add(tabbedView.getComponent(), BorderLayout.CENTER);
      content.add(rightSide, BorderLayout.EAST);
    }
    return content;
  }

  @Override
  public void addView(IView view, ContentProperties tabProperties) {
    tabbedView.addView(view, tabProperties);
    tabbedView.getComponent().revalidate();
  }

  @Override
  public void setAdditionalComponent(String title, JComponent component) {
    additionalViewPane.removeAll();
    additionalViewPane.add(component);
    additionalToolBar.removeAll();
    Action toggleAction = additionalViewPane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION);
    JButton toggleButton = new JButton(toggleAction);
    toggleButton.setText(title);
    toggleButton.setUI(new TopDownButtonUI());
    additionalToolBar.add(toggleButton);
  }
}