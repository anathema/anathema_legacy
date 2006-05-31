package net.sf.anathema.character.reporting.sheet.util;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfBoxEncoder extends AbstractPdfEncoder {

  private static final Logger logger = Logger.getLogger(PdfBoxEncoder.class);
  private static final int CONTENT_INSET = 5;
  private static final int HEADER_FONT_PADDING = 3;
  private static final int HEADER_FONT_SIZE = IVoidStateFormatConstants.HEADER_FONT_SIZE;
  public static final int HEADER_HEIGHT = 12;
  private static final int ARCSPACE = HEADER_HEIGHT / 2;
  private static final int ARC_SIZE = 2 * ARCSPACE;
  private BaseFont baseFont;
  private final IResources resources;

  public PdfBoxEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public Bounds calculateContentBounds(Bounds bounds) {
    Bounds contentBoxBounds = calculateContentBoxBounds(bounds);
    return calculateInsettedBounds(contentBoxBounds);
  }

  public Bounds encodeBox(PdfContentByte directContent, Bounds bounds, String title) {
    Bounds contentBounds = encodeContentBox(directContent, bounds);
    encodeHeaderBox(directContent, bounds, title);
    return calculateInsettedBounds(contentBounds);
  }

  public void encodeBox(
      PdfContentByte directContent,
      IPdfContentEncoder encoder,
      IGenericCharacter character,
      Bounds bounds,
      String headerId) throws DocumentException {
    String header = resources.getString("Sheet.Header." + headerId); //$NON-NLS-1$
    Bounds contentBounds = encodeBox(directContent, bounds, header);
    if (encoder != null) {
      encoder.encode(directContent, character, contentBounds);
    }
    else {
      logger.error("Encoder missing for " + header); //$NON-NLS-1$
    }
  }

  private Bounds calculateInsettedBounds(Bounds contentBounds) {
    return new Bounds(
        contentBounds.x + CONTENT_INSET,
        contentBounds.y,
        contentBounds.width - 2 * CONTENT_INSET,
        contentBounds.height - ARCSPACE);
  }

  private Bounds encodeContentBox(PdfContentByte directContent, Bounds bounds) {
    Bounds contentBounds = calculateContentBoxBounds(bounds);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
    add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
    directContent.lineTo(contentBounds.x + contentBounds.width - ARCSPACE, contentBounds.y);
    add90DegreeArc(directContent, contentBounds.x + contentBounds.width - ARC_SIZE, contentBounds.y, 270);
    directContent.moveTo(contentBounds.getMaxX(), contentBounds.y + ARCSPACE);
    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARCSPACE);
    add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - ARC_SIZE, 0);
    directContent.moveTo(contentBounds.getMaxX() - ARCSPACE, contentBounds.getMaxY());
    directContent.lineTo(contentBounds.getMinX() + ARCSPACE, contentBounds.getMaxY());
    add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARCSPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARCSPACE);
    directContent.stroke();
    return contentBounds;
  }

  private void add90DegreeArc(PdfContentByte directContent, float minX, float minY, float startAngle) {
    directContent.arc(minX, minY, minX + ARC_SIZE, minY + ARC_SIZE, startAngle, 90);
  }

  private Bounds calculateContentBoxBounds(Bounds bounds) {
    int headerPadding = HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  private void encodeHeaderBox(PdfContentByte directContent, Bounds bounds, String title) {
    setFillColorBlack(directContent);
    Bounds headerBounds = calculateHeaderBounds(bounds);
    directContent.rectangle(
        headerBounds.x + ARCSPACE,
        headerBounds.y,
        headerBounds.width - 2 * ARCSPACE,
        headerBounds.height);
    directContent.arc(headerBounds.x, headerBounds.y, headerBounds.x + 2 * ARCSPACE, headerBounds.y
        + headerBounds.height, 0, 360);
    directContent.arc(
        headerBounds.getMaxX(),
        headerBounds.y,
        headerBounds.getMaxX() - 2 * ARCSPACE,
        headerBounds.getMaxY(),
        0,
        360);
    directContent.fillStroke();
    setFillColorWhite(directContent);
    directContent.setFontAndSize(baseFont, HEADER_FONT_SIZE);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_CENTER, title, (int) headerBounds.getCenterX(), headerBounds.y
        + HEADER_FONT_PADDING, 0);
    directContent.endText();
  }

  private Bounds calculateHeaderBounds(Bounds bounds) {
    return new Bounds(bounds.x, bounds.y + bounds.height - HEADER_HEIGHT, bounds.width, HEADER_HEIGHT);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }
}