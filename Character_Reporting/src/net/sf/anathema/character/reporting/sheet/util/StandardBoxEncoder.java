package net.sf.anathema.character.reporting.sheet.util;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.BoxEncodingUtils;
import net.sf.anathema.character.reporting.common.encoder.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfBoxEncoder;

public class StandardBoxEncoder extends AbstractPdfEncoder implements IPdfBoxEncoder {

  private final BaseFont baseFont;

  public StandardBoxEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encodeContentBox(PdfContentByte directContent, Bounds contentBounds) {
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
    directContent.lineTo(contentBounds.x + contentBounds.width - ARCSPACE, contentBounds.y);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x + contentBounds.width - ARC_SIZE, contentBounds.y, 270);
    directContent.moveTo(contentBounds.getMaxX(), contentBounds.y + ARCSPACE);
    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARCSPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - ARC_SIZE, 0);
    directContent.moveTo(contentBounds.getMaxX() - ARCSPACE, contentBounds.getMaxY());
    directContent.lineTo(contentBounds.getMinX() + ARCSPACE, contentBounds.getMaxY());
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARCSPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARCSPACE);
    directContent.stroke();
  }
}
