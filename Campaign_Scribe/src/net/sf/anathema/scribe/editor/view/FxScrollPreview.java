package net.sf.anathema.scribe.editor.view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.presenter.ScrollPreview;
import org.tbee.javafx.scene.layout.MigPane;

public class FxScrollPreview implements ScrollPreview {

  public static final int DEBUG = 0;
  private TextField titleDisplay;
  private WebView content;
  private MigPane pane;

  public FxScrollPreview() {
    titleDisplay = new TextField("");
    titleDisplay.setEditable(false);
    titleDisplay.getStyleClass().add("scroll-title");
    content = new WebView();
    content.getStyleClass().add("scroll-preview-browser");
    pane = new MigPane(new LC().insets("0").gridGap("0", "2").wrapAfter(1).debug(DEBUG), new AC().grow().fill(), new AC().fill());
    pane.getStyleClass().add("scroll-preview-pane");
    pane.add(titleDisplay, new CC().width("100%").grow());
    pane.add(content, new CC().push());
  }

  @Override
  public void setHtmlText(final HtmlText text) {
    content.getEngine().loadContent(text.getHtmlText());
  }

  @Override
  public void setTitle(final String text) {
    titleDisplay.setText(text);
  }

  public Node getNode() {
    return pane;
  }
}