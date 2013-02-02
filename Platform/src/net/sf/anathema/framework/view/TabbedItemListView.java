package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.item.IComponentItemViewManagement;
import net.sf.anathema.framework.view.item.ItemViewManagement;
import net.sf.anathema.framework.view.toolbar.AnathemaToolBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public final class TabbedItemListView implements ViewFactory, IItemViewManagement {
  private final IComponentItemViewManagement itemViewManagement;
  private final AnathemaToolBar toolBar = new AnathemaToolBar();

  TabbedItemListView() {
    this.itemViewManagement = new ItemViewManagement();
  }

  @Override
  public JComponent createContent() {
    JPanel content = new JPanel(new BorderLayout());
    content.add(toolBar.getComponent(), BorderLayout.NORTH);
    content.add(itemViewManagement.getComponent(), BorderLayout.CENTER);
    return content;
  }

  @Override
  public void addItemView(IItemView view, Action action) {
    itemViewManagement.addItemView(view, action);
  }

  @Override
  public void removeItemView(IItemView view) {
    itemViewManagement.removeItemView(view);
  }

  @Override
  public void addViewSelectionListener(IViewSelectionListener listener) {
    itemViewManagement.addViewSelectionListener(listener);
  }

  @Override
  public void setSelectedItemView(IItemView view) {
    itemViewManagement.setSelectedItemView(view);
  }

  public IAnathemaToolbar getToolBar() {
    return toolBar;
  }
}
