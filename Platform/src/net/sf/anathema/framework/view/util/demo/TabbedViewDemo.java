package net.sf.anathema.framework.view.util.demo;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.framework.view.util.TabDirection;
import net.sf.anathema.framework.view.util.TabbedView;
import de.jdemo.extensions.SwingDemoCase;

public class TabbedViewDemo extends SwingDemoCase {

  public void demo() {
    TabbedView view = new TabbedView(TabDirection.Up);
    view.addTab(new ISimpleTabView() {
      private JLabel content = new JLabel("Content"); //$NON-NLS-1$
    
      public boolean needsScrollbar() {
        return true;
      }
    
      public JComponent getComponent() {
        return content;
      }
    
    }, "TabName"); //$NON-NLS-1$
    show(view.getComponent());
  }
}