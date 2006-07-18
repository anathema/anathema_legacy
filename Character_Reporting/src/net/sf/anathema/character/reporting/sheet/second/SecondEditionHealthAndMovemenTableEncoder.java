package net.sf.anathema.character.reporting.sheet.second;

import java.awt.Color;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.sheet.common.movement.AbstractHealthAndMovemenTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;

public class SecondEditionHealthAndMovemenTableEncoder extends AbstractHealthAndMovemenTableEncoder {

  public SecondEditionHealthAndMovemenTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected final Float[] getMovementColumns() {
    return new Float[] { 1f, PADDING, 1f, PADDING, 1f, 1f };
  }

  @Override
  protected final Phrase createIncapacitatedComment() {
    return new Phrase(getResources().getString("Sheet.Movement.Comment.Mobility"), getCommentFont()); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementHeader(PdfPTable table) {
    table.addCell(createHeaderCell(getResources().getString("Sheet.Movement.Move"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(getResources().getString("Sheet.Movement.Dash"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(getResources().getString("Sheet.Movement.Jump"), 3)); //$NON-NLS-1$
  }

  public static PdfTemplate createRectTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate activeHealthRect = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    activeHealthRect.setLineWidth(1f);
    activeHealthRect.setColorStroke(strokeColor);
    activeHealthRect.rectangle(0, 0, HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    activeHealthRect.stroke();
    return activeHealthRect;
  }

  public static PdfTemplate createBashingTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate bashingSlash = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    bashingSlash.setLineWidth(1f);
    bashingSlash.setColorStroke(strokeColor);
    bashingSlash.moveTo(0, 0);
    bashingSlash.lineTo(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    bashingSlash.stroke();
    return bashingSlash;
  }

  public static PdfTemplate createLethalTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate lethalCross = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    lethalCross.addTemplate(createBashingTemplate(directContent, strokeColor), 0, 0);
    lethalCross.setLineWidth(1f);
    lethalCross.setColorStroke(strokeColor);
    lethalCross.moveTo(0, HEALTH_RECT_SIZE);
    lethalCross.lineTo(HEALTH_RECT_SIZE, 0);
    lethalCross.stroke();
    return lethalCross;
  }

  public static PdfTemplate createAggravatedTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate aggravatedStar = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    aggravatedStar.addTemplate(createLethalTemplate(directContent, strokeColor), 0, 0);
    aggravatedStar.setLineWidth(1f);
    aggravatedStar.setColorStroke(strokeColor);
    aggravatedStar.moveTo(HEALTH_RECT_SIZE / 2f, 0);
    aggravatedStar.lineTo(HEALTH_RECT_SIZE / 2f, HEALTH_RECT_SIZE);
    aggravatedStar.stroke();
    return aggravatedStar;
  }

  @Override
  protected final void addMovementCells(PdfPTable table, IGenericCharacter character, HealthLevelType level, int painTolerance) {
    int penalty = getPenalty(level, painTolerance);
    int dexValue = character.getTrait(AttributeType.Dexterity).getCurrentValue();
    int moveValue = dexValue + penalty;
    table.addCell(createMovementCell(moveValue, 1));
    addSpaceCells(table, 1);
    table.addCell(createMovementCell(moveValue + 6, 2));
    int verticalJump = character.getTrait(AttributeType.Strength).getCurrentValue()
        + character.getTrait(AbilityType.Athletics).getCurrentValue()
        + penalty;
    addSpaceCells(table, 1);
    table.addCell(createMovementCell(verticalJump * 2, 0));
    table.addCell(createMovementCell(verticalJump, 0));
  }
}