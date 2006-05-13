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
    String personalLabel = "Personal Pool:";
    encodePool(directContent, personalLabel, character.getPersonalPool(), personalPosition, bounds.width);
    Point peripheralPosition = new Point(bounds.x, essencePosition.y - 2 * poolLineHeight);
    String peripheralLabel = "Peripheral Pool:";
    encodePool(directContent, peripheralLabel, character.getPeripheralPool(), peripheralPosition, bounds.width);
  }

  private void encodePool(PdfContentByte directContent, String label, String poolValue, Point poolPosition, int width) {
    addLabelledContent(directContent, label, poolValue, poolPosition, width);
  }
}