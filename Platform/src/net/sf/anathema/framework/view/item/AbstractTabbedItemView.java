package net.sf.anathema.framework.view.item;

import javax.swing.Icon;
import javax.swing.JComponent;

import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.framework.view.util.TabbedView;

public abstract class AbstractTabbedItemView extends AbstractItemView {

  private final TabbedView tabbedView = new TabbedView();
  private JComponent content;

  protected AbstractTabbedItemView(String name, Icon icon) {
    super(name, icon);
  }

  protected final void addTab(ITabView<?> cardView, final String name) {
    tabbedView.addTab(cardView, name);
  }

  public final JComponent getComponent() {
    if (content == null) {
      tabbedView.setTabAreaComponents(getTabAreaComponents());
      content = tabbedView.getComponent();
    }
    return content;
  }

  protected abstract JComponent[] getTabAreaComponents();
  
  protected final void setTabAreaComponents(JComponent[] components) {
    tabbedView.setTabAreaComponents(components);    
  }
}