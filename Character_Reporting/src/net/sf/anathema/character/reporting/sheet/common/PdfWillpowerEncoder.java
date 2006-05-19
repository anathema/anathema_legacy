package net.sf.anathema.character.reporting.sheet.common;

import java.awt.Point;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfWillpowerEncoder {

  private PdfTraitEncoder traitEncoder;

  public PdfWillpowerEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createLargeTraitEncoder(baseFont);
  }

  public void encodeWillpower(PdfContentByte directContent, IGenericCharacter character, SmartRectangle contentBounds) {
    int width = contentBounds.width;
    int value = character.getTrait(OtherTraitType.Willpower).getCurrentValue();
    int yPosition = (int) contentBounds.getMaxY() - traitEncoder.getTraitHeight();
    traitEncoder.encodeCenteredAndUngrouped(directContent, new Point(contentBounds.x, yPosition), width, value, 10);
    yPosition -= traitEncoder.getTraitHeight();
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, new Point(contentBounds.x, yPosition), width, 0, 10);
  }
}