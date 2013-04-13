package net.sf.anathema.platform.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class Navigation {

  private MigPane pane;
  private MigPane navigation;
  private ToolBar toolBar;

  public Navigation() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        navigation = new MigPane(withoutInsets().gridGap("0", "2").wrapAfter(1), new AC().grow().fill(),
                new AC().fill());
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
        pane = new MigPane(withoutInsets().gridGap("0", "2").wrapAfter(1), new AC().grow().fill(),
                new AC().fill());
        pane.add(toolBar, new CC().width("100%").grow());
        pane.add(navigation, new CC().push());
      }
    });
  }

  public Tool addTool() {
    final FxButtonTool fxButtonTool = FxButtonTool.ForToolbar();
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        toolBar.getItems().add(fxButtonTool.getNode());
      }
    });
    return fxButtonTool;
  }

  protected void addElementToNavigation(Node element) {
    navigation.add(element, new CC().pushX());
  }

  protected void addContainerToNavigation(Node element) {
    navigation.add(element, new CC().push());
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