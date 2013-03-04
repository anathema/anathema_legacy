package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.model.WikiText;
import org.jmock.example.announcer.Announcer;

public class ScrollEditor {

  private class InitGui implements Runnable {
    @Override
    public void run() {
      textArea = createTextArea();
      content = new BorderPane();
      webView = new WebView();
      content.setCenter(textArea);
      content.setRight(webView);
    }

    private TextArea createTextArea() {
      final TextArea area = new TextArea();
      area.setWrapText(true);
      area.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
          textChanged.announce().textChanged(area.getText());
        }
      });
      return area;
    }
  }

  private BorderPane content;
  private TextArea textArea;
  private WebView webView;
  private final Announcer<TextTypedListener> textChanged = Announcer.to(TextTypedListener.class);

  public ScrollEditor() {
    Platform.runLater(new InitGui());
  }

  public void setWikiText(final WikiText text) {
    if (text.getCanonicalText().equals(textArea.getText())) {
      return;
    }
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
