package net.sf.anathema.demo.platform.view.util;

import de.jdemo.extensions.SwingDemoCase;
import de.jdemo.junit.DemoAsTestRunner;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.MultiTabContentView;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedView;
import org.junit.runner.RunWith;

@RunWith(DemoAsTestRunner.class)
public class MultiTabViewDemo extends SwingDemoCase {

  public void demoMultiTabView() {
    MultiTabContentView view = createDemoView();
    show(view.getComponent());
  }

  public void demoMultiTabViewInTabbedView() {
    TabbedView tabbedView = new TabbedView();
    tabbedView.addView(createDemoView(), "MultiTabView"); //$NON-NLS-1$
    tabbedView.addView(new DemoView(), "Simple Demo View"); //$NON-NLS-1$
    show(tabbedView.getComponent());
  }

  private MultiTabContentView createDemoView() {
    MultiTabContentView view = new MultiTabContentView(TabDirection.Up);
    new SimpleViewContent(new ContentProperties("First Scroll Needed").needsScrollbar(), new DemoView()).addTo(view); //$NON-NLS-1$
    new SimpleViewContent(new ContentProperties("Second Scroll Needed").needsScrollbar(), new DemoView()).addTo(view); //$NON-NLS-1$
    new SimpleViewContent(new ContentProperties("First No Scroll Needed"), new DemoView()).addTo(view); //$NON-NLS-1$
    new SimpleViewContent(new ContentProperties("Second No Scroll Needed"), new DemoView()).addTo(view); //$NON-NLS-1$
    return view;
  }
}