package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.model.ScrollChangedListener;
import net.sf.anathema.scribe.editor.model.WikiText;
import net.sf.anathema.scribe.editor.view.ScrollEditor;

public class UpdateTextInScrollEditor implements ScrollChangedListener {
  private final ScrollEditor editor;

  public UpdateTextInScrollEditor(ScrollEditor editor) {
    this.editor = editor;
  }

  @Override
  public void contentChanged(WikiText wikiText, HtmlText htmlText) {
    editor.setHtmlText(htmlText);
    editor.setWikiText(wikiText);
  }
}
