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
      Bounds smallBounds,
      float overlapFreeSpaceHeight) {
    Bounds contentBounds = calculateContentBoxBounds(bounds);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
    add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
    directContent.lineTo(smallBounds.x + smallBounds.width - ARCSPACE, smallBounds.y);
    add90DegreeArc(directContent, smallBounds.x + smallBounds.width - ARC_SIZE, smallBounds.y, 270);
    directContent.moveTo(smallBounds.getMaxX(), smallBounds.y + ARCSPACE);
    directContent.lineTo(smallBounds.getMaxX(), smallBounds.getMaxY() - ARCSPACE - overlapFreeSpaceHeight);
    add90DegreeArc(directContent, smallBounds.getMaxX(), smallBounds.getMaxY() - overlapFreeSpaceHeight - ARC_SIZE, 90);
    directContent.moveTo(smallBounds.getMaxX() + ARCSPACE, smallBounds.getMaxY() - overlapFreeSpaceHeight);
    directContent.lineTo(contentBounds.getMaxX() - ARCSPACE, smallBounds.getMaxY() - overlapFreeSpaceHeight);
    add90DegreeArc(
        directContent,
        contentBounds.getMaxX() - ARC_SIZE,
        smallBounds.getMaxY() - overlapFreeSpaceHeight,
        270);
    directContent.moveTo(contentBounds.getMaxX(), smallBounds.getMaxY() + ARCSPACE - overlapFreeSpaceHeight);
    directContent.lineTo(contentBounds.getMaxX(), smallBounds.getMaxY() - ARC_SIZE);
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
        contentBounds.width - 2 * CONTENT_INSET,
        contentBounds.height - ARCSPACE);
  }

  private void add90DegreeArc(PdfContentByte directContent, float minX, float minY, float startAngle) {
    directContent.arc(minX, minY, minX + ARC_SIZE, minY + ARC_SIZE, startAngle, 90);
  }

  private Bounds calculateContentBoxBounds(Bounds bounds) {
    int headerPadding = HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }
}