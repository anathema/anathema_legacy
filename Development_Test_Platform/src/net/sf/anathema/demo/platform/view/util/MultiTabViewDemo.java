package net.sf.anathema.demo.platform.view.util;

import net.sf.anathema.framework.presenter.view.SimpleViewTabContent;
import net.sf.anathema.framework.view.util.ContentProperties;
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
    tabbedView.addTab(new DemoView(), "Simple Demo View"); //$NON-NLS-1$
    show(tabbedView.getComponent());
  }

  private MultiTabView createDemoView() {
    MultiTabView view = new MultiTabView();
    new SimpleViewTabContent(new ContentProperties("First Scroll Needed").needsScrollbar(), new DemoView()).addTo(view); //$NON-NLS-1$
    new SimpleViewTabContent(new ContentProperties("Second Scroll Needed").needsScrollbar(), new DemoView()).addTo(view); //$NON-NLS-1$
    new SimpleViewTabContent(new ContentProperties("First No Scroll Needed"), new DemoView()).addTo(view); //$NON-NLS-1$
    new SimpleViewTabContent(new ContentProperties("Second No Scroll Needed"), new DemoView()).addTo(view); //$NON-NLS-1$
    view.initGui(null);
    return view;
  }
}