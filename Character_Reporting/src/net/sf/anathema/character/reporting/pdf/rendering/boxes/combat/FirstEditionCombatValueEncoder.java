package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.combat.FirstEditionCombatStatsContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;

public class FirstEditionCombatValueEncoder implements IContentEncoder {

  public float encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    FirstEditionCombatStatsContent content = reportContent.createSubContent(FirstEditionCombatStatsContent.class);
    Position upperLeft = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(4, upperLeft, bounds.width, 3);
    encoder.addLabelledValue(graphics, 0, content.getInitiativeLabel(), content.getInitiative());
    encoder.addLabelledValue(graphics, 1, content.getDodgePoolLabel(), content.getDodgePool());
    encoder.addLabelledValue(graphics, 2, content.getKnockdownLabel(), content.getKnockdownThreshold(), content.getKnockdownPool());
    encoder.addLabelledValue(graphics, 3, content.getStunningLabel(), content.getStunningThreshold(), content.getStunningPool(),
      content.getStunningDuration());
    encoder.addComment(graphics, content.getMobilityPenaltyLabel(), 1);
    encoder.addComment(graphics, content.getThresholdPoolLabel(), 2);
    encoder.addComment(graphics, content.getThresholdPoolDuratoinLabel(), 3);
    return encoder.getHeight();
  }
}
