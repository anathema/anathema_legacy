package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class StandardBoundsEncoder implements BoundsEncoder {

  public static float getAdditionalBoxHeight() {
    return BoundsEncoder.HEADER_HEIGHT / 2f + BoundsEncoder.ARC_SPACE;
  }

  @Override
  public void encodeBoxBounds(SheetGraphics graphics, Bounds contentBounds) {
    PdfContentByte directContent = graphics.getDirectContent();
    graphics.setFillColorBlack();
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARC_SPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARC_SPACE, contentBounds.y);
    directContent.lineTo(contentBounds.x + contentBounds.width - ARC_SPACE, contentBounds.y);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x + contentBounds.width - ARC_SIZE, contentBounds.y, 270);
    directContent.moveTo(contentBounds.getMaxX(), contentBounds.y + ARC_SPACE);
    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARC_SPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - ARC_SIZE, 0);
    directContent.moveTo(contentBounds.getMaxX() - ARC_SPACE, contentBounds.getMaxY());
    directContent.lineTo(contentBounds.getMinX() + ARC_SPACE, contentBounds.getMaxY());
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARC_SPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARC_SPACE);
    directContent.stroke();
  }
}
