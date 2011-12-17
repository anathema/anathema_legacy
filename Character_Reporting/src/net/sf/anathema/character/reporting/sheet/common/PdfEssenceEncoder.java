package net.sf.anathema.character.reporting.sheet.common;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.reporting.common.encoder.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

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

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Essence"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    String personalPool = null, peripheralPool = null;
    try {
      personalPool = character.getPersonalPool();
      peripheralPool = character.getPeripheralPool();
    }
    catch (ContractFailedException e) {
    }
    int numberOfLines = (personalPool == null ? 0 : 1) + (peripheralPool == null ? 0 : 1);
    float poolHeight = bounds.height - largeTraitEncoder.getTraitHeight() - IVoidStateFormatConstants.TEXT_PADDING;
    float poolLineHeight = poolHeight / 2;
    int offset = (int) ((2 - numberOfLines) * (poolLineHeight / 2));
    int value = character.getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    Position essencePosition = new Position(bounds.x, bounds.y + bounds.height - largeTraitEncoder.getTraitHeight() - offset);
    largeTraitEncoder.encodeDotsCenteredAndUngrouped(directContent, essencePosition, bounds.width, value, essenceMax);

    if (personalPool != null) {
      Position personalPosition = new Position(bounds.x, essencePosition.y - poolLineHeight);
      String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
      encodePool(directContent, personalLabel, personalPool, personalPosition, bounds.width);
    }

    if (peripheralPool != null) {
      Position peripheralPosition = new Position(bounds.x, essencePosition.y - (numberOfLines == 1 ? 1 : 2) * poolLineHeight);
      String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
      encodePool(directContent, peripheralLabel, peripheralPool, peripheralPosition, bounds.width);
    }
  }

  private void encodePool(PdfContentByte directContent, String label, String poolValue, Position poolPosition, float width) {
    drawText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " " + resources.getString("Sheet.Essence.Available"); //$NON-NLS-1$ //$NON-NLS-2$
    float availableTextWidth = getDefaultTextWidth(availableString);
    Position availablePosition = new Position(poolPosition.x + width, poolPosition.y);
    drawText(directContent, availableString, availablePosition, PdfContentByte.ALIGN_RIGHT);
    float lineLength = 10;
    Position lineStartPoint = new Position((int) (availablePosition.x - availableTextWidth - lineLength), poolPosition.y);
    drawMissingTextLine(directContent, lineStartPoint, lineLength);
    String totalString = poolValue + " " + resources.getString("Sheet.Essence.Total") + " / "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    drawText(directContent, totalString, lineStartPoint, PdfContentByte.ALIGN_RIGHT);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
