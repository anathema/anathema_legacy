package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
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
    this.traitEncoder = PdfTraitEncoder.createLargeTraitEncoder();
    this.traitHeaderKey = traitHeaderKey;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header." + traitHeaderKey);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    float width = bounds.width - IVoidStateFormatConstants.PADDING;
    float leftX = bounds.x + IVoidStateFormatConstants.PADDING / 2f;
    int value = reportSession.getCharacter().getTraitCollection().getTrait(trait).getCurrentValue();
    float entryHeight = Math.max(bounds.height - IVoidStateFormatConstants.PADDING / 2f, traitEncoder.getTraitHeight());
    float yPosition = bounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, value, traitMax);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
