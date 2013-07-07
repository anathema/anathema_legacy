package net.sf.anathema.hero.sheet.pdf.encoder.general;

import net.sf.anathema.hero.sheet.pdf.encoder.extent.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.extent.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public class HorizontalLineEncoder {

  public void encodeLines(SheetGraphics graphics, Bounds bounds, float lineHeight) {
    float yPosition = bounds.getMaxY() - lineHeight;
    while (yPosition > bounds.getMinY()) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.getMinX(), yPosition), bounds.getMaxX()).encode();
      yPosition -= lineHeight;
    }
  }

  public void encodeLines(SheetGraphics graphics, Position lineStartPosition, float minX, float maxX, float lineHeight, int lineCount) {
    if (lineCount <= 0) {
      return;
    }
    graphics.createHorizontalLineByCoordinate(lineStartPosition, maxX).encode();
    for (int index = 1; index < lineCount; index++) {
      lineStartPosition = new Position(minX, lineStartPosition.y - lineHeight);
      graphics.createHorizontalLineByCoordinate(lineStartPosition, maxX).encode();
    }
  }
}
