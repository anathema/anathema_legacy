package net.sf.anathema.character.reporting.pdf.rendering.boxes.willpower;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
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

  private PdfTraitEncoder traitEncoder;
  private final IResources resources;
  private final BaseFont baseFont;
  private final Chunk symbolChunk;

  private final float fontSize = 5f;
  private final float lineHeight = 1.1f * fontSize;

  public ExtendedWillpowerBoxContentEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder();
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  public String getHeaderKey(ReportContent content) {
    return "Willpower"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float padding = IVoidStateFormatConstants.PADDING / 2f;
    float width = bounds.width - 2f * padding;
    float leftX = bounds.x + padding;
    float height = bounds.height - padding;
    float topY = bounds.getMaxY();

    int value = reportContent.getCharacter().getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
    float entryHeight = traitEncoder.getTraitHeight();
    float yPosition = topY - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, value, 10);
    yPosition -= entryHeight;
    traitEncoder.encodeSquaresCenteredAndUngrouped(graphics, new Position(leftX, yPosition), width, 0, 10);
    height -= 2f * entryHeight;

    yPosition -= lineHeight;
    height -= lineHeight;

    float columnPadding = padding / 2f;
    float columnWidth = (width - columnPadding) / 2f;

    Bounds spendingBounds = new Bounds(leftX, yPosition - height, columnWidth, height);
    Phrase spendingPhrase = new Phrase("", new Font(baseFont, fontSize, //$NON-NLS-1$
      Font.NORMAL, Color.BLACK));
    ListUtils.addBulletedListText(resources, symbolChunk, reportContent.getCharacter().getRules().getEdition(), "Sheet.WillpowerSpendingRules",
    //$NON-NLS-1$
      spendingPhrase, true);
    spendingPhrase.add("\n"); //$NON-NLS-1$
    String spendingNote = ListUtils.getRequiredString(resources, "Sheet.WillpowerSpendingNote", reportContent.getCharacter().getRules().getEdition
      ());
    spendingPhrase.add(spendingNote + "\n"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), spendingPhrase, spendingBounds, lineHeight).getYLine();

    float centerX = leftX + columnWidth + columnPadding;
    Bounds regainingBounds = new Bounds(centerX, yPosition - height, columnWidth, height);
    Phrase regainingPhrase = new Phrase("", new Font(baseFont, fontSize, //$NON-NLS-1$
      Font.NORMAL, Color.BLACK));
    ListUtils.addBulletedListText(resources, symbolChunk, reportContent.getCharacter().getRules().getEdition(), "Sheet.WillpowerRegainingRules",
    //$NON-NLS-1$
      regainingPhrase, true);
    PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), regainingPhrase, regainingBounds, lineHeight).getYLine();
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
