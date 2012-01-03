package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoundsEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoxEncodingUtils;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class AttributeBoundsEncoder implements BoundsEncoder {

  private final float smallWidth;
  private float smallHeight;

  public AttributeBoundsEncoder(float smallWidth, float smallHeight) {
    this.smallWidth = smallWidth;
    this.smallHeight = smallHeight - (HEADER_HEIGHT / 2);
  }

  public void encodeBoxBounds(SheetGraphics graphics, Bounds contentBounds) {
    PdfContentByte directContent = graphics.getDirectContent();
    float smallMaxX = contentBounds.x + smallWidth;
    graphics.setFillColorBlack();
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARC_SPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARC_SPACE, contentBounds.y);
    directContent.lineTo(smallMaxX - ARC_SPACE, contentBounds.y);
    BoxEncodingUtils.add90DegreeArc(directContent, smallMaxX - ARC_SIZE, contentBounds.y, 270);
    directContent.moveTo(smallMaxX, contentBounds.y + ARC_SPACE);
    directContent.lineTo(smallMaxX, contentBounds.getMaxY() - ARC_SPACE - smallHeight);
    BoxEncodingUtils.add90DegreeArc(directContent, smallMaxX, contentBounds.getMaxY() - smallHeight - ARC_SIZE, 90);
    directContent.moveTo(smallMaxX + ARC_SPACE, contentBounds.getMaxY() - smallHeight);
    directContent.lineTo(contentBounds.getMaxX() - ARC_SPACE, contentBounds.getMaxY() - smallHeight);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - smallHeight, 270);
    directContent.moveTo(contentBounds.getMaxX(), contentBounds.getMaxY() + ARC_SPACE - smallHeight);
    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARC_SPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY() - ARC_SIZE, 0);
    directContent.moveTo(contentBounds.getMinX() + ARC_SPACE, contentBounds.getMaxY());
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARC_SPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARC_SPACE);
    directContent.stroke();
  }
}
