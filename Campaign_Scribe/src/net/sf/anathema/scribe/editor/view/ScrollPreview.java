package net.sf.anathema.scribe.editor.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.web.WebView;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.scribe.editor.model.HtmlText;

public class ScrollPreview {

  private WebView webView;

  public ScrollPreview() {
    FxThreading.assertNotOnFxThread();
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        webView = new WebView();
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
    return webView;
  }
}
