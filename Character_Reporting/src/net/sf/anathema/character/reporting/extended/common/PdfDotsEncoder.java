package net.sf.anathema.character.reporting.extended.common;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.extended.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

public class PdfDotsEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private BaseFont baseFont;
  private PdfTraitEncoder traitEncoder;
  private OtherTraitType trait;
  private final int traitMax;
  private String traitHeaderKey;

  public PdfDotsEncoder(BaseFont baseFont, IResources resources, OtherTraitType trait, int traitMax, String traitHeaderKey) {
    this.baseFont = baseFont;
    this.traitMax = traitMax;
    this.trait = trait;
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
    this.traitHeaderKey = traitHeaderKey;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return traitHeaderKey; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    float width = bounds.width - IVoidStateFormatConstants.PADDING;
    float leftX = bounds.x + IVoidStateFormatConstants.PADDING / 2f;
    int value = character.getTraitCollection().getTrait(trait).getCurrentValue();
    float entryHeight = Math.max(bounds.height - IVoidStateFormatConstants.PADDING / 2f, traitEncoder.getTraitHeight());
    float yPosition = bounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(directContent, new Position(leftX, yPosition), width, value, traitMax);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}