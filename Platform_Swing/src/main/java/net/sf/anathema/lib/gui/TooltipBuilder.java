package net.sf.anathema.lib.gui;

import static net.sf.anathema.lib.lang.StringUtilities.createFixedWidthParagraph;

public class TooltipBuilder {

  public static final String HtmlLineBreak = "<br>";
  public static final String CommaSpace = ", ";
  public static final String Space = " ";
  public static final String ColonSpace = ": ";
  public static final int DEFAULT_TOOLTIP_WIDTH = 80;

  private final StringBuilder builder = new StringBuilder();

  public TooltipBuilder() {
    builder.append("<html>");
  }

  public void appendTitleLine(String title) {
    appendBold(title);
    appendLineBreak();
  }

  public void appendLine(String label, String value) {
    appendItalic(label);
    builder.append(ColonSpace);
    builder.append(value);
    appendLineBreak();
  }

  public void appendParagraphs(String[] paragraphs) {
    for (String paragraph : paragraphs) {
      builder.append(TooltipBuilder.HtmlLineBreak);
      String shortedParagraph = createFixedWidthParagraph(paragraph, TooltipBuilder.HtmlLineBreak, DEFAULT_TOOLTIP_WIDTH);
      builder.append(shortedParagraph);
    }
  }

  private void appendLineBreak() {
    builder.append(HtmlLineBreak);
  }

  private void appendItalic(String label) {
    builder.append("<i>").append(label).append("</i>");
  }

  private void appendBold(String label) {
    builder.append("<b>").append(label).append("</b>");
  }

  public String build() {
    builder.append("</html>");
    return builder.toString();
  }
}
