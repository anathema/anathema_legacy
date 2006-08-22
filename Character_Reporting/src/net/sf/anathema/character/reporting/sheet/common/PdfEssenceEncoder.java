package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfEssenceEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private BaseFont baseFont;
  private final IResources resources;
  private PdfTraitEncoder largeTraitEncoder;
  private final int essenceMax;

  public PdfEssenceEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.largeTraitEncoder = PdfTraitEncoder.createLargeTraitEncoder(baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey() {
    return "Essence"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    int value = character.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    Position essencePosition = new Position(bounds.x, bounds.y + bounds.height - largeTraitEncoder.getTraitHeight());
    largeTraitEncoder.encodeCenteredAndUngrouped(directContent, essencePosition, bounds.width, value, essenceMax);
    float poolHeight = bounds.height - largeTraitEncoder.getTraitHeight() - IVoidStateFormatConstants.TEXT_PADDING;
    float poolLineHeight = poolHeight / 2;
    Position personalPosition = new Position(bounds.x, essencePosition.y - poolLineHeight);
    String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
    encodePool(directContent, personalLabel, character.getPersonalPool(), personalPosition, bounds.width);
    Position peripheralPosition = new Position(bounds.x, essencePosition.y - 2 * poolLineHeight);
    String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
    encodePool(directContent, peripheralLabel, character.getPeripheralPool(), peripheralPosition, bounds.width);
  }

  private void encodePool(
      PdfContentByte directContent,
      String label,
      String poolValue,
      Position poolPosition,
      float width) {
    drawText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " " + resources.getString("Sheet.Essence.Available"); //$NON-NLS-1$ //$NON-NLS-2$
    float availableTextWidth = getDefaultTextWidth(availableString);
    Position availablePosition = new Position(poolPosition.x + width, poolPosition.y);
    drawText(directContent, availableString, availablePosition, PdfContentByte.ALIGN_RIGHT);
    float lineLength = 10;
    Position lineStartPoint = new Position(
        (int) (availablePosition.x - availableTextWidth - lineLength),
        poolPosition.y);
    drawMissingTextLine(directContent, lineStartPoint, lineLength);
    String totalString = poolValue + " " + resources.getString("Sheet.Essence.Total") + " / "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    drawText(directContent, totalString, lineStartPoint, PdfContentByte.ALIGN_RIGHT);
  }
}