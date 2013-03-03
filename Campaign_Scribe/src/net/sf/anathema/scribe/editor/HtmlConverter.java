package net.sf.anathema.scribe.editor;

public class HtmlConverter {

  public HtmlText convert(WikiText wikiText) {
    return new HtmlText(wikiText.getCanonicalText());
  }
}
