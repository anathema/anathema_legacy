package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface BoundsEncoder {

  float HEADER_HEIGHT = 12;
  float ARC_SPACE = HEADER_HEIGHT / 2;
  float ARC_SIZE = 2 * ARC_SPACE;

  void encodeBoxBounds(SheetGraphics graphics, Bounds bounds);
}
