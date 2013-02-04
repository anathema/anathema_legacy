package net.sf.anathema.fx.character.perspective;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class InitScene implements Runnable {

  private final JFXPanel panel;
  private Node content;

  public InitScene(JFXPanel panel, Node content) {
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
    BorderPane borderPane = new BorderPane();
    root.getChildren().add(borderPane);
    borderPane.setCenter(content);
    return scene;
  }
}
