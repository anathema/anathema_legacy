package net.sf.anathema.scribe.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

public class MigLayoutAndWebViewDoNotGetAlongDemo extends Application {

  public static void main(String[] arguments) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    TextField node = new TextField("The bug is fixed if you can read this text.");
    TextInputControl content = new TextArea("There should be a line of text on top of this one.");
    MigPane pane = layoutBug(node, content);
    Scene scene = new Scene(pane);
    stage.setScene(scene);
    stage.setWidth(400);
    stage.setHeight(100);
    stage.show();
  }

  private MigPane layoutBug(TextField node, TextInputControl content) {
    MigPane pane = new MigPane(new LC().wrapAfter(1), new AC(), new AC().index(1).shrinkPrio(200));
    pane.add(node);
    pane.add(content);
    return pane;
  }
}