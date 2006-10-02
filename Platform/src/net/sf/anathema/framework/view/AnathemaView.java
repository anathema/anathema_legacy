package net.sf.anathema.framework.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.item.IItemViewManagement;
import net.sf.anathema.framework.view.item.ItemViewManagement;
import net.sf.anathema.framework.view.menu.AnathemaMenuBar;
import net.sf.anathema.framework.view.messaging.AnathemaStatusBar;
import net.sf.anathema.framework.view.toolbar.AnathemaToolBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;
import net.sf.anathema.lib.gui.GuiUtilities;

public class AnathemaView implements IAnathemaView {

  private final JFrame mainFrame;
  private final AnathemaToolBar toolbar = new AnathemaToolBar();
  private final AnathemaMenuBar menu;
  private final IItemViewManagement itemViewManagement;
  private final AnathemaViewProperties properties;
  private final AnathemaStatusBar statusBar = new AnathemaStatusBar();

  public AnathemaView(AnathemaViewProperties properties) {
    this.properties = properties;
    this.itemViewManagement = new ItemViewManagement();
    this.menu = new AnathemaMenuBar(properties.getMainMenuName(), properties.getHelpMenuName());
    itemViewManagement.setTabAreaComponents(statusBar.getComponent());
    this.mainFrame = initGui();
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

  public AnathemaStatusBar getStatusBar() {
    return statusBar;
  }

  public void showFrame() {
    GuiUtilities.displayOnScreenCenter(mainFrame, new Dimension(1024, 768));
  }

  public IAnathemaToolbar getToolbar() {
    return toolbar;
  }

  public void addItemView(IItemView view, Action action) {
    IItemViewManagement management = itemViewManagement;
    management.addItemView(view, action);
  }

  public void removeItemView(IItemView view) {
    IItemViewManagement management = itemViewManagement;
    management.removeItemView(view);
  }

  public void addViewSelectionListener(IViewSelectionListener listener) {
    itemViewManagement.addViewSelectionListener(listener);
  }

  public void removeViewSelectionListener(IViewSelectionListener listener) {
    itemViewManagement.removeViewSelectionListener(listener);
  }

  public void setSelectedItemView(IItemView view) {
    IItemViewManagement management = itemViewManagement;
    management.setSelectedItemView(view);
  }

  public void addWindowListener(WindowListener listener) {
    mainFrame.addWindowListener(listener);
  }

  public IMenuBar getMenuBar() {
    return menu;
  }
}