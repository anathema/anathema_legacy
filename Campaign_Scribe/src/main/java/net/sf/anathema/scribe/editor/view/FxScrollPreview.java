package net.sf.anathema.scribe.editor.view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.scribe.editor.presenter.ScrollPreview;

public class FxScrollPreview implements ScrollPreview {

  private TextField titleDisplay;
  private WebView content;
  private BorderPane pane;

  public FxScrollPreview() {
    titleDisplay = new TextField("");
    titleDisplay.setEditable(false);
    titleDisplay.getStyleClass().add("scroll-title");
    content = new WebView();
    content.getStyleClass().add("scroll-preview-browser");
    pane = new BorderPane();
    pane.getStyleClass().add("scroll-preview-pane");
    pane.setTop(titleDisplay);
    pane.setCenter(content);
  }

  @Override
  public void setHtmlText(final HtmlText text) {
    content.getEngine().loadContent(text.getHtmlText());
  }

  @Override
  public void setTitle(final String text) {
    titleDisplay.setText(text);
  }

  @Override
  public void setUnnamedScrollTitlePreview(String text) {
    titleDisplay.setPromptText(text);
  }

  public Node getNode() {
    return pane;
  }
}