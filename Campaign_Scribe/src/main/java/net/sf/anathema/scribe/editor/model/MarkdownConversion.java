package net.sf.anathema.scribe.editor.model;

import com.github.rjeschke.txtmark.Processor;

public class MarkdownConversion implements Conversion {
  @Override
  public String convert(String original) {
    return Processor.process(original);

  }
}
