package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

public class PdfDotsEncoder implements IBoxContentEncoder {

  private PdfTraitEncoder traitEncoder;
  private OtherTraitType trait;
  private final int traitMax;
  private String traitHeaderKey;

  public PdfDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey) {
    this.traitMax = traitMax;
    this.trait = trait;
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder();
    this.traitHeaderKey = traitHeaderKey;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return traitHeaderKey; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float width = bounds.width - IVoidStateFormatConstants.PADDING;
    float leftX = bounds.x + IVoidStateFormatConstants.PADDING / 2f;
    int value = reportContent.getCharacter().getTraitCollection().getTrait(trait).getCurrentValue();
    float entryHeight = Math.max(bounds.height - IVoidStateFormatConstants.PADDING / 2f, traitEncoder.getTraitHeight());
    float yPosition = bounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, value, traitMax);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
