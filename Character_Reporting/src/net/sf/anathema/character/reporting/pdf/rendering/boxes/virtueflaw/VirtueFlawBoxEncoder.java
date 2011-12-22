package net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw;

import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class VirtueFlawBoxEncoder {

  public Bounds encode(SheetGraphics graphics, Bounds bounds, int currentLimit) {
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - encodeHeight(graphics, bounds, currentLimit));
  }

  public float encodeHeight(SheetGraphics graphics, Bounds bounds, int currentLimit) {
    PdfTraitEncoder traitEncoder    = PdfTraitEncoder.createMediumTraitEncoder();
    float traitBaseLine = bounds.getMaxY() - traitEncoder.getTraitHeight();
    float padding = PADDING / 2.0f;
    Position traitPosition = new Position(bounds.x + padding, traitBaseLine);
    return traitEncoder.encodeSquaresCenteredAndUngrouped(graphics, traitPosition, bounds.width - 2 * padding, currentLimit, 10);
  }
}
