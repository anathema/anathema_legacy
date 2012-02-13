package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TextMetrics;

public class EncodingMetrics {

  public static final EncodingMetrics From(SheetGraphics graphics, ReportContent content) {
    return new EncodingMetrics(content, graphics);
  }

  private final ReportContent content;
  private SheetGraphics graphics;

  public EncodingMetrics(ReportContent content, SheetGraphics graphics) {
    this.content = content;
    this.graphics = graphics;
  }

  public ReportContent getContent() {
    return content;
  }

  public TextMetrics getTextMetrics() {
    return graphics.getTextMetrics();
  }

  public SheetGraphics createSimulateGraphics(float width) {
     return graphics.createTemplate(width, 1000).getTemplateGraphics();
  }

  public Bounds createSimulateBounds(float width) {
    return  new Bounds(0, 0, width, 1000);

  }
}
