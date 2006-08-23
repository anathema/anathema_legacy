package net.sf.anathema.character.reporting.sheet.first;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.sheet.common.movement.AbstractHealthAndMovemenTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class FirstEditionHealthAndMovemenTableEncoder extends AbstractHealthAndMovemenTableEncoder {

  public FirstEditionHealthAndMovemenTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected final Float[] getMovementColumns() {
    return new Float[] { 1f, PADDING, 1f, PADDING, 1f };
  }

  @Override
  protected final Phrase createIncapacitatedComment() {
    return new Phrase(getResources().getString("Sheet.FirstEdition.Movement.Comment.Mobility"), getCommentFont()); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementHeader(PdfPTable table) {
    table.addCell(createHeaderCell(getResources().getString("Sheet.Movement.Move"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(getResources().getString("Sheet.Movement.Run"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(getResources().getString("Sheet.Movement.Sprint"), 2)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementCells(
      PdfPTable table,
      HealthLevelType level,
      int painTolerance,
      IGenericTraitCollection collection) {
    int penalty = getPenalty(level, painTolerance);
    int dexValue = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    int moveValue = 5;
    table.addCell(createMovementCell(moveValue + penalty, 1));
    addSpaceCells(table, 1);
    int runningValue = Math.max(1, dexValue + 12 + Math.max(-12, 4 * penalty));
    table.addCell(createMovementCell(runningValue, 2));
    addSpaceCells(table, 1);
    int sprintValue = Math.max(1, calculateSprintValue(penalty, dexValue));
    table.addCell(createMovementCell(sprintValue, 2));
  }

  private int calculateSprintValue(int penalty, int dexValue) {
    int sprintValue = dexValue * 3 + 20;
    double factor = 1 + Math.max(0.3 * penalty, -0.9);
    return (int) (sprintValue * factor);
  }
}