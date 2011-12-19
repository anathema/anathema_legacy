package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;

import static com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT;

public class SimpleEssenceBoxContentEncoder implements IBoxContentEncoder {

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    SimpleEssenceContent content = createContent(reportContent);
    SimpleEssenceBoxLayout layout = new SimpleEssenceBoxLayout(graphics, bounds, content.getNumberOfPoolLines());
    encodeEssenceTrait(graphics, content, layout);
    encodePersonalPool(graphics, content, layout);
    encodePeripheralPool(graphics, content, layout);
  }

  private void encodeEssenceTrait(SheetGraphics graphics, SimpleEssenceContent content, SimpleEssenceBoxLayout layout) {
    Position essencePosition = layout.getEssencePosition();
    int essenceValue = content.getEssenceValue();
    int essenceMax = content.getEssenceMax();
    PdfTraitEncoder largeTraitEncoder = PdfTraitEncoder.createTraitEncoder(layout.getEssenceDotSize());
    largeTraitEncoder.encodeDotsCenteredAndUngrouped(graphics, essencePosition, layout.geWidth(), essenceValue, essenceMax);
  }

  private void encodePersonalPool(SheetGraphics graphics, SimpleEssenceContent content, SimpleEssenceBoxLayout layout) {
    if (content.hasPersonalPool()) {
      Position personalPosition = layout.getFirstPoolPosition();
      String personalLabel = content.getPersonalPoolLabel();
      encodePool(graphics, content, layout, personalLabel, content.getPersonalPool(), personalPosition);
    }
  }

  private void encodePeripheralPool(SheetGraphics graphics, SimpleEssenceContent content, SimpleEssenceBoxLayout layout) {
    if (content.hasPeripheralPool()) {
      Position peripheralPosition = content.getNumberOfPoolLines() == 1 ? layout.getFirstPoolPosition() : layout.getSecondPoolPosition();
      String peripheralLabel = content.getPeripheralPoolLabel();
      encodePool(graphics, content, layout, peripheralLabel, content.getPeripheralPool(), peripheralPosition);
    }
  }

  private void encodePool(SheetGraphics graphics, SimpleEssenceContent content, SimpleEssenceBoxLayout layout, String label, String poolValue, Position poolPosition) {
    graphics.drawText(label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = content.getAvailableString();
    Position availablePosition = layout.getAvailabilityPositionRightAligned(poolPosition);
    graphics.drawText(availableString, availablePosition, ALIGN_RIGHT);
    Position lineStartPoint = layout.getAvailabilityLineStartPosition(poolPosition, availableString);
    graphics.drawMissingTextLine(lineStartPoint, layout.getMissingValueLineLength());
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
