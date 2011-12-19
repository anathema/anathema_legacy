package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;

import static com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT;

public class SimpleEssenceBoxContentEncoder implements IBoxContentEncoder {

  private PdfTraitEncoder largeTraitEncoder;

  public SimpleEssenceBoxContentEncoder() {
    this.largeTraitEncoder = PdfTraitEncoder.createTraitEncoder(PdfTraitEncoder.LARGE_DOT_SIZE);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    SimpleEssenceContent content = createContent(reportContent);
    String personalPool = content.getPersonalPool();
    String peripheralPool = content.getPeripheralPool();
    float traitHeight = largeTraitEncoder.getTraitHeight();
    int numberOfLines = (personalPool == null ? 0 : 1) + (peripheralPool == null ? 0 : 1);
    SimpleEssenceBoxLayout layout = new SimpleEssenceBoxLayout(graphics, bounds, traitHeight, numberOfLines);
    int value = reportContent.getCharacter().getTraitCollection().getTrait(OtherTraitType.Essence).getCurrentValue();
    Position essencePosition = layout.getEssencePosition();
    largeTraitEncoder.encodeDotsCenteredAndUngrouped(graphics, essencePosition, bounds.width, value, content.getEssenceMax());

    if (personalPool != null) {
      Position personalPosition = layout.getFirstPoolPosition();
      String personalLabel = content.getPersonalPoolLabel();
      encodePool(graphics, content, personalLabel, personalPool, personalPosition, layout);
    }

    if (peripheralPool != null) {
      Position peripheralPosition = numberOfLines == 1 ? layout.getFirstPoolPosition() : layout.getSecondPoolPosition();
      String peripheralLabel = content.getPeripheralPoolLabel();
      encodePool(graphics, content, peripheralLabel, peripheralPool, peripheralPosition, layout);
    }
  }

  private void encodePool(SheetGraphics graphics,SimpleEssenceContent content, String label, String poolValue, Position poolPosition,
    SimpleEssenceBoxLayout layout) {
    graphics.drawText(label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = content.getAvailableString();
    Position availablePosition = layout.getAvailabilityPositionRightAligned(poolPosition);
    graphics.drawText(availableString, availablePosition, ALIGN_RIGHT);
    Position lineStartPoint = layout.getAvailabilityLineStartPosition(poolPosition, availableString);
    graphics.drawMissingTextLine(lineStartPoint, layout.getAvailabilityLineLength());
    String totalString = content.getTotalString(poolValue);
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
