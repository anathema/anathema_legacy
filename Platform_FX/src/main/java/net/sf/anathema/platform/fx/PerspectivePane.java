package net.sf.anathema.platform.fx;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.lang.ArrayUtilities;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.platform.fx.FxThreading.runOnCorrectThread;

public class PerspectivePane {

  private MigPane outerPane = new MigPane(new LC().fill());
  private MigPane navigationPanel = new MigPane(LayoutUtils.fillWithoutInsets());
  private MigPane contentPanel = new MigPane(LayoutUtils.fillWithoutInsets());

  public PerspectivePane(final String... styleSheetPaths) {
    runOnCorrectThread(new InitNavigationPane());
    runOnCorrectThread(new InitContentPane());
    runOnCorrectThread(new InitOuterPane());
    runOnCorrectThread(new Runnable() {
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

  public void setNavigationComponent(final Node component) {
    navigationPanel.add(component, new CC().grow().push());
  }

  public void setContentComponent(final Node component) {
    contentPanel.add(component, new CC().grow().push());
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
      outerPane.getStyleClass().add("perspective-outer-pane");
      outerPane.add(navigationPanel, new CC().grow().minWidth("200").width("200").maxWidth("200"));
      outerPane.add(contentPanel, new CC().grow().push());
    }
  }

  private class InitNavigationPane implements Runnable {
    @Override
    public void run() {
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