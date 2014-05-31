package net.sf.anathema.scribe.editor.view;

import javafx.scene.Node;
import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.scribe.editor.presenter.ScrollPreview;

public class ThreadedFxScrollPreview implements ScrollPreview {

  private FxScrollPreview preview;

  public ThreadedFxScrollPreview() {
    preview = new FxScrollPreview();
  }

  @Override
  public void setHtmlText(final HtmlText text) {
    preview.setHtmlText(text);
  }

  @Override
  public void setTitle(final String text) {
    preview.setTitle(text);
  }

  @Override
  public void setUnnamedScrollTitlePreview(final String text) {
    preview.setUnnamedScrollTitlePreview(text);
  }

  public Node getNode() {
    return preview.getNode();
  }
}