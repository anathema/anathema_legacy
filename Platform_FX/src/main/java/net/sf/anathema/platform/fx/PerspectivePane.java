package net.sf.anathema.platform.fx;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
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
        for (String sheetPath : styleSheetPaths) {
          new Stylesheet(sheetPath).applyToParent(outerPane);
        }
      }
    });
  }

  private void initBorderedPane(Parent pane) {
    BorderPane.setMargin(pane, new Insets(3));
  }

  public void setNavigationComponent(final Node component) {
    runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        navigationPanel.add(component, new CC().grow().push());
      }
    });
  }

  public void setContentComponent(final Node component) {
    runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        contentPanel.add(component, new CC().grow().push());
      }
    });
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
      outerPane.add(navigationPanel, new CC().grow().minWidth("200").width("200").maxWidth("200"));
      outerPane.add(contentPanel, new CC().grow().push());
    }
  }

  private class InitNavigationPane implements Runnable {
    @Override
    public void run() {
      initBorderedPane(navigationPanel);
    }
  }

  private class InitContentPane implements Runnable {
    @Override
    public void run() {
      initBorderedPane(contentPanel);
    }
  }
}