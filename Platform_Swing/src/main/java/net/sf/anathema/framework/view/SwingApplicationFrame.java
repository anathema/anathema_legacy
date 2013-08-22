package net.sf.anathema.framework.view;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.menu.MainMenuBar;
import net.sf.anathema.framework.view.messaging.OneLineStatusBar;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.initialization.ApplicationFrameView;
import net.sf.anathema.platform.fx.BridgingPanel;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Dimension;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static net.sf.anathema.lib.gui.swing.GuiUtilities.displayOnScreenCenter;

public class SwingApplicationFrame implements ApplicationFrameView {

  public static final Dimension DEFAULT_SIZE = new Dimension(1280, 720);
  private final AnathemaViewProperties properties;
  private ViewFactory contentFactory;
  private final MainMenuBar menu;
  private final OneLineStatusBar statusBar = new OneLineStatusBar();

  public SwingApplicationFrame(AnathemaViewProperties properties, ViewFactory contentFactory) {
    this.properties = properties;
    this.contentFactory = contentFactory;
    this.menu = new MainMenuBar(properties.getMainMenuName(), properties.getHelpMenuName());
  }

  public static JComponent getParentComponent() {
    return (JComponent) ((JFrame) JOptionPane.getRootFrame()).getContentPane();
  }

  public void show() {
    JFrame applicationFrame = initFrame();
    BridgingPanel bridgingPanel = new BridgingPanel();
    final Node contentPane = createContentPane();
    bridgingPanel.init(new NodeHolder() {
      @Override
      public Node getNode() {
        return contentPane;
      }
    });
    applicationFrame.getContentPane().add(bridgingPanel.getComponent());
    applicationFrame.setJMenuBar(menu.getMenuBar());
    displayOnScreenCenter(applicationFrame, DEFAULT_SIZE);
  }

  @Override
  public ApplicationFrame getWindow() {
    return this;
  }

  public MainMenuBar getMenuBar() {
    return menu;
  }

  public StatusBar getStatusBar() {
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

  private Node createContentPane() {
    MigPane contentPane = new MigPane(new LC().fill().wrapAfter(1));
    contentPane.add(contentFactory.createContent(), new CC().grow().push());
    contentPane.add(statusBar.getComponent(), new CC().dockSouth());
    return contentPane;
  }
}