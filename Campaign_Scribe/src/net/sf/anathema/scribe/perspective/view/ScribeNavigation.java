package net.sf.anathema.scribe.perspective.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.sf.anathema.platform.fx.FxThreading;

public class ScribeNavigation {

  private VBox navigation;

  public ScribeNavigation() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        navigation = new VBox();
      }
    });
  }

  public void addText(final String text) {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        navigation.getChildren().add(new Text(text));
      }
    });
  }

  public Node getNode() {
    return navigation;
  }
}
