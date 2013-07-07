package net.sf.anathema.hero.othertraits.sheet.willpower.encoder;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.general.box.AbstractContentEncoder;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.othertraits.sheet.willpower.content.WillpowerContent;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.PADDING;

public class SimpleWillpowerEncoder extends AbstractContentEncoder<WillpowerContent> {

  public SimpleWillpowerEncoder() {
    super(WillpowerContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    PdfTraitEncoder traitEncoder = PdfTraitEncoder.createLargeTraitEncoder();
    WillpowerContent content = createContent(reportSession);
    float padding = PADDING / 2f;
    float width = bounds.width - 2 * padding;
    float leftX = bounds.x + padding;
    float entryHeight = Math.max((bounds.height - padding) / 2, traitEncoder.getTraitHeight());
    float yPosition = bounds.getMaxY() - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, content.getWillpowerValue(), 10);
    yPosition -= entryHeight;
    traitEncoder.encodeSquaresCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, 0, 10);
  }
}
