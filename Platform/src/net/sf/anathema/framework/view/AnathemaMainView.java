package net.sf.anathema.framework.view;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.item.IComponentItemViewManagement;
import net.sf.anathema.framework.view.item.ItemViewManagement;
import net.sf.anathema.framework.view.messaging.IStatusBar;
import net.sf.anathema.framework.view.toolbar.AnathemaToolBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class AnathemaMainView implements MainView, IItemViewManagement, ApplicationFrame {


  public final class TabbedItemListViewFactory implements ViewFactory {
    @Override
    public JComponent create() {
      JPanel content = new JPanel(new BorderLayout());
      content.add(toolbar.getComponent(), BorderLayout.NORTH);
      content.add(itemViewManagement.getComponent(), BorderLayout.CENTER);
      return content;
    }
  }

  private final IComponentItemViewManagement itemViewManagement;
  private final AnathemaToolBar toolbar = new AnathemaToolBar();
  private final SwingApplicationFrame applicationFrame;

  public AnathemaMainView(AnathemaViewProperties properties) {
    this.applicationFrame = new SwingApplicationFrame(properties, new TabbedItemListViewFactory());
    this.itemViewManagement = createItemViewManagement();
  }

  private IComponentItemViewManagement createItemViewManagement() {
    return new ItemViewManagement();
  }

  @Override
  public IStatusBar getStatusBar() {
    return applicationFrame.getStatusBar();
  }

  @Override
  public void show() {
    applicationFrame.show();
  }

  @Override
  public IAnathemaToolbar getToolbar() {
    return toolbar;
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

  @Override
  public IItemViewManagement getItemViewManagement() {
    return this;
  }

  @Override
  public ApplicationFrame getWindow() {
    return this;
  }

  @Override
  public IMenuBar getMenuBar() {
    return applicationFrame.getMenuBar();
  }
}