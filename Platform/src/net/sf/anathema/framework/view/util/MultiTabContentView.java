package net.sf.anathema.framework.view.util;

import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MultiTabContentView implements IMultiContentView {

  private final TabbedView tabbedView;
  private JPanel content;

  public MultiTabContentView() {
    this(TabDirection.Left);
  }

  public MultiTabContentView(TabDirection direction) {
    this.tabbedView = new TabbedView(direction);
  }

  @Override
  public final JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new BorderLayout());
      content.add(tabbedView.getComponent(), BorderLayout.CENTER);
    }
    return content;
  }

  @Override
  public void addView(IView view, ContentProperties tabProperties) {
    tabbedView.addView(view, tabProperties);
    tabbedView.getComponent().revalidate();
  }

  @Override
  public void setAdditionalComponent(JComponent component) {
    tabbedView.setTabAreaComponents(component);
    tabbedView.getComponent().revalidate();
  }
}