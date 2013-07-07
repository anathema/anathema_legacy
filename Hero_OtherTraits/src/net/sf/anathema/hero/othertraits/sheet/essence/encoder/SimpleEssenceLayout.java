package net.sf.anathema.hero.othertraits.sheet.essence.encoder;

import net.sf.anathema.hero.sheet.pdf.encoder.extent.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.extent.Position;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.TextMetrics;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.TEXT_PADDING;

public class SimpleEssenceLayout {

  private TextMetrics textMetrics;
  private Bounds bounds;
  private int numberOfLines;

  public SimpleEssenceLayout(TextMetrics textMetrics, Bounds bounds, int numberOfLines) {
    this.textMetrics = textMetrics;
    this.bounds = bounds;
    this.numberOfLines = numberOfLines;
  }

  private float getPoolHeight() {
    return bounds.height - getEssenceHeight() - TEXT_PADDING;
  }

  private float getEssenceHeight() {
    return PdfTraitEncoder.getEncodedHeight(getEssenceDotSize());
  }

  public int getEssenceDotSize() {
    return PdfTraitEncoder.LARGE_DOT_SIZE;
  }

  private float getPoolLineHeight() {
    return getPoolHeight() / 2;
  }

  public Position getEssencePosition() {
    float essenceOffset = (2 - numberOfLines) * (getPoolLineHeight() / 2);
    return new Position(bounds.x, bounds.y + bounds.height - getEssenceHeight() - essenceOffset);
  }

  public Position getFirstPoolPosition() {
    return new Position(bounds.x, getEssencePosition().y - 1 * getPoolLineHeight());
  }

  public Position getSecondPoolPosition() {
    return new Position(bounds.x, getEssencePosition().y - 2 * getPoolLineHeight());
  }

  public Position getAvailablePositionRightAligned(Position poolPosition) {
    return new Position(bounds.getMaxX(), poolPosition.y);
  }

  public float getMissingValueLineLength() {
    return 10f;
  }

  public Position getAvailableLineStart(Position poolPosition, String availableString) {
    float textWidth = textMetrics.getDefaultTextWidth(availableString);
    float availabilityX = bounds.getMaxX() - textWidth - getMissingValueLineLength();
    return new Position(availabilityX, poolPosition.y);
  }

  public float geWidth() {
    return bounds.width;
  }
}
