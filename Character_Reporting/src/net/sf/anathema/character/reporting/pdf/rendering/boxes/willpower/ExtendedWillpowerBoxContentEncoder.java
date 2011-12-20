package net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.general.BulletList;
import net.sf.anathema.character.reporting.pdf.content.willpower.WillpowerContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.ListUtils;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.traits.PdfTraitEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class ExtendedWillpowerBoxContentEncoder implements IBoxContentEncoder {

  public String getHeaderKey(ReportContent content) {
    return "Willpower"; //$NON-NLS-1$
  }

  private WillpowerContent createContent(ReportContent content) {
    return content.createSubContent(WillpowerContent.class);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float lineHeight = 1.1f * IVoidStateFormatConstants.COMMENT_FONT_SIZE;
    PdfTraitEncoder traitEncoder = PdfTraitEncoder.createMediumTraitEncoder();
    WillpowerContent content = createContent(reportContent);
    float padding = IVoidStateFormatConstants.PADDING / 2f;
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
    Phrase spendingPhrase = new Phrase("", graphics.createCommentFont());  //$NON-NLS-1$
    BulletList willpowerSpendingRules = content.getWillpowerSpendingRules();
    Chunk symbolChunk = graphics.createSymbolChunk()  ;
    ListUtils.addBulletList(spendingPhrase, symbolChunk, willpowerSpendingRules.header, willpowerSpendingRules.items);
    spendingPhrase.add("\n" + content.getWillpowerSpendingNote() + "\n"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), spendingPhrase, spendingBounds, lineHeight).getYLine();

    float centerX = leftX + columnWidth + columnPadding;
    Bounds regainingBounds = new Bounds(centerX, yPosition - height, columnWidth, height);
    Phrase regainingPhrase = new Phrase("", graphics.createCommentFont());   //$NON-NLS-1$
    BulletList willpowerRegainingRules = content.getWillpowerRegainingRules();
    ListUtils.addBulletList(regainingPhrase, symbolChunk, willpowerRegainingRules.header, willpowerRegainingRules.items);
    PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), regainingPhrase, regainingBounds, lineHeight).getYLine();
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
