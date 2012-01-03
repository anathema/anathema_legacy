package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class PdfBoxEncoder {

  public static final int CONTENT_INSET = 5;

  private final IResources resources;
  private final PdfHeaderBoxEncoder headerBoxEncoder = new PdfHeaderBoxEncoder();
  private final BoundsEncoder standardBoxEncoder = new StandardBoundsEncoder();

  public PdfBoxEncoder(IResources resources) {
    this.resources = resources;
  }

  public Bounds calculateContentBounds(Bounds bounds) {
    Bounds contentBoxBounds = calculateContentBoxBounds(bounds);
    return calculateInsettedBounds(contentBoxBounds);
  }

  private Bounds calculateContentBoxBounds(Bounds bounds) {
    float headerPadding = BoundsEncoder.HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  public float getRequestedHeight(SheetGraphics graphics, IVariableBoxContentEncoder encoder, ReportContent content, float width) {
    float boxHeight = BoundsEncoder.HEADER_HEIGHT / 2f + BoundsEncoder.ARC_SPACE;
    return boxHeight + encoder.getRequestedHeight(graphics, content, width);
  }

  private Bounds encodeBox(SheetGraphics graphics, Bounds bounds, String title, BoundsEncoder boxEncoder) {
    Bounds contentBounds = calculateContentBoxBounds(bounds);
    boxEncoder.encodeBoxBounds(graphics, contentBounds);
    headerBoxEncoder.encodeHeaderBox(graphics, bounds, title);
    return calculateInsettedBounds(contentBounds);
  }

  public Bounds encodeBox(SheetGraphics graphics, Bounds bounds, String title) {
    return encodeBox(graphics, bounds, title, standardBoxEncoder);
  }

  public void encodeBox(ReportContent content, SheetGraphics graphics, ContentEncoder encoder, Bounds bounds) throws DocumentException {
    encodeBox(graphics, encoder, standardBoxEncoder, content, bounds);
  }

  public void encodeBox(SheetGraphics graphics, ContentEncoder encoder, BoundsEncoder boxEncoder, ReportContent content, Bounds bounds)
    throws DocumentException {
    String header = resources.getString("Sheet.Header." + encoder.getHeaderKey(content)); //$NON-NLS-1$
    Bounds contentBounds = encodeBox(graphics, bounds, header, boxEncoder);
    encoder.encode(graphics, content, contentBounds);
  }

  private Bounds calculateInsettedBounds(Bounds contentBounds) {
    return new Bounds(contentBounds.x + CONTENT_INSET, contentBounds.y, calculateInsettedWidth(contentBounds.width),
      contentBounds.height - BoundsEncoder.ARC_SPACE);
  }

  public float calculateInsettedWidth(float width) {
    return width - 2 * CONTENT_INSET;
  }
}
