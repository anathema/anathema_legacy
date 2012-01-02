package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionHealthAndMovementTableEncoder extends AbstractHealthAndMovementTableEncoder {

  public FirstEditionHealthAndMovementTableEncoder(IResources resources) {
    super(resources);
  }

  @Override
  protected final Float[] getMovementColumns() {
    return new Float[] { 1f, PADDING, 1f, PADDING, 1f };
  }

  @Override
  protected final Phrase createIncapacitatedComment(SheetGraphics graphics) {
    return new Phrase(getResources().getString("Sheet.FirstEdition.Movement.Comment.Mobility"), createCommentFont(graphics)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementHeader(SheetGraphics graphics, PdfPTable table) {
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Move"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Run"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Sprint"), 2)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance,
    IGenericTraitCollection collection) {
    int penalty = getPenalty(level, painTolerance);
    int dexValue = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    int moveValue = 5;
    table.addCell(createMovementCell(graphics, moveValue + penalty, 1));
    addSpaceCells(graphics, table, 1);
    int runningValue = Math.max(1, dexValue + 12 + Math.max(-12, 4 * penalty));
    table.addCell(createMovementCell(graphics, runningValue, 2));
    addSpaceCells(graphics, table, 1);
    int sprintValue = Math.max(1, calculateSprintValue(penalty, dexValue));
    table.addCell(createMovementCell(graphics, sprintValue, 2));
  }

  private int calculateSprintValue(int penalty, int dexValue) {
    int sprintValue = dexValue * 3 + 20;
    double factor = 1 + Math.max(0.3 * penalty, -0.9);
    return (int) (sprintValue * factor);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
