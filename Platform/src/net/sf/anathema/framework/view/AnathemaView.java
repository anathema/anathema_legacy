package net.sf.anathema.framework.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.item.IComponentItemViewManagement;
import net.sf.anathema.framework.view.item.WindowItemViewManagement;
import net.sf.anathema.framework.view.menu.AnathemaMenuBar;
import net.sf.anathema.framework.view.messaging.AnathemaStatusBar;
import net.sf.anathema.framework.view.messaging.IAnathemaStatusBar;
import net.sf.anathema.framework.view.toolbar.AnathemaToolBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.lib.gui.GuiUtilities;

public class AnathemaView implements IAnathemaView {

  private final AnathemaToolBar toolbar = new AnathemaToolBar();
  private final AnathemaMenuBar menu;
  private final IComponentItemViewManagement itemViewManagement;
  private final AnathemaViewProperties properties;
  private final AnathemaStatusBar statusBar = new AnathemaStatusBar();

  public AnathemaView(AnathemaViewProperties properties) {
    this.properties = properties;
    this.menu = new AnathemaMenuBar(properties.getMainMenuName(), properties.getHelpMenuName());
    this.itemViewManagement = createItemViewManagement();
  }

  private IComponentItemViewManagement createItemViewManagement() {
    WindowItemViewManagement management = new WindowItemViewManagement();
    //    management.setTabAreaComponents(statusBar.getComponent());
    return management;
  }

  private JFrame initGui() {
    JFrame applicationFrame = createApplicationFrame();
    applicationFrame.setExtendedState(properties.getLaunchState());
    JPanel applicationPanel = new JPanel(new BorderLayout());
    initApplicationPanel(applicationPanel);
    applicationFrame.getContentPane().add(applicationPanel);
    applicationFrame.setJMenuBar(menu.getMenuBar());
    return applicationFrame;
  }

  private JFrame createApplicationFrame() {
    JFrame frame = new JFrame(properties.getDefaultFrameTitle());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JOptionPane.setRootFrame(frame);
    frame.setIconImage(properties.getFrameIcon());
    return frame;
  }

  private void initApplicationPanel(JPanel applicationPanel) {
    applicationPanel.add(toolbar.getComponent(), BorderLayout.NORTH);
    applicationPanel.add(itemViewManagement.getComponent(), BorderLayout.CENTER);
  }

  public IAnathemaStatusBar getStatusBar() {
    return statusBar;
  }

  public void showFrame() {
    JFrame mainFrame = initGui();
    GuiUtilities.displayOnScreenCenter(mainFrame, new Dimension(1024, 768));
  }

  public IAnathemaToolbar getToolbar() {
    return toolbar;
  }

  public void addItemView(IItemView view, Action action) {
    itemViewManagement.addItemView(view, action);
  }

  public void removeItemView(IItemView view) {
    itemViewManagement.removeItemView(view);
  }

  public void addViewSelectionListener(IViewSelectionListener listener) {
    itemViewManagement.addViewSelectionListener(listener);
  }

  public void setSelectedItemView(IItemView view) {
    itemViewManagement.setSelectedItemView(view);
  }

  public IMenuBar getMenuBar() {
    return menu;
  }
}