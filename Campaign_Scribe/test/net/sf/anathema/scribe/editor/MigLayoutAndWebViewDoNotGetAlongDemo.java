package net.sf.anathema.scribe.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.miginfocom.layout.CC;
import org.tbee.javafx.scene.layout.MigPane;

public class MigLayoutAndWebViewDoNotGetAlongDemo extends Application {

  public static void main(String[] arguments) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    WebView content = new WebView();
    MigPane pane = new MigPane();
    TextField node = new TextField("London!");
    pane.add(node, new CC().wrap());
    pane.add(content);
    Scene scene = new Scene(pane);
    content.getEngine().loadContent("Hullo!");
    stage.setScene(scene);
    stage.setWidth(800);
    stage.setHeight(600);
    stage.show();
  }
}