package net.sf.anathema.fx.character.perspective;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

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
    Scene scene = new Scene(content, Color.LAVENDER);
    scene.getStylesheets().add("skin/sandra/sandra.css");
    return scene;
  }
}
