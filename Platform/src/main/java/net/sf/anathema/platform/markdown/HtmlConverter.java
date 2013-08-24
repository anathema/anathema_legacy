package net.sf.anathema.platform.markdown;

import java.util.ArrayList;
import java.util.List;

public class HtmlConverter {

  private final List<Conversion> allConversions = new ArrayList<Conversion>() {
    {
      add(new ReplacementConversion("<", "&lt;"));
      add(new ReplacementConversion(">", "&gt;"));
      add(new MarkdownConversion());
      add(new EmbracedConversion("++", "u"));
    }
  };

  public HtmlText convert(WikiText wikiText) {
    String result = wikiText.getCanonicalText();
    for (Conversion conversion : allConversions) {
      result = conversion.convert(result);
    }
    return new HtmlText(result);
  }
}
