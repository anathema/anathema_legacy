package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IItemViewManagement;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.framework.view.ViewFactory;
import net.sf.anathema.framework.view.item.IComponentItemViewManagement;
import net.sf.anathema.framework.view.item.ItemViewManagement;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CampaignPerspectiveView implements ViewFactory, IItemViewManagement {
  private final IComponentItemViewManagement itemViewManagement;
  private final CampaignPerspectiveToolBar toolBar = new CampaignPerspectiveToolBar();

  public CampaignPerspectiveView() {
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

  public ToolBar getToolBar() {
    return toolBar;
  }
}
