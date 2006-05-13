package net.sf.anathema.development.character.reporting;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfBoxEncoder extends AbstractPdfEncoder {

  private static final int CONTENT_INSET = 5;
  private static final int HEADER_FONT_PADDING = 3;
  private static final int HEADER_FONT_SIZE = IVoidStateFormatConstants.HEADER_FONT_SIZE;
  private static final int HEADER_HEIGHT = 12;
  private static final int ARCSPACE = HEADER_HEIGHT / 2;
  private static final int ARC_SIZE = 2 * ARCSPACE;
  private BaseFont baseFont;

  public PdfBoxEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  public SmartRectangle encodeBox(PdfContentByte directContent, SmartRectangle bounds, String title) {
    SmartRectangle contentBounds = encodeContentBox(directContent, bounds);
    encodeHeaderBox(directContent, bounds, title);
    return new SmartRectangle(
        contentBounds.x + CONTENT_INSET,
        contentBounds.y,
        contentBounds.width - 2 * CONTENT_INSET,
        contentBounds.height - ARCSPACE);
  }

  private SmartRectangle encodeContentBox(PdfContentByte directContent, SmartRectangle bounds) {
    SmartRectangle contentBounds = calculateContentBounds(bounds);
    setFillColorBlack(directContent);
    setLineWidthAHalf(directContent);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
    add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
    directContent.lineTo(contentBounds.x + contentBounds.width - ARCSPACE, contentBounds.y);
    add90DegreeArc(directContent, contentBounds.x + contentBounds.width - ARC_SIZE, contentBounds.y, 270);
    directContent.moveTo((int) contentBounds.getMaxX(), contentBounds.y + ARCSPACE);
    directContent.lineTo((int) contentBounds.getMaxX(), (int) contentBounds.getMaxY() - ARCSPACE);
    add90DegreeArc(directContent, (int) contentBounds.getMaxX() - ARC_SIZE, (int) contentBounds.getMaxY() - ARC_SIZE, 0);
    directContent.moveTo((int) contentBounds.getMaxX() - ARCSPACE, (int) contentBounds.getMaxY());
    directContent.lineTo((int) contentBounds.getMinX() + ARCSPACE, (int) contentBounds.getMaxY());
    add90DegreeArc(directContent, contentBounds.x, (int) contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, (int) contentBounds.getMaxY() - ARCSPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARCSPACE);
    directContent.stroke();
    return contentBounds;
  }

  private void add90DegreeArc(PdfContentByte directContent, int minX, int minY, float startAngle) {
    directContent.arc(minX, minY, minX + ARC_SIZE, minY + ARC_SIZE, startAngle, 90);
  }

  private SmartRectangle calculateContentBounds(SmartRectangle bounds) {
    int headerPadding = HEADER_HEIGHT / 2;
    return new SmartRectangle(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  private void encodeHeaderBox(PdfContentByte directContent, SmartRectangle bounds, String title) {
    setFillColorBlack(directContent);
    SmartRectangle headerBounds = calculateHeaderBounds(bounds);
    directContent.rectangle(
        headerBounds.x + ARCSPACE,
        headerBounds.y,
        headerBounds.width - 2 * ARCSPACE,
        headerBounds.height);
    directContent.arc(headerBounds.x, headerBounds.y, headerBounds.x + 2 * ARCSPACE, headerBounds.y
        + headerBounds.height, 0, 360);
    directContent.arc(
        (float) headerBounds.getMaxX(),
        headerBounds.y,
        (float) headerBounds.getMaxX() - 2 * ARCSPACE,
        (float) headerBounds.getMaxY(),
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

  private SmartRectangle calculateHeaderBounds(SmartRectangle bounds) {
    return new SmartRectangle(bounds.x, bounds.y + bounds.height - HEADER_HEIGHT, bounds.width, HEADER_HEIGHT);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }
}