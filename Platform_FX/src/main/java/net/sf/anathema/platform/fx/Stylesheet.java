package net.sf.anathema.platform.fx;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;

public class Stylesheet {

  private final String path;

  public Stylesheet(String pathBeneathSourceFolder) {
    this.path = pathBeneathSourceFolder;
  }

  public void applyToScene(Scene scene) {
    String urlAsString = getUrlToResource();
    scene.getStylesheets().add(urlAsString);
  }

  public void applyToParent(Parent parent) {
    String urlAsString = getUrlToResource();
    parent.getStylesheets().add(urlAsString);
  }

  private String getUrlToResource() {
    URL resource = new ResourceLoader().loadResourceAsUrl(path);
    return resource.toExternalForm();
  }
}