package net.sf.anathema.character.reporting.second.rendering.combat;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.second.content.combat.CombatStatsContent;

public class CombatValueEncoder implements IContentEncoder {

  public float encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    CombatStatsContent content = createContent(reportContent);
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

  private CombatStatsContent createContent(ReportContent content) {
    return content.createSubContent(CombatStatsContent.class);
  }
}
