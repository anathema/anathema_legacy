package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class PdfEssenceEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {

  private BaseFont baseFont;
  private final IResources resources;
  private PdfTraitEncoder largeTraitEncoder;
  private final int essenceMax;

  public PdfEssenceEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.largeTraitEncoder = PdfTraitEncoder.createLargeTraitEncoder(baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Essence"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    String personalPool = null, peripheralPool = null;
    try {
      personalPool = reportContent.getCharacter().getPersonalPool();
      peripheralPool = reportContent.getCharacter().getPeripheralPool();
    }
    catch (ContractFailedException e) {
    }
    int numberOfLines = (personalPool == null ? 0 : 1) + (peripheralPool == null ? 0 : 1);
    float poolHeight = graphics.getBounds().height - largeTraitEncoder.getTraitHeight() - IVoidStateFormatConstants.TEXT_PADDING;
    float poolLineHeight = poolHeight / 2;
    int offset = (int) ((2 - numberOfLines) * (poolLineHeight / 2));
    int value = reportContent.getCharacter().getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    Position essencePosition = new Position(graphics.getBounds().x, graphics.getBounds().y + graphics.getBounds().height - largeTraitEncoder
      .getTraitHeight() - offset);
    largeTraitEncoder.encodeDotsCenteredAndUngrouped(graphics.getDirectContent(), essencePosition, graphics.getBounds().width, value, essenceMax);

    if (personalPool != null) {
      Position personalPosition = new Position(graphics.getBounds().x, essencePosition.y - poolLineHeight);
      String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
      encodePool(graphics.getDirectContent(), personalLabel, personalPool, personalPosition, graphics.getBounds().width);
    }

    if (peripheralPool != null) {
      Position peripheralPosition = new Position(graphics.getBounds().x, essencePosition.y - (numberOfLines == 1 ? 1 : 2) * poolLineHeight);
      String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
      encodePool(graphics.getDirectContent(), peripheralLabel, peripheralPool, peripheralPosition, graphics.getBounds().width);
    }
  }

  private void encodePool(PdfContentByte directContent, String label, String poolValue, Position poolPosition, float width) {
    drawText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " " + resources.getString("Sheet.Essence.Available"); //$NON-NLS-1$ //$NON-NLS-2$
    float availableTextWidth = getDefaultTextWidth(availableString);
    Position availablePosition = new Position(poolPosition.x + width, poolPosition.y);
    drawText(directContent, availableString, availablePosition, PdfContentByte.ALIGN_RIGHT);
    float lineLength = 10;
    Position lineStartPoint = new Position((int) (availablePosition.x - availableTextWidth - lineLength), poolPosition.y);
    drawMissingTextLine(directContent, lineStartPoint, lineLength);
    String totalString = poolValue + " " + resources.getString("Sheet.Essence.Total") + " / "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    drawText(directContent, totalString, lineStartPoint, PdfContentByte.ALIGN_RIGHT);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
