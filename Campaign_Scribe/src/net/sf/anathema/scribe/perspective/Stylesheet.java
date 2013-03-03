package net.sf.anathema.scribe.perspective;

import javafx.scene.Scene;

import java.net.URL;

public class Stylesheet {

  private final String path;

  public Stylesheet(String pathBeneathSourceFolder) {
    this.path = pathBeneathSourceFolder;
  }

  public void applyToScene(Scene scene) {
    URL resource = new ResourceLoader().loadResourceAsUrl(path);
    scene.getStylesheets().add(resource.toExternalForm());
  }
}