package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class DotBoxContentEncoder implements ContentEncoder {

  private PdfTraitEncoder traitEncoder;
  private OtherTraitType trait;
  private IResources resources;
  private final int traitMax;
  private String traitHeaderKey;

  public DotBoxContentEncoder(OtherTraitType trait, int traitMax, IResources resources, String traitHeaderKey) {
    this.traitMax = traitMax;
    this.trait = trait;
    this.resources = resources;
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder();
    this.traitHeaderKey = traitHeaderKey;
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header." + traitHeaderKey);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float width = bounds.width - IVoidStateFormatConstants.PADDING;
    float leftX = bounds.x + IVoidStateFormatConstants.PADDING / 2f;
    int value = reportContent.getCharacter().getTraitCollection().getTrait(trait).getCurrentValue();
    float entryHeight = Math.max(bounds.height - IVoidStateFormatConstants.PADDING / 2f, traitEncoder.getTraitHeight());
    float yPosition = bounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, value, traitMax);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
