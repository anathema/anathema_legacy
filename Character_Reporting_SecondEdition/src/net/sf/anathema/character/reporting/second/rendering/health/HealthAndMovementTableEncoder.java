package net.sf.anathema.character.reporting.second.rendering.health;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.AbstractHealthAndMovementTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class HealthAndMovementTableEncoder extends AbstractHealthAndMovementTableEncoder {

  public HealthAndMovementTableEncoder(IResources resources) {
    super(resources);
  }

  @Override
  protected final Float[] getMovementColumns() {
    return new Float[] { 1f, PADDING, 1f, PADDING, 1f, 1f };
  }

  @Override
  protected final Phrase createIncapacitatedComment(SheetGraphics graphics) {
    return new Phrase(getResources().getString("Sheet.Movement.Comment.Mobility"), createCommentFont(graphics)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementHeader(SheetGraphics graphics, PdfPTable table) {
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Move"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Dash"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Jump"), 3)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance,
    IGenericTraitCollection collection) {
    int penalty = getPenalty(level, painTolerance);
    int dexValue = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    int moveValue = dexValue + penalty;
    table.addCell(createMovementCell(graphics, moveValue, 1));
    addSpaceCells(graphics, table, 1);
    table.addCell(createMovementCell(graphics, moveValue + 6, 2));
    int verticalJump =
      collection.getTrait(AttributeType.Strength).getCurrentValue() + collection.getTrait(AbilityType.Athletics).getCurrentValue() + penalty;
    addSpaceCells(graphics, table, 1);
    table.addCell(createMovementCell(graphics, verticalJump * 2, 0));
    table.addCell(createMovementCell(graphics, verticalJump, 0));
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
