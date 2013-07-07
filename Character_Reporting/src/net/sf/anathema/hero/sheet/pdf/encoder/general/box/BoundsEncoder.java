package net.sf.anathema.hero.sheet.pdf.encoder.general.box;

import net.sf.anathema.hero.sheet.pdf.encoder.extent.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public interface BoundsEncoder {

  float HEADER_HEIGHT = 12;
  float ARC_SPACE = HEADER_HEIGHT / 2;
  float ARC_SIZE = 2 * ARC_SPACE;

  void encodeBoxBounds(SheetGraphics graphics, Bounds bounds);
}
