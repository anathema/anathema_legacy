package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.SimpleEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static com.lowagie.text.pdf.PdfContentByte.ALIGN_RIGHT;

public class SimpleEssenceBoxContentEncoder extends AbstractBoxContentEncoder<SimpleEssenceContent> {

  public SimpleEssenceBoxContentEncoder() {
    super(SimpleEssenceContent.class);
  }

  @Override
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
    graphics.drawText(content.getAvailableText(), layout.getAvailablePositionRightAligned(poolPosition), ALIGN_RIGHT);
    Position availableLineStart = layout.getAvailableLineStart(poolPosition, content.getAvailableText());
    graphics.drawMissingTextLine(availableLineStart, layout.getMissingValueLineLength());
    graphics.drawText(content.getTotalString(poolValue), availableLineStart, ALIGN_RIGHT);
  }
}
