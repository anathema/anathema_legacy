package net.sf.anathema.scribe.perspective.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.scribe.perspective.presenter.Tool;
import net.sf.anathema.scribe.scroll.persistence.ScrollReference;
import org.tbee.javafx.scene.layout.MigPane;

public class ScribeNavigation {

  private MigPane pane;
  private MigPane navigation;
  private ToolBar toolBar;

  public ScribeNavigation() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        navigation = new MigPane(new LC().insets("0").gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().fill());
      }
    });
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        toolBar = new ToolBar();
      }
    });
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        pane = new MigPane(new LC().insets("0").gridGap("0", "2").wrapAfter(1), new AC().grow().fill(), new AC().fill());
        pane.add(toolBar, new CC().width("100%").grow());
        pane.add(navigation, new CC().push());
      }
    });
  }

  public void addScroll(final ScrollReference reference, final Command command) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Button button = new Button(reference.printName);
        button.getStyleClass().add("scribe-navigation-button");
        button.setOnAction(new Execute(command));
        navigation.add(button, new CC().pushX());
      }
    });
  }

  public Tool addTool() {
    final FxButtonTool fxButtonTool = new FxButtonTool();
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        toolBar.getItems().add(fxButtonTool.getNode());
      }
    });
    return fxButtonTool;
  }

  public void clear() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        navigation.getChildren().clear();
      }
    });
  }

  public Node getNode() {
    return pane;
  }
}
