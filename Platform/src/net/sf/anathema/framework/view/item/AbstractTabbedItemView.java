package net.sf.anathema.framework.view.item;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.view.util.TabbedView;
import net.sf.anathema.lib.gui.IView;

public abstract class AbstractTabbedItemView extends AbstractItemView {

  private final TabbedView tabbedView = new TabbedView();
  private JComponent content;

  protected AbstractTabbedItemView(String name, Icon icon) {
    super(name, icon);
  }

  protected final void addTab(IView viewContent, final String name) {
    tabbedView.addTab(viewContent, new ContentProperties(name));
  }

  public final JComponent getComponent() {
    if (content == null) {
      setTabAreaComponents(getTabAreaComponents());
      content = tabbedView.getComponent();
    }
    return content;
  }

  protected abstract JComponent[] getTabAreaComponents();

  protected final void setTabAreaComponents(JComponent[] components) {
    tabbedView.setTabAreaComponents(components);
  }
}