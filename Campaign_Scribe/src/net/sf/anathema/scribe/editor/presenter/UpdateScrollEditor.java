package net.sf.anathema.scribe.editor.presenter;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.scribe.editor.model.HtmlText;
import net.sf.anathema.scribe.editor.model.ScrollChangedListener;
import net.sf.anathema.scribe.editor.model.WikiText;

public class UpdateScrollEditor implements ScrollChangedListener {
  private final ScrollEditor editor;
  private final ScrollPreview preview;
  private final IResources resources;

  public UpdateScrollEditor(ScrollEditor editor, ScrollPreview preview, IResources resources) {
    this.editor = editor;
    this.preview = preview;
    this.resources = resources;
  }

  @Override
  public void contentChanged(WikiText wikiText, HtmlText htmlText) {
    preview.setHtmlText(htmlText);
    editor.setWikiText(wikiText);
  }

  @Override
  public void nameChanged(String name) {
    String title = name.isEmpty() ? resources.getString("Scribe.UnnamedScroll.Title") : name;
    editor.setTitle(title);
    preview.setTitle(title);
  }
}
