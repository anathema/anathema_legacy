package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public abstract class AbstractHealthAndMovementEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public AbstractHealthAndMovementEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public String getHeaderKey(ReportContent content) {
    return "MovementHealth"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Bounds tableBounds = new Bounds(bounds.x, bounds.y, (bounds.width * 0.66f), bounds.height);
    ITableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(graphics, reportContent, tableBounds);
    float textX = tableBounds.getMaxX() + IVoidStateFormatConstants.TEXT_PADDING;
    Bounds textBounds = new Bounds(textX, bounds.y, bounds.x + bounds.width - textX, bounds.height - 2);
    encodeText(graphics, textBounds);
  }

  protected abstract ITableEncoder createTableEncoder();

  protected void encodeText(SheetGraphics graphics, Bounds textBounds) throws DocumentException {
    BaseFont baseFont = graphics.getBaseFont();
    Font headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    Font commentFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE, Font.NORMAL, Color.BLACK);
    Font commentTitleFont = new Font(commentFont);
    commentTitleFont.setStyle(Font.BOLD);
    Paragraph healthText = createHealthRulesPhrase(graphics, headerFont, commentFont, commentTitleFont);
    int leading = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 1;
    float yLine = graphics.encodeText(healthText, textBounds, (float) leading).getYLine();
    int rectangleOffset = AbstractHealthAndMovementTableEncoder.HEALTH_RECT_SIZE + 1;
    final float additionalOffset = 2.5f;
    float rectYPosition = yLine - rectangleOffset - additionalOffset;
    float textYPosition = yLine - leading - additionalOffset;
    float xPosition = textBounds.x;
    PdfTemplate rectTemplate = HealthTemplateFactory.createRectTemplate(graphics.getDirectContent(), Color.BLACK);
    graphics.getDirectContent().addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate bashingTemplate = HealthTemplateFactory.createBashingTemplate(graphics.getDirectContent(), Color.GRAY);
    graphics.getDirectContent().addTemplate(bashingTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    final String createSpacedString = createSpacedString(resources.getString("Sheet.Health.Comment.MarkDamageBashing")); //$NON-NLS-1$
    String bashingString = createSpacedString;
    graphics.drawComment(bashingString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += graphics.getCommentTextWidth(bashingString);
    graphics.getDirectContent().addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate lethalTemplate = HealthTemplateFactory.createLethalTemplate(graphics.getDirectContent(), Color.GRAY);
    graphics.getDirectContent().addTemplate(lethalTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    String lethalString = createSpacedString(resources.getString("Sheet.Health.Comment.MarkDamageLethal")); //$NON-NLS-1$
    graphics.drawComment(lethalString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += graphics.getCommentTextWidth(lethalString);
    graphics.getDirectContent().addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate aggravatedTemplate = HealthTemplateFactory.createAggravatedTemplate(graphics.getDirectContent(), Color.GRAY);
    graphics.getDirectContent().addTemplate(aggravatedTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    String aggravatedString = createSpacedString(resources.getString("Sheet.Health.Comment.MarkDamageAggravated")); //$NON-NLS-1$
    graphics.drawComment(aggravatedString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += graphics.getCommentTextWidth(lethalString);
  }

  private String createSpacedString(final String string) {
    return " " + string + "   "; //$NON-NLS-1$ //$NON-NLS-2$
  }

  protected final BaseFont getBaseFont() {
    return baseFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected abstract IExaltedEdition getEdition();

  private Paragraph createHealthRulesPhrase(SheetGraphics graphics, Font headerFont, Font commentFont, Font commentTitleFont) {
    Paragraph healthText = new Paragraph();
    healthText.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
    final Chunk seperator = new Chunk(": ", commentTitleFont); //$NON-NLS-1$
    final Chunk newLine = new Chunk("\n", commentFont); //$NON-NLS-1$
    final Chunk header = new Chunk(resources.getString("Sheet.Health.Comment.Rules"), headerFont); //$NON-NLS-1$
    healthText.add(header);
    healthText.add(newLine);
    healthText.add(graphics.createSymbolChunk());
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.HealthHeader"), commentTitleFont)); //$NON-NLS-1$
    healthText.add(seperator);
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.HealthText"), commentFont)); //$NON-NLS-1$
    healthText.add(newLine);
    healthText.add(graphics.createSymbolChunk());
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.DeathHeader"), commentTitleFont)); //$NON-NLS-1$
    healthText.add(seperator);
    healthText.add(new Chunk(resources.getString("Sheet." + getEdition().getId() + ".Health.Comment.DeathText"),
      commentFont)); //$NON-NLS-1$ //$NON-NLS-2$
    healthText.add(newLine);
    healthText.add(graphics.createSymbolChunk());
    healthText.add(new Chunk(resources.getString("Sheet.Health.Comment.MarkDamageHeader"), commentTitleFont)); //$NON-NLS-1$
    healthText.add(seperator);
    return healthText;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
