package net.sf.anathema.framework.presenter.view;

import javax.swing.JComponent;

import net.infonode.docking.RootWindow;
import net.infonode.docking.View;
import net.infonode.docking.util.DockingUtil;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;

public class MultiWindowContentView implements IMultiContentView {

  private final RootWindow window = new RootWindow(null);
  private final View additionalComponentView = new View("Overview", null, null);
  
  public MultiWindowContentView() {
    DockingUtil.addWindow(additionalComponentView, window);
  }

  public void addView(IView view, ContentProperties tabProperties) {
    JComponent content = view.getComponent();
    final View windowView = new View(tabProperties.getName(), null, content);
    DockingUtil.addWindow(windowView, window);
  }

  public void setAdditionalComponent(JComponent component) {
    additionalComponentView.setComponent(component);
  }

  public JComponent getComponent() {
    return window;
  }
}