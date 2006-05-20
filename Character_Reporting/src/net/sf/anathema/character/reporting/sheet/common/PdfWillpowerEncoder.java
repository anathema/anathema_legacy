package net.sf.anathema.character.reporting.sheet.common;

import java.awt.Point;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfWillpowerEncoder implements IPdfContentEncoder {

  private PdfTraitEncoder traitEncoder;

  public PdfWillpowerEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createLargeTraitEncoder(baseFont);
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds) {
    float width = contentBounds.width;
    int value = character.getTrait(OtherTraitType.Willpower).getCurrentValue();
    float yPosition = (int) contentBounds.getMaxY() - traitEncoder.getTraitHeight();
    traitEncoder.encodeCenteredAndUngrouped(directContent, new Position(contentBounds.x, yPosition), width, value, 10);
    yPosition -= traitEncoder.getTraitHeight();
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, new Position(contentBounds.x, yPosition), width, 0, 10);
  }
}