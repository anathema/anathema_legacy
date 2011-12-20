package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Box;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

public class LabelledValueEncoder {

  private final static float BOX_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final static float BOX_WIDTH = 12;

  private final BaseFont baseFont;
  private final int columnCount;
  private final Position position;
  private final float width;
  private final float baseLine;
  private final float commentLine;
  private final float padding;
  private boolean commentPresent = false;

  public LabelledValueEncoder(BaseFont baseFont, int columnCount, Position position, float width, int padding) {
    this.baseFont = baseFont;
    this.columnCount = columnCount;
    this.position = position;
    this.width = width;
    this.baseLine = position.y - BOX_HEIGHT - padding;
    this.commentLine = baseLine - IVoidStateFormatConstants.COMMENT_FONT_SIZE - padding + 1;
    this.padding = padding;
  }

  private float getRightColumnX(int index) {
    return position.x + width / columnCount * (index + 1);
  }

  public void addComment(SheetGraphics graphics, String text, int column) {
    float rightX = getRightColumnX(column);
    commentPresent = true;
    graphics.drawComment(text, new Position(rightX, commentLine), Element.ALIGN_RIGHT);
  }

  public void addLabelledValue(SheetGraphics graphics, int column, String text, int... values) {
    float rightX = getRightColumnX(column);
    float allBoxesWidth = BOX_WIDTH * values.length + (values.length - 1) * padding;
    Position textPosition = new Position(rightX - allBoxesWidth - padding, baseLine);
    graphics.drawText(text, textPosition, Element.ALIGN_RIGHT);
    for (int index = 0; index < values.length; index++) {
      float boxX = rightX - allBoxesWidth + (BOX_WIDTH + padding) * index;
      Bounds boxBounds = new Bounds(boxX, textPosition.y - 2, BOX_WIDTH, BOX_HEIGHT);
      new Box(boxBounds).encodeTotalType(graphics.getDirectContent());
      Position valuePosition = new Position(boxBounds.getCenterX(), textPosition.getY());
      graphics.drawText(String.valueOf(values[index]), valuePosition, Element.ALIGN_CENTER);
    }
  }

  public float getHeight() {
    float textPosition = commentPresent ? commentLine : baseLine;
    return position.y - textPosition + 1;
  }
}
