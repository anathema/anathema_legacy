package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import static net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxEncoder.ARCSPACE;
import static net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxEncoder.HEADER_HEIGHT;

public class PdfHeaderBoxEncoder extends AbstractPdfEncoder {
  private static final int HEADER_FONT_PADDING = 3;
  private static final int HEADER_FONT_SIZE = IVoidStateFormatConstants.HEADER_FONT_SIZE;
  private final BaseFont baseFont;

  public PdfHeaderBoxEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encodeHeaderBox(PdfContentByte directContent, Bounds bounds, String title) {
    setFillColorBlack(directContent);
    Bounds headerBounds = calculateHeaderBounds(bounds);
    directContent.rectangle(headerBounds.x + ARCSPACE, headerBounds.y, headerBounds.width - 2 * ARCSPACE, headerBounds.height);
    directContent.arc(headerBounds.x, headerBounds.y, headerBounds.x + 2 * ARCSPACE, headerBounds.y + headerBounds.height, 0, 360);
    directContent.arc(headerBounds.getMaxX(), headerBounds.y, headerBounds.getMaxX() - 2 * ARCSPACE, headerBounds.getMaxY(), 0, 360);
    directContent.fillStroke();
    setFillColorWhite(directContent);
    directContent.setFontAndSize(baseFont, HEADER_FONT_SIZE);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_CENTER, title, (int) headerBounds.getCenterX(), headerBounds.y + HEADER_FONT_PADDING, 0);
    directContent.endText();
  }

  private Bounds calculateHeaderBounds(Bounds bounds) {
    return new Bounds(bounds.x, bounds.y + bounds.height - HEADER_HEIGHT, bounds.width, HEADER_HEIGHT);
  }

  private void setFillColorWhite(PdfContentByte directContent) {
    directContent.setRGBColorFill(255, 255, 255);
  }
}
