package net.sf.anathema.scribe.editor;

import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.presenter.ScrollPreview;

public class ScrollPreviewDummy implements ScrollPreview {

  public HtmlText htmlText;
  public String title;

  @Override
  public void setHtmlText(HtmlText text) {
    this.htmlText = text;
  }

  @Override
  public void setTitle(String text) {
    this.title = text;
  }
}
