package net.sf.anathema.framework.presenter.view;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.infonode.docking.RootWindow;
import net.infonode.docking.SplitWindow;
import net.infonode.docking.View;
import net.infonode.docking.util.DockingUtil;
import net.infonode.util.Direction;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;

public class MultiWindowContentView implements IMultiContentView {

  private final RootWindow contentWindow = new RootWindow(null);
  private final View additionalComponentView = new View("Overview", null, null);
  private final View controlComponentView = new View("Control", null, new JPanel());
  private final RootWindow mainRootWindow;

  public MultiWindowContentView() {
    controlComponentView.getWindowProperties().setCloseEnabled(false);
    contentWindow.getWindowBar(Direction.UP).setEnabled(true);
    contentWindow.getWindowBar(Direction.RIGHT).setEnabled(true);
    this.mainRootWindow = new RootWindow(null);
    // SplitWindow controlSplitWindow = new SplitWindow(false, controlComponentView, additionalComponentView);
    // controlSplitWindow.setDividerLocation(0.3f);
    // SplitWindow mainSplitWindow = new SplitWindow(true, controlSplitWindow, contentWindow);
    // mainSplitWindow.setDividerLocation(0.2f);
    additionalComponentView.getWindowProperties().setCloseEnabled(false);
    additionalComponentView.getWindowProperties().setUndockEnabled(false);
    additionalComponentView.getWindowProperties().setRestoreEnabled(false);
    DockingUtil.addWindow(additionalComponentView, contentWindow);
    contentWindow.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent event) {
        boolean overviewDemand = event.getKeyChar() == 'O'
            && event.getModifiersEx() == Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();        
        System.out.println(overviewDemand);
        if (overviewDemand) {
          additionalComponentView.makeVisible();
          event.consume();
        }        
      }
    });
    additionalComponentView.minimize(Direction.RIGHT);
  }

  public void addView(IView view, ContentProperties tabProperties) {
    JComponent content = view.getComponent();
    final View windowView = new View(tabProperties.getName(), null, content);
    windowView.getWindowProperties().setCloseEnabled(false);
    DockingUtil.addWindow(windowView, contentWindow);
  }

  public void setAdditionalComponent(JComponent component) {
    additionalComponentView.setComponent(component);
  }

  public JComponent getComponent() {
    return contentWindow;
  }
}