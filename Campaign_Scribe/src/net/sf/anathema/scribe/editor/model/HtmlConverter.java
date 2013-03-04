package net.sf.anathema.scribe.editor.model;

import com.github.rjeschke.txtmark.Processor;

public class HtmlConverter {

  public HtmlText convert(WikiText wikiText) {
    String result = Processor.process(wikiText.getCanonicalText());
    return new HtmlText(result);
  }
}
