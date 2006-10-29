package net.sf.anathema.framework.view.util;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.view.AbstractContentView;
import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.lib.gui.IView;

public class MultiTabView extends AbstractContentView<Object> implements IMultiContentView {

  private TabbedView tabbedView = new TabbedView(TabDirection.Up);

  @Override
  protected void createContent(JPanel panel, Object properties) {
    panel.setLayout(new BorderLayout());
    panel.add(tabbedView.getComponent(), BorderLayout.CENTER);
  }

  public void addView(IView view, ContentProperties tabProperties) {
    tabbedView.addTab(view, tabProperties);
    tabbedView.getComponent().revalidate();
  }
}