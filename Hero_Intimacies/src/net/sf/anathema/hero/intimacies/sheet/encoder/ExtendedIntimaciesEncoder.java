package net.sf.anathema.hero.intimacies.sheet.encoder;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.hero.intimacies.sheet.content.ExtendedIntimaciesContent;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.LINE_HEIGHT;

public class ExtendedIntimaciesEncoder extends AbstractContentEncoder<ExtendedIntimaciesContent> {
  private final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder();

  public ExtendedIntimaciesEncoder() {
    super(ExtendedIntimaciesContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    ExtendedIntimaciesContent content = createContent(reportSession);
    float yPosition = bounds.getMaxY() - LINE_HEIGHT;
    for (NamedValue printIntimacy : content.getPrintIntimacies()) {
      if (yPosition < bounds.getMinY()) {
        return;
      }
      traitEncoder.encode(graphics, printIntimacy, new Position(bounds.x, yPosition), bounds.width, content.getTraitMaxValue());
      yPosition -= LINE_HEIGHT;
    }
    encodeEmptyLines(graphics, bounds, yPosition, content.getTraitMaxValue());
  }

  private void encodeEmptyLines(SheetGraphics graphics, Bounds bounds, float yPosition, int maxValue) {
    while (yPosition > bounds.getMinY()) {
      Position position = new Position(bounds.x, yPosition);
      traitEncoder.encodeWithLine(graphics, position, bounds.width, 0, maxValue);
      yPosition -= LINE_HEIGHT;
    }
  }
}
