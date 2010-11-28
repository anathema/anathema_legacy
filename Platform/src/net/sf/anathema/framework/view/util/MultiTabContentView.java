package net.sf.anathema.framework.view.util;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.lib.gui.IView;

public class MultiTabContentView implements IMultiContentView {

  private final TabbedView tabbedView;
  private JPanel content;

  public MultiTabContentView() {
    this(TabDirection.Left);
  }

  public MultiTabContentView(TabDirection direction) {
    this.tabbedView = new TabbedView(direction);
  }

  public final JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new BorderLayout());
      content.add(tabbedView.getComponent(), BorderLayout.CENTER);
    }
    return content;
  }

  public void addView(IView view, ContentProperties tabProperties) {
    tabbedView.addView(view, tabProperties);
    tabbedView.getComponent().revalidate();
  }

  public void setAdditionalComponent(JComponent component) {
    tabbedView.setTabAreaComponents(new JComponent[] { component });
    tabbedView.getComponent().revalidate();
  }
}