package net.sf.anathema.lib.gui;

public class TooltipBuilder {

  public static final String HtmlLineBreak = "<br>"; //$NON-NLS-1$
  public static final String CommaSpace = ", "; //$NON-NLS-1$
  public static final String Space = " "; //$NON-NLS-1$
  public static final String ColonSpace = ": "; //$NON-NLS-1$

  private final StringBuilder builder = new StringBuilder();

  public TooltipBuilder() {
    builder.append("<html>");
  }

  public void appendTitleLine(String title) {
    appendBold(title);
    appendLineBreak();
  }

  public void appendLine(String label, String value) {
    builder.append(label);
    builder.append(ColonSpace);
    builder.append(value);
    appendLineBreak();
  }

  private void appendLineBreak() {
    builder.append(HtmlLineBreak);
  }

  private void appendBold(String label) {
    builder.append("<b>" + label + "</b>");
  }

  public String build() {
    builder.append("</html>");
    return builder.toString();
  }
}
