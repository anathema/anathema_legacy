package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionHealthAndMovementTableEncoder extends AbstractHealthAndMovementTableEncoder {

  public SecondEditionHealthAndMovementTableEncoder(IResources resources, BaseFont baseFont) {
    super(resources, baseFont);
  }

  @Override
  protected final Float[] getMovementColumns() {
    return new Float[]{1f, PADDING, 1f, PADDING, 1f, 1f};
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

  @Override
  protected final void addMovementCells(PdfPTable table, HealthLevelType level, int painTolerance, IGenericTraitCollection collection) {
    int penalty = getPenalty(level, painTolerance);
    int dexValue = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    int moveValue = dexValue + penalty;
    table.addCell(createMovementCell(moveValue, 1));
    addSpaceCells(table, 1);
    table.addCell(createMovementCell(moveValue + 6, 2));
    int verticalJump = collection.getTrait(AttributeType.Strength).getCurrentValue() + collection.getTrait(AbilityType.Athletics).getCurrentValue()
                       + penalty;
    addSpaceCells(table, 1);
    table.addCell(createMovementCell(verticalJump * 2, 0));
    table.addCell(createMovementCell(verticalJump, 0));
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
