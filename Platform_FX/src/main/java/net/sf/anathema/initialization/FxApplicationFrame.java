package net.sf.anathema.initialization;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.ApplicationFrame;
import net.sf.anathema.framework.view.ViewFactory;
import net.sf.anathema.framework.view.menu.MainMenuBar;
import net.sf.anathema.framework.view.messaging.OneLineStatusBar;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.platform.tool.ImageContainer;
import net.sf.anathema.platform.tool.LoadImage;
import org.tbee.javafx.scene.layout.MigPane;

public class FxApplicationFrame implements ApplicationFrameView {
  private final Stage stage;
  private final AnathemaViewProperties properties;
  private final ViewFactory contentFactory;
  private final MainMenuBar menu;
  private final OneLineStatusBar statusBar = new OneLineStatusBar();

  public FxApplicationFrame(Stage stage, AnathemaViewProperties viewProperties, ViewFactory factory) {
    this.stage = stage;
    this.properties = viewProperties;
    this.contentFactory = factory;
    this.menu = new MainMenuBar(properties.getMainMenuName(), properties.getHelpMenuName());
    Parent contentPane = createContentPane();
    Scene scene = new Scene(contentPane);
    stage.setScene(scene);
  }

  public void show() {
    stage.setHeight(720);
    stage.setWidth(1280);
    stage.setTitle(properties.getDefaultFrameTitle());
    stage.getIcons().add(getFrameIcon());
    stage.show();
  }

  private Image getFrameIcon() {
    ImageView imageView = new ImageView();
    ImageContainer container = new LoadImage(properties.getFrameIcon()).run();
    container.displayIn(imageView);
    return imageView.getImage();
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


  private Parent createContentPane() {
    MigPane contentPane = new MigPane(new LC().fill().wrapAfter(1));
    contentPane.add(menu.getMenuBar(), new CC().dockNorth());
    contentPane.add(contentFactory.createContent(), new CC().grow().push());
    contentPane.add(statusBar.getComponent(), new CC().dockSouth());
    return contentPane;
  }
}
