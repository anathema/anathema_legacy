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
    MigPane pane = new MigPane();
    TextField node = new TextField("The bug is fixed if you can read this text.");
    pane.add(node, new CC().wrap());
    WebView content = new WebView();
    pane.add(content);
    Scene scene = new Scene(pane);
    content.getEngine().loadContent("There should be a line of text on top of this one.");
    stage.setScene(scene);
    stage.setWidth(400);
    stage.setHeight(100);
    stage.show();
  }
}