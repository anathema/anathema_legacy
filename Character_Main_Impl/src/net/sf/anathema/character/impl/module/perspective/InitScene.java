package net.sf.anathema.character.impl.module.perspective;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class InitScene implements Runnable {

  private final JFXPanel panel;
  private Node content;

  InitScene(JFXPanel panel, Node content) {
    this.panel = panel;
    this.content = content;
  }

  @Override
  public void run() {
    Scene scene = createScene();
    panel.setScene(scene);
  }

  private Scene createScene() {
    Group root = new Group();
    Scene scene = new Scene(root, Color.LAVENDER);
    scene.getStylesheets().add("skin/sandra/sandra.css");
    root.getChildren().add(content);
    return scene;
  }
}
