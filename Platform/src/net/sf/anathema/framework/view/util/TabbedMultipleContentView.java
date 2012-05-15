package net.sf.anathema.framework.view.util;

import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;

public class TabbedMultipleContentView implements MultipleContentView {

  private final TabbedView tabbedView;

  public TabbedMultipleContentView() {
    this(TabDirection.Left);
  }

  public TabbedMultipleContentView(TabDirection direction) {
    this.tabbedView = new TabbedView(direction);
  }

  @Override
  public final JComponent getComponent() {
    return tabbedView.getComponent();
  }

  @Override
  public void addView(IView view, ContentProperties tabProperties) {
    tabbedView.addView(view, tabProperties);
    tabbedView.getComponent().revalidate();
  }
}