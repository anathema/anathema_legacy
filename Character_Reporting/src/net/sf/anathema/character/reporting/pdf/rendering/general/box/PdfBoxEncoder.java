package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class PdfBoxEncoder {

  private final PdfHeaderBoxEncoder headerBoxEncoder = new PdfHeaderBoxEncoder();
  private final BoundsEncoder boundsEncoder = new StandardBoundsEncoder();

  public float getRequestedHeight(SheetGraphics graphics, IVariableContentEncoder encoder, ReportSession session, float width) {
    float boxHeight = BoundsEncoder.HEADER_HEIGHT / 2f + BoundsEncoder.ARC_SPACE;
    return boxHeight + encoder.getRequestedHeight(graphics, session, width);
  }

  private Bounds encodeBox(SheetGraphics graphics, Bounds bounds, String title, BoundsEncoder boxEncoder) {
    Bounds contentBounds = BoxBoundsFactory.calculateBoxRenderBounds(bounds);
    boxEncoder.encodeBoxBounds(graphics, contentBounds);
    headerBoxEncoder.encodeHeaderBox(graphics, bounds, title);
    return BoxBoundsFactory.calculateContentBounds(bounds);
  }

  public Bounds encodeBox(SheetGraphics graphics, Bounds bounds, String title) {
    return encodeBox(graphics, bounds, title, boundsEncoder);
  }

  public void encodeBox(ReportSession session, SheetGraphics graphics, ContentEncoder encoder, Bounds bounds) throws DocumentException {
    String header = encoder.getHeader(session);
    Bounds contentBounds = encodeBox(graphics, bounds, header, boundsEncoder);
    encoder.encode(graphics, session, contentBounds);
  }
}
