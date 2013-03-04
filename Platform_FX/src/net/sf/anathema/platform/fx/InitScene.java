package net.sf.anathema.platform.fx;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class InitScene implements Runnable {

  private final JFXPanel panel;
  private ParentHolder content;
  private String[] cssPathArray;

  public InitScene(JFXPanel panel, ParentHolder content, String... cssPathArray) {
    this.panel = panel;
    this.content = content;
    this.cssPathArray = cssPathArray;
  }

  @Override
  public void run() {
    Scene scene = createScene();
    panel.setScene(scene);
  }

  private Scene createScene() {
    Scene scene = new Scene(content.getParent(), Color.TRANSPARENT);
    for (String path : cssPathArray) {
      new Stylesheet(path).applyToScene(scene);
    }
    return scene;
  }
}
