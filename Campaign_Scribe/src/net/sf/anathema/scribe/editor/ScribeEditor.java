package net.sf.anathema.scribe.editor;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import org.jmock.example.announcer.Announcer;

public class ScribeEditor {

  private BorderPane content;
  private TextArea textArea;
  private WebView webView;
  private final Announcer<TextTypedListener> textChanged = Announcer.to(TextTypedListener.class);

  public ScribeEditor() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        content = new BorderPane();
        textArea = new TextArea();
        webView = new WebView();
        content.setCenter(textArea);
        content.setRight(webView);
        textArea.setWrapText(true);
        textArea.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
          public void handle(KeyEvent event) {
            textChanged.announce().textChanged(textArea.getText());
          }
        });
        webView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
            System.err.println(mouseEvent);
          }
        });
      }
    });
  }

  public void setWikiText(final WikiText text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        textArea.setText(text.getCanonicalText());
      }
    });
  }

  public void setHtmlText(final HtmlText text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        webView.getEngine().loadContent(text.getHtmlText());
      }
    });
  }

  public void whenTextTyped(TextTypedListener listener) {
    textChanged.addListener(listener);
  }

  public Node getNode() {
    return content;
  }
}
