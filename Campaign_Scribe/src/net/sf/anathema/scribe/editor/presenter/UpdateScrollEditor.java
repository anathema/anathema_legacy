package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.model.ScrollChangedListener;
import net.sf.anathema.scribe.editor.model.WikiText;
import net.sf.anathema.scribe.editor.view.ScrollView;

public class UpdateScrollEditor implements ScrollChangedListener {
  private final ScrollView editor;

  public UpdateScrollEditor(ScrollView editor) {
    this.editor = editor;
  }

  @Override
  public void contentChanged(WikiText wikiText, HtmlText htmlText) {
    editor.scrollPreview.setHtmlText(htmlText);
    editor.scrollEditor.setWikiText(wikiText);
  }

  @Override
  public void nameChanged(String name) {
    String title = name.isEmpty() ? "Unnamed Scroll" : name;
    editor.scrollEditor.setTitle(title);
    editor.scrollPreview.setTitle(title);
  }
}
