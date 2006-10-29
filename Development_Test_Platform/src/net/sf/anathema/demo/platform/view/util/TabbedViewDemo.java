package net.sf.anathema.demo.platform.view.util;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.infonode.tabbedpanel.TabbedPanel;
import net.infonode.tabbedpanel.titledtab.TitledTab;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IView;
import de.jdemo.extensions.SwingDemoCase;

public class TabbedViewDemo extends SwingDemoCase {

  public void demoViewThatNeedsScrollbar() {
    TabbedView view = new TabbedView(TabDirection.Up);
    view.addTab(new DemoView(), "TabName"); //$NON-NLS-1$
    show(view.getComponent());
  }

  public void demoViewDoesNotNeedScrollbar() {
    TabbedView view = new TabbedView(TabDirection.Up);
    view.addTab(new DemoView(), "TabName"); //$NON-NLS-1$
    show(view.getComponent());
  }

  public void demoTabbedPanelInTabView() {
    TabbedView view = new TabbedView(TabDirection.Left);
    view.addTab(new IView() {

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