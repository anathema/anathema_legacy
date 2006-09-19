package net.sf.anathema.demo.platform.view.util;

import net.sf.anathema.framework.view.util.MultiTabView;
import net.sf.anathema.framework.view.util.TabbedView;
import de.jdemo.extensions.SwingDemoCase;

public class MultiTabViewDemo extends SwingDemoCase {

  public void demoMultiTabView() {
    MultiTabView view = createDemoView();
    show(view.getComponent());
  }

  public void demoMultiTabViewInTabbedView() {
    TabbedView tabbedView = new TabbedView();
    tabbedView.addTab(createDemoView(), "MultiTabView"); //$NON-NLS-1$
    tabbedView.addTab(new DemoTabView(false), "Simple Demo View"); //$NON-NLS-1$
    show(tabbedView.getComponent());
  }

  private MultiTabView createDemoView() {
    MultiTabView view = new MultiTabView();
    view.addTabView(new DemoTabView(true), "First Scroll Needed"); //$NON-NLS-1$
    view.addTabView(new DemoTabView(true), "Second Scroll Needed"); //$NON-NLS-1$
    view.addTabView(new DemoTabView(false), "First No Scroll Needed"); //$NON-NLS-1$
    view.addTabView(new DemoTabView(false), "Second No Scroll Needed"); //$NON-NLS-1$
    view.initGui(null);
    return view;
  }
}