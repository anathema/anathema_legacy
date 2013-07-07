package net.sf.anathema.hero.sheet.pdf.encoder.general.box;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public class PdfBoxEncoder {

  private final PdfHeaderBoxEncoder headerBoxEncoder = new PdfHeaderBoxEncoder();
  private final BoundsEncoder boundsEncoder = new StandardBoundsEncoder();

  public void encodeBox(ReportSession session, SheetGraphics graphics, ContentEncoder encoder, Bounds bounds) throws DocumentException {
    String header = encoder.getHeader(session);
    Bounds contentBounds = encodeBox(graphics, bounds, header, boundsEncoder);
    encoder.encode(graphics, session, contentBounds);
  }

  private Bounds encodeBox(SheetGraphics graphics, Bounds bounds, String title, BoundsEncoder boxEncoder) {
    Bounds contentBounds = BoxBoundsFactory.calculateBoxRenderBounds(bounds);
    boxEncoder.encodeBoxBounds(graphics, contentBounds);
    headerBoxEncoder.encodeHeaderBox(graphics, bounds, title);
    return BoxBoundsFactory.calculateContentBounds(bounds);
  }
}