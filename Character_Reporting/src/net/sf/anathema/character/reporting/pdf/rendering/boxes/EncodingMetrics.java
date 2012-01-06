package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TextMetrics;

public class EncodingMetrics {

  public static final EncodingMetrics From(SheetGraphics graphics, ReportContent content) {
    return new EncodingMetrics(content, graphics.getTextMetrics());
  }

  private final ReportContent content;
  private TextMetrics textMetrics;

  public EncodingMetrics(ReportContent content, TextMetrics textMetrics) {
    this.content = content;
    this.textMetrics = textMetrics;
  }

  public ReportContent getContent() {
    return content;
  }

  public TextMetrics getTextMetrics() {
    return textMetrics;
  }
}
