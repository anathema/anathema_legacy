package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.combat.SecondEditionCombatStatsContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;

public class SecondEditionCombatValueEncoder implements IContentEncoder {

  public float encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    SecondEditionCombatStatsContent content = createContent(reportContent);
    Position upperLeft = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(4, upperLeft, bounds.width, 3);
    encoder.addLabelledValue(graphics, 0, content.getJoinLabel(), content.getJoinBattle());
    encoder.addLabelledValue(graphics, 1, content.getDodgeLabel(), content.getDodgeDv());
    encoder.addLabelledValue(graphics, 2, content.getKnockdownLabel(), content.getKnockdownThreshold(), content.getKnockdownPool());
    encoder.addLabelledValue(graphics, 3, content.getStunningLabel(), content.getStunningThreshold(), content.getStunningPool());
    encoder.addComment(graphics, content.getThresholdPoolLabel(), 2);
    encoder.addComment(graphics, content.getThresholdPoolLabel(), 3);
    return encoder.getHeight();
  }
  
  private SecondEditionCombatStatsContent createContent(ReportContent content) {
    return content.createSubContent(SecondEditionCombatStatsContent.class);
  }
}
