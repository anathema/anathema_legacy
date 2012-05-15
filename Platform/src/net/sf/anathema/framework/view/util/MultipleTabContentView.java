package net.sf.anathema.framework.view.util;

import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MultipleTabContentView implements IMultiContentView {

  private final TabbedView tabbedView;
  private JPanel content;
  private OptionalViewBar optionalViewPane = new OptionalViewBar();

  public MultipleTabContentView() {
    this(TabDirection.Left);
  }

  public MultipleTabContentView(TabDirection direction) {
    this.tabbedView = new TabbedView(direction);
  }

  @Override
  public final JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new BorderLayout());
      content.add(tabbedView.getComponent(), BorderLayout.CENTER);
      content.add(optionalViewPane.getComponent(), BorderLayout.EAST);
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
    optionalViewPane.setView(title, component);
  }
}