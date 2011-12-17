package net.sf.anathema.character.reporting.sheet.common;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;

public class PdfWillpowerEncoder implements IPdfContentBoxEncoder {

  private PdfTraitEncoder traitEncoder;

  public PdfWillpowerEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Willpower"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds contentBounds) {
    float padding = IVoidStateFormatConstants.PADDING / 2f;
    float width = contentBounds.width - 2 * padding;
    float leftX = contentBounds.x + padding;
    int value = character.getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
    float entryHeight = Math.max((contentBounds.height - padding) / 2, traitEncoder.getTraitHeight());
    float yPosition = contentBounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(directContent, new Position(leftX, yPosition), width, value, 10);
    yPosition -= entryHeight;
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, new Position(leftX, yPosition), width, 0, 10);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
