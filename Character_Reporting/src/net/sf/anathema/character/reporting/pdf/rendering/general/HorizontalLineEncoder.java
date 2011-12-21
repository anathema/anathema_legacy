package net.sf.anathema.character.reporting.pdf.rendering.general;

import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class HorizontalLineEncoder {

  public void encodeLines(SheetGraphics graphics, Bounds bounds, float lineHeight) {
    float yPosition = bounds.getMaxY() - lineHeight;
    while (yPosition > bounds.getMinY()) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.getMinX(), yPosition), bounds.getMaxX()).encode();
      yPosition -= lineHeight;
    }
  }

  public void encodeLines(SheetGraphics graphics, Position lineStartPosition, float minX, float maxX, float lineHeight,
    int lineCount) {
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
