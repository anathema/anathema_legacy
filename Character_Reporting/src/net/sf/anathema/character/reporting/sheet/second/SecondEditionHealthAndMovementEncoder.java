package net.sf.anathema.character.reporting.sheet.second;

import java.awt.Color;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionHealthAndMovementEncoder implements IPdfContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;

  public SecondEditionHealthAndMovementEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Bounds tableBounds = new Bounds(bounds.x, bounds.y, (bounds.width * 0.66f), bounds.height);
    IPdfTableEncoder tableEncoder = new SecondEditionHealthAndMovemenTableEncoder(resources, baseFont);
    tableEncoder.encodeTable(directContent, character, tableBounds);
    float textX = tableBounds.getMaxX() + IVoidStateFormatConstants.TEXT_PADDING;
    Bounds textBounds = new Bounds(textX, bounds.y, bounds.x + bounds.width - textX, bounds.height - 2);
    encodeText(directContent, textBounds);
  }

  private void encodeText(PdfContentByte directContent, Bounds textBounds) throws DocumentException {
    Font headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    Font commentFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE, Font.NORMAL, Color.BLACK);
    Font commentTitleFont = new Font(commentFont);
    commentTitleFont.setStyle(Font.BOLD);
    Paragraph healthText = new Paragraph();
    healthText.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
    final Chunk seperator = new Chunk(": ", commentTitleFont); //$NON-NLS-1$
    final Chunk newLine = new Chunk("\n", commentFont); //$NON-NLS-1$
    final Chunk header = new Chunk(resources.getString("Sheet.Health.Comment.Rules"), headerFont); //$NON-NLS-1$
    final Chunk caretChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
    healthText.add(header);
    healthText.add(newLine);
    healthText.add(caretChunk);
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.HealthHeader"), commentTitleFont)); //$NON-NLS-1$
    healthText.add(seperator);
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.HealthText"), commentFont)); //$NON-NLS-1$
    healthText.add(newLine);
    healthText.add(caretChunk);
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.DeathHeader"), commentTitleFont)); //$NON-NLS-1$
    healthText.add(seperator);
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.DeathText"), commentFont)); //$NON-NLS-1$
    healthText.add(newLine);
    healthText.add(caretChunk);
    PdfTextEncodingUtilities.encodeText(
        directContent,
        healthText,
        textBounds,
        IVoidStateFormatConstants.COMMENT_FONT_SIZE + 1);
    // ¨ Marking Damage: Bashing, Lethal, Aggravated
  }
}