package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class PdfBoxEncoder {

  public static final int CONTENT_INSET = 5;

  private final IResources resources;
  private final PdfHeaderBoxEncoder headerBoxEncoder;
  private final IBoxEncoder standardBoxEncoder;

  public PdfBoxEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.headerBoxEncoder = new PdfHeaderBoxEncoder(baseFont);
    this.standardBoxEncoder = new StandardBoxEncoder(baseFont);
  }

  public Bounds calculateContentBounds(Bounds bounds) {
    Bounds contentBoxBounds = calculateContentBoxBounds(bounds);
    return calculateInsettedBounds(contentBoxBounds);
  }

  private Bounds calculateContentBoxBounds(Bounds bounds) {
    float headerPadding = IBoxEncoder.HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  public float getRequestedHeight(IVariableBoxContentEncoder encoder, ReportContent content, float width) {
    float boxHeight = IBoxEncoder.HEADER_HEIGHT / 2f + IBoxEncoder.ARCSPACE;
    return boxHeight + encoder.getRequestedHeight(content, width);
  }

  private Bounds encodeBox(SheetGraphics graphics, Bounds bounds, String title, IBoxEncoder boxEncoder) {
    Bounds contentBounds = calculateContentBoxBounds(bounds);
    boxEncoder.encodeContentBox(graphics, contentBounds);
    headerBoxEncoder.encodeHeaderBox(graphics, bounds, title);
    return calculateInsettedBounds(contentBounds);
  }

  public Bounds encodeBox(SheetGraphics graphics, Bounds bounds, String title) {
    return encodeBox(graphics, bounds, title, standardBoxEncoder);
  }

  public void encodeBox(ReportContent content, SheetGraphics graphics, IBoxContentEncoder encoder, Bounds bounds) throws DocumentException {
    encodeBox(graphics, encoder, standardBoxEncoder, content, bounds);
  }

  public void encodeBox(SheetGraphics graphics, IBoxContentEncoder encoder, IBoxEncoder boxEncoder, ReportContent content,
    Bounds bounds) throws DocumentException {
    String header = resources.getString("Sheet.Header." + encoder.getHeaderKey(content)); //$NON-NLS-1$
    Bounds contentBounds = encodeBox(graphics, bounds, header, boxEncoder);
    encoder.encode(graphics, content, contentBounds);
  }

  private Bounds calculateInsettedBounds(Bounds contentBounds) {
    return new Bounds(contentBounds.x + CONTENT_INSET, contentBounds.y, calculateInsettedWidth(contentBounds.width),
      contentBounds.height - IBoxEncoder.ARCSPACE);
  }

  public float calculateInsettedWidth(float width) {
    return width - 2 * CONTENT_INSET;
  }
}
