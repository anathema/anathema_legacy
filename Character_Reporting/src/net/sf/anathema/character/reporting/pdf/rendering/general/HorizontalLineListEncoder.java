package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.Line;

public class HorizontalLineListEncoder {

  public void encodeLines(PdfContentByte directContent, Bounds bounds, float lineHeight) {
    float yPosition = bounds.getMaxY() - lineHeight;
    while (yPosition > bounds.getMinY()) {
      Line line = Line.createHorizontalByCoordinate(new Position(bounds.getMinX(), yPosition), bounds.getMaxX());
      line.encode(directContent);
      yPosition -= lineHeight;
    }
  }
}
