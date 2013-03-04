package net.sf.anathema.platform.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.lang.ArrayUtilities;

import javax.swing.JComponent;

public class PerspectivePane implements IView {

  private final JFXPanel bridgePanel = new JFXPanel();
  private final BorderPane outerPane = new BorderPane();
  private final BorderPane navigationPanel = new BorderPane();
  private final BorderPane contentPanel = new BorderPane();

  public PerspectivePane(String... styleSheetPaths) {
    FxThreading.assertNotOnFxThread();
    navigationPanel.setMinWidth(200);
    navigationPanel.setPrefWidth(200);
    navigationPanel.setMaxWidth(200);
    initBorderedPane(navigationPanel, "perspective-navigation-pane");
    initBorderedPane(contentPanel, "perspective-content-pane");
    outerPane.getStyleClass().add("perspective-outer-pane");
    outerPane.setLeft(navigationPanel);
    outerPane.setCenter(contentPanel);
    Platform.runLater(new InitScene(bridgePanel, outerPane, getAllStyleSheetPaths(styleSheetPaths)));
  }

  private void initBorderedPane(Parent pane, String basicStyleClass) {
    BorderPane.setMargin(pane, new Insets(3));
    pane.getStyleClass().add(basicStyleClass);
    pane.getStyleClass().add("bordered-perspective-container");
  }

  private String[] getAllStyleSheetPaths(String[] styleSheetPaths) {
    return ArrayUtilities.concat(String.class, styleSheetPaths, "skin/anathema/perspective.css");
  }

  public void setNavigationComponent(Node component) {
    FxThreading.assertOnFxThread();
    navigationPanel.setCenter(component);
  }

  public void setContentComponent(Node component) {
    FxThreading.assertOnFxThread();
    contentPanel.setCenter(component);
  }

  @Override
  public JComponent getComponent() {
    return bridgePanel;
  }
}