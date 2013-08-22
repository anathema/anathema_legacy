package net.sf.anathema.platform.fx;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class PerspectivePane {

  private BorderPane outerPane;
  private BorderPane navigationPanel = new BorderPane();
  private BorderPane contentPanel = new BorderPane();

  public PerspectivePane(final String... styleSheetPaths) {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new InitNavigationPane());
    Platform.runLater(new InitContentPane());
    Platform.runLater(new InitOuterPane());
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        String[] allStyleSheetPaths = getAllStyleSheetPaths(styleSheetPaths);
        for (String sheetPath : allStyleSheetPaths) {
          new Stylesheet(sheetPath).applyToParent(outerPane);
        }
      }
    });
  }

  private void initBorderedPane(Parent pane, String basicStyleClass) {
    BorderPane.setMargin(pane, new Insets(3));
    pane.getStyleClass().add(basicStyleClass);
    pane.getStyleClass().add("bordered-perspective-container");
  }

  private String[] getAllStyleSheetPaths(String[] styleSheetPaths) {
    return ArrayUtilities.concat(String.class, styleSheetPaths, "skin/platform/perspective.css");
  }

  public void setNavigationComponent(Node component) {
    FxThreading.assertOnFxThread();
    navigationPanel.setCenter(component);
  }

  public void setContentComponent(Node component) {
    FxThreading.assertOnFxThread();
    contentPanel.setCenter(component);
  }

  public void addStyleSheetClass(String styleClass) {
    outerPane.getStyleClass().add(styleClass);
  }

  public Node getNode() {
    return outerPane;
  }

  private class InitOuterPane implements Runnable {
    @Override
    public void run() {
      outerPane = new BorderPane();
      outerPane.getStyleClass().add("perspective-outer-pane");
      outerPane.setLeft(navigationPanel);
      outerPane.setCenter(contentPanel);
    }
  }

  private class InitNavigationPane implements Runnable {
    @Override
    public void run() {
      navigationPanel.setMinWidth(200);
      navigationPanel.setPrefWidth(200);
      navigationPanel.setMaxWidth(200);
      initBorderedPane(navigationPanel, "perspective-navigation-pane");
    }
  }

  private class InitContentPane implements Runnable {
    @Override
    public void run() {
      initBorderedPane(contentPanel, "perspective-content-pane");
    }
  }
}