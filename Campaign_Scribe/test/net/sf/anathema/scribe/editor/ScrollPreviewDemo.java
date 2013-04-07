package net.sf.anathema.scribe.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.anathema.platform.fx.Stylesheet;
import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.view.FxScrollPreview;
import org.tbee.javafx.scene.layout.MigPane;

public class ScrollPreviewDemo extends Application {

  public static void main(String[] arguments) {
    launch(arguments);
  }

  @Override
  public void start(Stage stage) throws Exception {
    FxScrollPreview preview = new FxScrollPreview();
    MigPane node = (MigPane) preview.getNode();
    Scene scene = new Scene(node);
    styleView(node);
    loadStylesheet(scene);
    setContent(preview);
    prepareView(stage, scene);
    stage.show();
  }

  private void prepareView(Stage stage, Scene scene) {
    stage.setScene(scene);
    stage.setWidth(800);
    stage.setHeight(600);
  }

  private void setContent(FxScrollPreview preview) {
    preview.setTitle("Welt!");
    preview.setHtmlText(new HtmlText("<html><body><b>Hallo!</b></body></html>"));
  }

  private void styleView(MigPane node) {
    node.getStyleClass().add("scribe-perspective");
  }

  private void loadStylesheet(Scene scene) {
    new Stylesheet("skin/anathema/scribe.css").applyToScene(scene);
  }
}