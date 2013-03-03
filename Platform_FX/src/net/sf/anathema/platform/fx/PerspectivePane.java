package net.sf.anathema.platform.fx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
    navigationPanel.setMinWidth(200);
    navigationPanel.setPrefWidth(200);
    navigationPanel.setMaxWidth(200);
    BorderPane.setMargin(navigationPanel, new Insets(3));
    BorderPane.setMargin(contentPanel, new Insets(3));
    outerPane.setLeft(navigationPanel);
    outerPane.getStyleClass().add("perspective-outer-pane");
    navigationPanel.getStyleClass().add("perspective-navigation-pane");
    navigationPanel.getStyleClass().add("bordered-perspective-container");
    outerPane.setCenter(contentPanel);
    contentPanel.getStyleClass().add("perspective-content-pane");
    contentPanel.getStyleClass().add("bordered-perspective-container");
    Platform.runLater(new InitScene(bridgePanel, outerPane, getAllStyleSheetPaths(styleSheetPaths)));
  }

  private String[] getAllStyleSheetPaths(String[] styleSheetPaths) {
    return ArrayUtilities.concat(String.class, styleSheetPaths, "skin/anathema/perspective.css");
  }

  public void setNavigationComponent(Node component) {
    navigationPanel.setCenter(component);
  }

  public void setContentComponent(Node component) {
    contentPanel.setCenter(component);
  }

  @Override
  public JComponent getComponent() {
    return bridgePanel;
  }
}