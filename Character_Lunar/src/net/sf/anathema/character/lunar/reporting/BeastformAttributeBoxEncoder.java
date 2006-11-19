package net.sf.anathema.character.lunar.reporting;

import static net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder.ARCSPACE;
import static net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder.ARC_SIZE;
import static net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder.HEADER_HEIGHT;
import static net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder.CONTENT_INSET;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class BeastformAttributeBoxEncoder extends AbstractPdfEncoder {

  private final BaseFont baseFont;

  public BeastformAttributeBoxEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public Bounds encodeContentBox(
      PdfContentByte directContent,
      Bounds bounds,
      float smallWidth,
      float overlapFreeSpaceHeight) {
    Bounds contentBounds = calculateContentBoxBounds(bounds);
    overlapFreeSpaceHeight -= getHeaderPadding();
    float smallMaxX = contentBounds.x + smallWidth;
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
    add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
    directContent.lineTo(smallMaxX - ARCSPACE, contentBounds.y);
    add90DegreeArc(directContent, smallMaxX - ARC_SIZE, contentBounds.y, 270);
    directContent.moveTo(smallMaxX, contentBounds.y + ARCSPACE);
    directContent.lineTo(smallMaxX, contentBounds.getMaxY() - ARCSPACE - overlapFreeSpaceHeight);
    add90DegreeArc(directContent, smallMaxX, contentBounds.getMaxY() - overlapFreeSpaceHeight - ARC_SIZE, 90);
    directContent.moveTo(smallMaxX + ARCSPACE, contentBounds.getMaxY() - overlapFreeSpaceHeight);
    directContent.lineTo(contentBounds.getMaxX() - ARCSPACE, contentBounds.getMaxY() - overlapFreeSpaceHeight);
    add90DegreeArc(
        directContent,
        contentBounds.getMaxX() - ARC_SIZE,
        contentBounds.getMaxY() - overlapFreeSpaceHeight,
        270);
    directContent.moveTo(contentBounds.getMaxX(), contentBounds.getMaxY() + ARCSPACE - overlapFreeSpaceHeight);
    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARCSPACE);
    add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - ARC_SIZE, 0);
    directContent.moveTo(contentBounds.getMinX() + ARCSPACE, contentBounds.getMaxY());
    add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARCSPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARCSPACE);
    directContent.stroke();
    return calculateInsettedBounds(contentBounds);
  }

  public Bounds calculateInsettedBounds(Bounds contentBounds) {
    return new Bounds(
        contentBounds.x + CONTENT_INSET,
        contentBounds.y,
        calculateInsettedWidth(contentBounds.width),
        contentBounds.height - ARCSPACE);
  }

  public float calculateInsettedWidth(float width) {
    return width - 2 * CONTENT_INSET;
  }

  private void add90DegreeArc(PdfContentByte directContent, float minX, float minY, float startAngle) {
    directContent.arc(minX, minY, minX + ARC_SIZE, minY + ARC_SIZE, startAngle, 90);
  }

  private Bounds calculateContentBoxBounds(Bounds bounds) {
    int headerPadding = getHeaderPadding();
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  private int getHeaderPadding() {
    int headerPadding = HEADER_HEIGHT / 2;
    return headerPadding;
  }
}