package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.scribe.editor.model.HtmlText;

public class ScrollPreview {

  private WebView webView;
  private BorderPane content;

  public ScrollPreview() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        webView = new WebView();
        webView.getStyleClass().add("scroll-preview-browser");
        content = new BorderPane();
        content.setCenter(webView);
        content.getStyleClass().add("scroll-preview-pane");
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

  public Node getNode() {
    return content;
  }
}
