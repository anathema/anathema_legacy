package net.sf.anathema.hero.spiritual.sheet.willpower.encoder;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.ListUtils;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.spiritual.sheet.willpower.content.WillpowerContent;
import net.sf.anathema.hero.traits.sheet.content.BulletList;
import net.sf.anathema.hero.traits.sheet.content.PdfTraitEncoder;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.PADDING;

public class ExtendedWillpowerEncoder extends AbstractContentEncoder<WillpowerContent> {

  public ExtendedWillpowerEncoder() {
    super(WillpowerContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    float lineHeight = 1.05f * COMMENT_FONT_SIZE;
    PdfTraitEncoder traitEncoder = PdfTraitEncoder.createLargeTraitEncoder();
    WillpowerContent content = createContent(reportSession);
    float padding = PADDING / 4f;
    float width = bounds.width - 2f * padding;
    float leftX = bounds.x + padding;
    float height = bounds.height - padding;
    float topY = bounds.getMaxY();

    float entryHeight = traitEncoder.getTraitHeight();
    float yPosition = topY - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, content.getWillpowerValue(), 10);
    yPosition -= entryHeight;
    traitEncoder.encodeSquaresCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, 0, 10);
    height -= 2f * entryHeight;

    yPosition -= lineHeight;
    height -= lineHeight;

    float columnPadding = padding / 2f;
    float columnWidth = (width - columnPadding) / 2f;

    Bounds spendingBounds = new Bounds(leftX, yPosition - height, columnWidth, height);
    Phrase spendingPhrase = new Phrase("", graphics.createCommentFont());
    BulletList willpowerSpendingRules = content.getWillpowerSpendingRules();
    Chunk symbolChunk = graphics.createSymbolChunk();
    ListUtils.addBulletList(spendingPhrase, symbolChunk, willpowerSpendingRules.header, willpowerSpendingRules.items);
    spendingPhrase.add("\n" + content.getWillpowerSpendingNote() + "\n");
    graphics.createSimpleColumn(spendingBounds).withLeading(lineHeight).andTextPart(spendingPhrase).encode().getYLine();

    float centerX = leftX + columnWidth + columnPadding;
    Bounds regainingBounds = new Bounds(centerX, yPosition - height, columnWidth, height);
    Phrase regainingPhrase = new Phrase("", graphics.createCommentFont());
    BulletList willpowerRegainingRules = content.getWillpowerRegainingRules();
    ListUtils.addBulletList(regainingPhrase, symbolChunk, willpowerRegainingRules.header, willpowerRegainingRules.items);
    graphics.createSimpleColumn(regainingBounds).withLeading(lineHeight).andTextPart(regainingPhrase).encode().getYLine();
  }
}
