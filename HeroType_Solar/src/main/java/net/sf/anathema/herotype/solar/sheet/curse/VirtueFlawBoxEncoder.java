package net.sf.anathema.herotype.solar.sheet.curse;

import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.PADDING;

public class VirtueFlawBoxEncoder {

  public Bounds encode(SheetGraphics graphics, Bounds bounds, int currentLimit) {
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - encodeHeight(graphics, bounds, currentLimit));
  }

  public float encodeHeight(SheetGraphics graphics, Bounds bounds, int currentLimit) {
    PdfTraitEncoder traitEncoder = PdfTraitEncoder.createLargeTraitEncoder();
    float traitBaseLine = bounds.getMaxY() - traitEncoder.getTraitHeight();
    float padding = PADDING / 2.0f;
    Position traitPosition = new Position(bounds.x + padding, traitBaseLine);
    return traitEncoder.encodeSquaresCenteredAndUngrouped(graphics, traitPosition, bounds.width - 2 * padding, currentLimit, 10);
  }
}
