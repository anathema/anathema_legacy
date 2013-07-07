package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TextMetrics;

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
