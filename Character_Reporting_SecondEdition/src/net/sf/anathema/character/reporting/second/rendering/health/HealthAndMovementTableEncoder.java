package net.sf.anathema.character.reporting.second.rendering.health;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.StatsModifierFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.AbstractHealthAndMovementTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class HealthAndMovementTableEncoder extends AbstractHealthAndMovementTableEncoder {
  
  private int mobilityPenalty;
  
  public HealthAndMovementTableEncoder(Resources resources) {
    super(resources);
  }
  
  @Override
  public final float encodeTable(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    mobilityPenalty = Math.min( 0, StatsModifierFactory.create(session.getCharacter()).getMobilityPenalty() );
    return super.encodeTable(graphics, session, bounds);
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
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Move"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Dash"), 2)); //$NON-NLS-1$
    table.addCell(createHeaderCell(graphics, getResources().getString("Sheet.Movement.Jump"), 3)); //$NON-NLS-1$
  }

  @Override
  protected final void addMovementCells(SheetGraphics graphics, PdfPTable table, HealthLevelType level, int painTolerance, IGenericTraitCollection collection) {

    int woundPenalty    = getPenalty(level, painTolerance);
    int dex             = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    int str             = collection.getTrait(AttributeType.Strength).getCurrentValue();
    int athletics       = collection.getTrait(AbilityType.Athletics).getCurrentValue();
    
    // minimum move is 1, minimum dash is 2, minimum jump h/v, swim, & climb is unknown
    int move            = Math.max( dex + woundPenalty + mobilityPenalty    , 1 );
    int dash            = Math.max( dex + woundPenalty + mobilityPenalty + 6, 2 );
    int verticalJump    = str + athletics + woundPenalty + mobilityPenalty;
    int horizontalJump  = verticalJump * 2;

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
