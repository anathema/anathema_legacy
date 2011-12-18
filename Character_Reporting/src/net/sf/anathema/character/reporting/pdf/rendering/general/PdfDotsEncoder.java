package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class PdfDotsEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {

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

  public String getHeaderKey(ReportContent reportContent) {
    return traitHeaderKey; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float width = bounds.width - IVoidStateFormatConstants.PADDING;
    float leftX = bounds.x + IVoidStateFormatConstants.PADDING / 2f;
    int value = reportContent.getCharacter().getTraitCollection().getTrait(trait).getCurrentValue();
    float entryHeight = Math.max(bounds.height - IVoidStateFormatConstants.PADDING / 2f, traitEncoder.getTraitHeight());
    float yPosition = bounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics.getDirectContent(), new Position(leftX, yPosition), width, value, traitMax);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
