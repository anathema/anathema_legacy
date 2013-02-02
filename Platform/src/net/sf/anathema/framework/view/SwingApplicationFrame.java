package net.sf.anathema.framework.view;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.menu.MainMenuBar;
import net.sf.anathema.framework.view.messaging.OneLineStatusBar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static net.sf.anathema.lib.gui.swing.GuiUtilities.displayOnScreenCenter;

public class SwingApplicationFrame implements ApplicationFrame {

  public static final Dimension DEFAULT_SIZE = new Dimension(1024, 768);
  private final AnathemaViewProperties properties;
  private ViewFactory contentFactory;
  private final MainMenuBar menu;
  private final OneLineStatusBar statusBar = new OneLineStatusBar();

  public SwingApplicationFrame(AnathemaViewProperties properties, ViewFactory contentFactory) {
    this.properties = properties;
    this.contentFactory = contentFactory;
    this.menu = new MainMenuBar(properties.getMainMenuName(), properties.getHelpMenuName());
  }

  public void show() {
    JFrame applicationFrame = initFrame();
    applicationFrame.getContentPane().add(createContentPane());
    applicationFrame.setJMenuBar(menu.getMenuBar());
    displayOnScreenCenter(applicationFrame, DEFAULT_SIZE);
  }

  public MainMenuBar getMenuBar() {
    return menu;
  }

  public OneLineStatusBar getStatusBar() {
    return statusBar;
  }

  private JFrame initFrame() {
    JFrame applicationFrame = createApplicationFrame();
    applicationFrame.setExtendedState(properties.getLaunchState());
    return applicationFrame;
  }

  private JFrame createApplicationFrame() {
    JFrame frame = new JFrame(properties.getDefaultFrameTitle());
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    JOptionPane.setRootFrame(frame);
    frame.setIconImage(properties.getFrameIcon());
    return frame;
  }

  private JComponent createContentPane() {
    JPanel contentPane = new JPanel(new BorderLayout());
    contentPane.add(contentFactory.createContent(), BorderLayout.CENTER);
    contentPane.add(statusBar.getComponent(), BorderLayout.SOUTH);
    return contentPane;
  }
}
