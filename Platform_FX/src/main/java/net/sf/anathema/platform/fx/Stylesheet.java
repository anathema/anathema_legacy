package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import net.sf.anathema.lib.logging.Logger;

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

  @SuppressWarnings("ConstantConditions")
  public void applyToParent(Node parent) {
    if (!(parent instanceof Parent)) {
      Logger.getLogger(Stylesheet.class).warn(
              "Could not apply stylesheet " + path + " to " + parent + " because it is not a JavaFX Parent.");
    }
    applyToParent((Parent) parent);
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