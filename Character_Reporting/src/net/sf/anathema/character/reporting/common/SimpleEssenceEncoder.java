package net.sf.anathema.character.reporting.common;

import java.awt.Point;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.encoder.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.encoder.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SimpleEssenceEncoder extends AbstractPdfEncoder {

  private BaseFont baseFont;
  private final IResources resources;
  private PdfTraitEncoder largeTraitEncoder;
  private final int essenceMax;

  public SimpleEssenceEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.largeTraitEncoder = PdfTraitEncoder.createLargeTraitEncoder(baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encodeEssence(PdfContentByte directContent, IGenericCharacter character, SmartRectangle bounds) {
    int value = character.getTrait(OtherTraitType.Essence).getCurrentValue();
    Point essencePosition = new Point(bounds.x, bounds.y + bounds.height - largeTraitEncoder.getTraitHeight());
    largeTraitEncoder.encodeCenteredAndUngrouped(directContent, essencePosition, bounds.width, value, essenceMax);
    int poolHeight = bounds.height - largeTraitEncoder.getTraitHeight() - IVoidStateFormatConstants.TEXT_PADDING;
    int poolLineHeight = poolHeight / 2;
    Point personalPosition = new Point(bounds.x, essencePosition.y - poolLineHeight);
    String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
    encodePool(directContent, personalLabel, character.getPersonalPool(), personalPosition, bounds.width);
    Point peripheralPosition = new Point(bounds.x, essencePosition.y - 2 * poolLineHeight);
    String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
    encodePool(directContent, peripheralLabel, character.getPeripheralPool(), peripheralPosition, bounds.width);
  }

  private void encodePool(PdfContentByte directContent, String label, String poolValue, Point poolPosition, int width) {
    addText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " " + resources.getString("Sheet.Essence.Available"); //$NON-NLS-1$ //$NON-NLS-2$
    float availableTextWidth = getDefaultTextWidth(availableString);
    Point availablePosition = new Point(poolPosition.x + width, poolPosition.y);
    addText(directContent, availableString, availablePosition, PdfContentByte.ALIGN_RIGHT);
    int lineLength = 10;
    Point lineStartPoint = new Point((int) (availablePosition.x - availableTextWidth - lineLength), poolPosition.y);
    drawThinHorizontalLine(directContent, lineStartPoint, lineLength);
    String totalString = poolValue + " " + resources.getString("Sheet.Essence.Total") + " / "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    addText(directContent, totalString, lineStartPoint, PdfContentByte.ALIGN_RIGHT);
  }

  protected final void drawThinHorizontalLine(PdfContentByte directContent, Point position, int length) {
    setFillColorBlack(directContent);
    directContent.setLineWidth(0);
    directContent.moveTo(position.x, position.y);
    directContent.lineTo(position.x + length, position.y);
    directContent.stroke();
  }
}