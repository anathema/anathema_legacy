package net.sf.anathema.scribe.scroll.conversion;

import net.sf.anathema.framework.styledtext.model.IStyledTextualDescription;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.text.FontStyle;

public class StyledTextToWikiTextConverter {
  private final StringBuilder builder = new StringBuilder();

  public String convert(IStyledTextualDescription content) {
    for (ITextPart part : content.getTextParts()) {
      convert(part);
    }
    return builder.toString();
  }

  private void convert(ITextPart part) {
    FontStyle fontStyle = part.getFormat().getFontStyle();
    String nextConvertedPart = part.getText();
    if (fontStyle.isBold()) {
      nextConvertedPart = wrap(nextConvertedPart, "**");
    }
    if (fontStyle.isItalic()) {
      nextConvertedPart = wrap(nextConvertedPart, "*");
    }
    if (part.getFormat().isUnderline()) {
      nextConvertedPart = wrap(nextConvertedPart, "++");
    }
    if (nextConvertedPart.contains("\n")) {
      nextConvertedPart = nextConvertedPart.replace("\n", "  \n");
    }
    builder.append(nextConvertedPart);
  }

  private String wrap(String part, String wrap) {
    return wrap + part + wrap;
  }
}
