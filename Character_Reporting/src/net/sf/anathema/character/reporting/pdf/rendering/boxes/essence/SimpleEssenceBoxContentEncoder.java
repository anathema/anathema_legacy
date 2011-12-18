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
import net.sf.anathema.lib.resources.IResources;

import static com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT;

public class SimpleEssenceBoxContentEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {

  private BaseFont baseFont;
  private final IResources resources;
  private PdfTraitEncoder largeTraitEncoder;
  private final int essenceMax;

  public SimpleEssenceBoxContentEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
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
    String personalPool = null;
    String peripheralPool = null;
    try {
      personalPool = reportContent.getCharacter().getPersonalPool();
      peripheralPool = reportContent.getCharacter().getPeripheralPool();
    }
    catch (ContractFailedException e) {
    }
    float traitHeight = largeTraitEncoder.getTraitHeight();
    int numberOfLines = (personalPool == null ? 0 : 1) + (peripheralPool == null ? 0 : 1);
    SimpleEssenceBoxLayout layout = new SimpleEssenceBoxLayout(this, graphics.getBounds(), traitHeight, numberOfLines);
    int value = reportContent.getCharacter().getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    Position essencePosition = layout.getEssencePosition();
    largeTraitEncoder.encodeDotsCenteredAndUngrouped(graphics.getDirectContent(), essencePosition, graphics.getBounds().width, value, essenceMax);

    if (personalPool != null) {
      Position personalPosition = layout.getFirstPoolPosition();
      String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
      encodePool(graphics.getDirectContent(), personalLabel, personalPool, personalPosition, layout);
    }

    if (peripheralPool != null) {
      Position peripheralPosition = numberOfLines == 1 ? layout.getFirstPoolPosition() : layout.getSecondPoolPosition();
      String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
      encodePool(graphics.getDirectContent(), peripheralLabel, peripheralPool, peripheralPosition, layout);
    }
  }

  private void encodePool(PdfContentByte directContent, String label, String poolValue, Position poolPosition, SimpleEssenceBoxLayout layout) {
    drawText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " " + resources.getString("Sheet.Essence.Available"); //$NON-NLS-1$ //$NON-NLS-2$
    Position availablePosition = layout.getAvailabilityPositionRightAligned(poolPosition);
    drawText(directContent, availableString, availablePosition, ALIGN_RIGHT);
    Position lineStartPoint = layout.getAvailabilityLineStartPosition(poolPosition, availableString);
    drawMissingTextLine(directContent, lineStartPoint, layout.getAvailabilityLineLength());
    String totalString = poolValue + " " + resources.getString("Sheet.Essence.Total") + " / "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    drawText(directContent, totalString, lineStartPoint, ALIGN_RIGHT);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
