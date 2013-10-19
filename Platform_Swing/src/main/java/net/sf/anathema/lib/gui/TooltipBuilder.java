package net.sf.anathema.lib.gui;

public class TooltipBuilder implements ConfigurableTooltip {

  public static final String HtmlLineBreak = "<br>";

  private final StringBuilder builder = new StringBuilder();
  private boolean showNoTooltip = false;

  public TooltipBuilder() {
    builder.append("<html>");
  }

  @Override
  public void showNoTooltip() {
    this.showNoTooltip = true;
  }

  @Override
  public void appendLine(String text) {
    builder.append(text);
    appendLineBreak();
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

  @Override
  public void appendDescriptiveLine(String description) {
    appendItalic(description);
    appendLineBreak();
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
    if (showNoTooltip) {
      return "";
    }
    builder.append("</html>");
    return builder.toString();
  }
}
