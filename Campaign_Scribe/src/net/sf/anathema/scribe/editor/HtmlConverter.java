package net.sf.anathema.scribe.editor;

import com.github.rjeschke.txtmark.Processor;

public class HtmlConverter {

  public HtmlText convert(WikiText wikiText) {
    String result = Processor.process(wikiText.getCanonicalText());
    System.err.println(result);
    return new HtmlText(result);
  }
}
