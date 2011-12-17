package net.sf.anathema.character.reporting.extended.util;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;

public class VirtueFlawBoxEncoder {

  private final PdfTraitEncoder traitEncoder;

  public VirtueFlawBoxEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
  }
  
  public float getBoxHeight() {
    return traitEncoder.getTraitHeight();
  }

  public Bounds encode(PdfContentByte directContent, Bounds bounds, int currentLimit) {
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - encodeHeight(directContent, bounds, currentLimit));
  }

  public float encodeHeight(PdfContentByte directContent, Bounds bounds, int currentLimit) {
    float traitBaseLine = bounds.getMaxY() - traitEncoder.getTraitHeight();
    float padding = IVoidStateFormatConstants.PADDING / 2.0f;
    Position traitPosition = new Position(bounds.x + padding, traitBaseLine);
    return traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, traitPosition, bounds.width - 2 * padding, currentLimit, 10);
  }
}
