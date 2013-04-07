package net.sf.anathema.scribe.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;

public class ScrollPreviewDemo extends Application {

  private static final int DEBUG = 0;

  public static void main(String[] arguments) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    TextField titleDisplay = new TextField("");
    WebView content = new WebView();
    MigPane pane = new MigPane(new LC().insets("0").gridGap("0", "2").wrapAfter(1).debug(DEBUG), new AC().grow().fill(),
            new AC().fill());
    pane.add(titleDisplay, new CC().width("100%").grow());
    pane.add(content, new CC().push());
    Scene scene = new Scene(pane);
    titleDisplay.setText("London!");
    content.getEngine().loadContent("Hullo!");
    stage.setScene(scene);
    stage.setWidth(800);
    stage.setHeight(600);
    stage.show();
  }
}