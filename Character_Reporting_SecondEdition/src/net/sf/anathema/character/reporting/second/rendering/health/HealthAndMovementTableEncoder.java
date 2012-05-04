package net.sf.anathema.character.reporting.second.rendering.health;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.AbstractHealthAndMovementTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.equipment.impl.character.model.stats.CharacterStatsModifiers;

import net.sf.anathema.lib.resources.IResources;

public class HealthAndMovementTableEncoder extends AbstractHealthAndMovementTableEncoder {
  
  private int mobilityPenalty;
  
  public HealthAndMovementTableEncoder(IResources resources) {
    super(resources);
  }
  
  @Override
  public final float encodeTable(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    mobilityPenalty = Math.min( 0, CharacterStatsModifiers.extractFromCharacter(session.getCharacter()).getMobilityPenalty() );
    return super.encodeTable(graphics, session, bounds);
  }

  @Override
  protected final int getMobilityPenalty() {
    return mobilityPenalty;
  }

  @Override
  protected final Float[] getMovementColumns() {
    return new Float[]{1f, PADDING, 1f, PADDING, 1f, 1f};
  }

  @Override
  protected final Phrase createIncapacitatedComment(SheetGraphics graphics) {
    return new Phrase(getResources().getString("Sheet.Movement.Comment.Mobility"), createCommentFont(graphics)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementHeader(SheetGraphics graphics, PdfPTable table) {
    //table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Health.Levels"), 3)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Move"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Dash"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Jump"), 3)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance, IGenericTraitCollection collection) {
    //addHealthPenaltyCells(graphics, table, level, painTolerance);
    //addSpaceCells(graphics, table, 1);

    int woundPenalty    = getPenalty(level, painTolerance);
    int mobilityPenalty = getMobilityPenalty();
    int dex             = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    int str             = collection.getTrait(AttributeType.Strength).getCurrentValue();
    int athletics       = collection.getTrait(AbilityType.Athletics).getCurrentValue();
    
    int move            = Math.max( dex + woundPenalty + mobilityPenalty    , 1 ); // minimum movement rate is 1
    int dash            = Math.max( dex + woundPenalty + mobilityPenalty + 6, 2 ); // minimum dash rate is 2
    int verticalJump    = str + athletics + woundPenalty + mobilityPenalty;
    int horizontalJump  = verticalJump * 2;
    int swimClimb       = move / 2;  // not used yet, future addition to the sheet

    table.addCell(createMovementCell(graphics, move, 1));
    addSpaceCells(graphics, table, 1);
    table.addCell(createMovementCell(graphics, dash, 2));

    addSpaceCells(graphics, table, 1);
    table.addCell(createMovementCell(graphics, horizontalJump, 0));
    table.addCell(createMovementCell(graphics, verticalJump, 0));
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
