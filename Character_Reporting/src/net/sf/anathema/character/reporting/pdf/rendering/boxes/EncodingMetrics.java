package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TextMetrics;

public class EncodingMetrics {

  public static final EncodingMetrics From(SheetGraphics graphics, ReportSession session) {
    return new EncodingMetrics(session, graphics);
  }

  private final ReportSession session;
  private SheetGraphics graphics;

  public EncodingMetrics(ReportSession session, SheetGraphics graphics) {
    this.session = session;
    this.graphics = graphics;
  }

  public ReportSession getSession() {
    return session;
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
