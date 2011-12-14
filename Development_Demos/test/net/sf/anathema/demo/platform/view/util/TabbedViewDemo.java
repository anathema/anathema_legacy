package net.sf.anathema.demo.platform.view.util;

import de.jdemo.extensions.SwingDemoCase;
import de.jdemo.junit.DemoAsTestRunner;
import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IView;
import org.junit.runner.RunWith;

import javax.swing.*;
import java.awt.*;

@RunWith(DemoAsTestRunner.class)
public class TabbedViewDemo extends SwingDemoCase {

  public void demoViewThatNeedsScrollbar() {
    TabbedView view = new TabbedView(TabDirection.Up);
    view.addView(new DemoView(), "TabName"); //$NON-NLS-1$
    show(view.getComponent());
  }

  public void demoViewDoesNotNeedScrollbar() {
    TabbedView view = new TabbedView(TabDirection.Up);
    view.addView(new DemoView(), "TabName"); //$NON-NLS-1$
    show(view.getComponent());
  }

  public void demoTabbedPanelInTabView() {
    TabbedView view = new TabbedView(TabDirection.Left);
    view.addView(new IView() {
      @SuppressWarnings("serial")
      private TabbedPanel tabbedPanel = new TabbedPanel() {
        {
          JPanel panel = new JPanel(new BorderLayout());
          panel.add(new JLabel("Content"), BorderLayout.CENTER); //$NON-NLS-1$
          addTab(new TitledTab("Title", null, panel, null)); //$NON-NLS-1$
        }
      };

      public JComponent getComponent() {
        return tabbedPanel;
      }
    }, "TabName"); //$NON-NLS-1$
    show(view.getComponent());
  }
}