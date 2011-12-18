package net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.Graphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

public class PdfWillpowerEncoder implements IBoxContentEncoder {

  private PdfTraitEncoder traitEncoder;

  public PdfWillpowerEncoder(BaseFont baseFont) {
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Willpower"; //$NON-NLS-1$
  }

  public void encode(Graphics graphics, ReportContent reportContent) {
    float padding = IVoidStateFormatConstants.PADDING / 2f;
    float width = graphics.getBounds().width - 2 * padding;
    float leftX = graphics.getBounds().x + padding;
    int value = reportContent.getCharacter().getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
    float entryHeight = Math.max((graphics.getBounds().height - padding) / 2, traitEncoder.getTraitHeight());
    float yPosition = graphics.getBounds().getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics.getDirectContent(), new Position(leftX, yPosition), width, value, 10);
    yPosition -= entryHeight;
    traitEncoder.encodeSquaresCenteredAndUngrouped(graphics.getDirectContent(), new Position(leftX, yPosition), width, 0, 10);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
