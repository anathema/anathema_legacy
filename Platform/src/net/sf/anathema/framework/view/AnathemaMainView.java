package net.sf.anathema.framework.view;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.item.IComponentItemViewManagement;
import net.sf.anathema.framework.view.item.ItemViewManagement;
import net.sf.anathema.framework.view.menu.MainMenuBar;
import net.sf.anathema.framework.view.messaging.IStatusBar;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.framework.view.toolbar.AnathemaToolBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static net.sf.anathema.lib.gui.swing.GuiUtilities.displayOnScreenCenter;

public class AnathemaMainView implements MainView, IItemViewManagement, IWindow {

  private final AnathemaToolBar toolbar = new AnathemaToolBar();
  private final MainMenuBar menu;
  private final IComponentItemViewManagement itemViewManagement;
  private final AnathemaViewProperties properties;
  private final StatusBar statusBar = new StatusBar();

  public AnathemaMainView(AnathemaViewProperties properties) {
    this.properties = properties;
    this.menu = new MainMenuBar(properties.getMainMenuName(), properties.getHelpMenuName());
    this.itemViewManagement = createItemViewManagement();
  }

  private IComponentItemViewManagement createItemViewManagement() {
    return new ItemViewManagement();
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
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    JOptionPane.setRootFrame(frame);
    frame.setIconImage(properties.getFrameIcon());
    return frame;
  }

  private void initApplicationPanel(JPanel applicationPanel) {
    applicationPanel.add(toolbar.getComponent(), BorderLayout.NORTH);
    applicationPanel.add(itemViewManagement.getComponent(), BorderLayout.CENTER);
    applicationPanel.add(statusBar.getComponent(), BorderLayout.SOUTH);
  }

  @Override
  public IStatusBar getStatusBar() {
    return statusBar;
  }

  @Override
  public void show() {
    JFrame mainFrame = initGui();
    displayOnScreenCenter(mainFrame, new Dimension(1024, 768));
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
  public IWindow getWindow() {
    return this;
  }

  @Override
  public IMenuBar getMenuBar() {
    return menu;
  }
}