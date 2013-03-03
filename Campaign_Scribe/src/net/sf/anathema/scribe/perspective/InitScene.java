package net.sf.anathema.scribe.perspective;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class InitScene implements Runnable {

  private final JFXPanel panel;
  private Parent content;

  public InitScene(JFXPanel panel, Parent content) {
    this.panel = panel;
    this.content = content;
  }

  @Override
  public void run() {
    Scene scene = createScene();
    panel.setScene(scene);
  }

  private Scene createScene() {
    Scene scene = new Scene(content);
    new Stylesheet("skin/anathema/scribe.css").applyToScene(scene);
    return scene;
  }
}
