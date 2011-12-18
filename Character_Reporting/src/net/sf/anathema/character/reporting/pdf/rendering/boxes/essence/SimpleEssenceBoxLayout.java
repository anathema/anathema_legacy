package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.ITextMetrics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class SimpleEssenceBoxLayout {

  private ITextMetrics textMetrics;
  private Bounds bounds;
  private float traitHeight;
  private int numberOfLines;

  public SimpleEssenceBoxLayout(ITextMetrics textMetrics, Bounds bounds, float traitHeight, int numberOfLines) {
    this.textMetrics = textMetrics;
    this.bounds = bounds;
    this.traitHeight = traitHeight;
    this.numberOfLines = numberOfLines;
  }

  private float getPoolHeight() {
    return bounds.height - traitHeight - TEXT_PADDING;
  }

  private float getPoolLineHeight() {
    return getPoolHeight() / 2;
  }

  public Position getEssencePosition() {
    float essenceOffset = (2 - numberOfLines) * (getPoolLineHeight() / 2);
    return new Position(bounds.x, bounds.y + bounds.height - traitHeight - essenceOffset);
  }

  public Position getFirstPoolPosition() {
    return new Position(bounds.x, getEssencePosition().y - 1 * getPoolLineHeight());
  }

  public Position getSecondPoolPosition() {
    return new Position(bounds.x, getEssencePosition().y - 2 * getPoolLineHeight());
  }

  public Position getAvailabilityPositionRightAligned(Position poolPosition) {
    return new Position(bounds.getMaxX(), poolPosition.y);
  }

  public float getAvailabilityLineLength() {
    return 10f;
  }

  public Position getAvailabilityLineStartPosition(Position poolPosition, String availableString) {
    float textWidth = textMetrics.getDefaultTextWidth(availableString);
    float availabilityX = bounds.getMaxX() - textWidth - getAvailabilityLineLength();
    return new Position(availabilityX, poolPosition.y);
  }
}
