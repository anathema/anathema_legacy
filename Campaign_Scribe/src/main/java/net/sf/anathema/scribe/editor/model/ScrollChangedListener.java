package net.sf.anathema.scribe.editor.model;

import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.platform.markdown.WikiText;

public interface ScrollChangedListener {

  void contentChanged(WikiText wikiText, HtmlText htmlText);

  void nameChanged(String text);
}
