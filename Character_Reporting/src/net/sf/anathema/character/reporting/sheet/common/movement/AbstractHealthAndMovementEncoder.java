package net.sf.anathema.character.reporting.sheet.common.movement;

import java.awt.Color;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;

public abstract class AbstractHealthAndMovementEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;

  public AbstractHealthAndMovementEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "MovementHealth"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds)
      throws DocumentException {
    Bounds tableBounds = new Bounds(bounds.x, bounds.y, (bounds.width * 0.66f), bounds.height);
    IPdfTableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(directContent, character, tableBounds);
    float textX = tableBounds.getMaxX() + IVoidStateFormatConstants.TEXT_PADDING;
    Bounds textBounds = new Bounds(textX, bounds.y, bounds.x + bounds.width - textX, bounds.height - 2);
    encodeText(directContent, textBounds);
  }

  protected abstract IPdfTableEncoder createTableEncoder();

  protected void encodeText(PdfContentByte directContent, Bounds textBounds) throws DocumentException {
    Font headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    Font commentFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE, Font.NORMAL, Color.BLACK);
    Font commentTitleFont = new Font(commentFont);
    commentTitleFont.setStyle(Font.BOLD);
    Paragraph healthText = createHealthRulesPhrase(headerFont, commentFont, commentTitleFont);
    int leading = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 1;
    ColumnText text = PdfTextEncodingUtilities.encodeText(directContent, healthText, textBounds, leading);
    int rectangleOffset = AbstractHealthAndMovementTableEncoder.HEALTH_RECT_SIZE + 1;
    final float additionalOffset = 2.5f;
    float rectYPosition = text.getYLine() - rectangleOffset - additionalOffset;
    float textYPosition = text.getYLine() - leading - additionalOffset;
    float xPosition = textBounds.x;
    PdfTemplate rectTemplate = AbstractHealthAndMovementTableEncoder.createRectTemplate(directContent, Color.BLACK);
    directContent.addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate bashingTemplate = AbstractHealthAndMovementTableEncoder.createBashingTemplate(directContent, Color.GRAY);
    directContent.addTemplate(bashingTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    final String createSpacedString = createSpacedString(resources.getString("Sheet.Health.Comment.MarkDamageBashing")); //$NON-NLS-1$
    String bashingString = createSpacedString;
    drawComment(directContent, bashingString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += getCommentTextWidth(bashingString);
    directContent.addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate lethalTemplate = AbstractHealthAndMovementTableEncoder.createLethalTemplate(directContent, Color.GRAY);
    directContent.addTemplate(lethalTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    String lethalString = createSpacedString(resources.getString("Sheet.Health.Comment.MarkDamageLethal")); //$NON-NLS-1$
    drawComment(directContent, lethalString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += getCommentTextWidth(lethalString);
    directContent.addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate aggravatedTemplate = AbstractHealthAndMovementTableEncoder.createAggravatedTemplate(
        directContent,
        Color.GRAY);
    directContent.addTemplate(aggravatedTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    String aggravatedString = createSpacedString(resources.getString("Sheet.Health.Comment.MarkDamageAggravated")); //$NON-NLS-1$
    drawComment(directContent, aggravatedString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += getCommentTextWidth(lethalString);
  }

  private String createSpacedString(final String string) {
    return " " + string + "   "; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  protected final BaseFont getBaseFont() {
    return baseFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected abstract IExaltedEdition getEdition();

  private Paragraph createHealthRulesPhrase(Font headerFont, Font commentFont, Font commentTitleFont) {
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
    healthText.add(new Chunk(
        resources.getString("Sheet." + getEdition().getId() + ".Health.Comment.DeathText"), commentFont)); //$NON-NLS-1$ //$NON-NLS-2$
    healthText.add(newLine);
    healthText.add(caretChunk);
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.MarkDamageHeader"), commentTitleFont)); //$NON-NLS-1$
    healthText.add(seperator);
    return healthText;
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}