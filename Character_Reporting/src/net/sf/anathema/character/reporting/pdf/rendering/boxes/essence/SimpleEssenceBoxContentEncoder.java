package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

import static com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT;

public class SimpleEssenceBoxContentEncoder implements IBoxContentEncoder {

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

  public void encode(PdfGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    SimpleEssenceContent content = createContent(reportContent);
    String personalPool = content.getPersonalPool();
    String peripheralPool = content.getPeripheralPool();
    float traitHeight = largeTraitEncoder.getTraitHeight();
    int numberOfLines = (personalPool == null ? 0 : 1) + (peripheralPool == null ? 0 : 1);
    SimpleEssenceBoxLayout layout = new SimpleEssenceBoxLayout(graphics, bounds, traitHeight, numberOfLines);
    int value = reportContent.getCharacter().getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    Position essencePosition = layout.getEssencePosition();
    largeTraitEncoder.encodeDotsCenteredAndUngrouped(graphics.getDirectContent(), essencePosition, bounds.width, value, essenceMax);

    if (personalPool != null) {
      Position personalPosition = layout.getFirstPoolPosition();
      String personalLabel = resources.getString("Sheet.Essence.PersonalPool"); //$NON-NLS-1$
      encodePool(graphics, personalLabel, personalPool, personalPosition, layout);
    }

    if (peripheralPool != null) {
      Position peripheralPosition = numberOfLines == 1 ? layout.getFirstPoolPosition() : layout.getSecondPoolPosition();
      String peripheralLabel = resources.getString("Sheet.Essence.PeripheralPool"); //$NON-NLS-1$
      encodePool(graphics, peripheralLabel, peripheralPool, peripheralPosition, layout);
    }
  }

  private void encodePool(PdfGraphics graphics, String label, String poolValue, Position poolPosition, SimpleEssenceBoxLayout layout) {
    graphics.drawText(label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " " + resources.getString("Sheet.Essence.Available"); //$NON-NLS-1$ //$NON-NLS-2$
    Position availablePosition = layout.getAvailabilityPositionRightAligned(poolPosition);
    graphics.drawText(availableString, availablePosition, ALIGN_RIGHT);
    Position lineStartPoint = layout.getAvailabilityLineStartPosition(poolPosition, availableString);
    graphics.drawMissingTextLine(lineStartPoint, layout.getAvailabilityLineLength());
    String totalString = poolValue + " " + resources.getString("Sheet.Essence.Total") + " / "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    graphics.drawText(totalString, lineStartPoint, ALIGN_RIGHT);
  }

  public String getHeaderKey(ReportContent content) {
    return createContent(content).getHeaderKey();
  }

  public boolean hasContent(ReportContent content) {
    return createContent(content).hasContent();
  }

  private SimpleEssenceContent createContent(ReportContent content) {
    return content.createSubContent(SimpleEssenceContent.class);
  }
}
