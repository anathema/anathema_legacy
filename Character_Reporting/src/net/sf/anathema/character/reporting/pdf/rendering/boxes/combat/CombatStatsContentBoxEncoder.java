package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class CombatStatsContentBoxEncoder implements IBoxContentEncoder {

  private final static float PADDING = 3;

  private final ITableEncoder combatRulesEncoder;
  private final IContentEncoder combatValueEncoder;

  public CombatStatsContentBoxEncoder(ITableEncoder combatRulesEncoder, IContentEncoder combatValueEncoder) {
    this.combatRulesEncoder = combatRulesEncoder;
    this.combatValueEncoder = combatValueEncoder;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float height = combatValueEncoder.encode(graphics, reportContent, bounds);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - height - PADDING);
    encodeRules(graphics, reportContent, ruleBounds);
  }

  private void encodeRules(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    combatRulesEncoder.encodeTable(graphics, content, bounds);
  }

  public String getHeaderKey(ReportContent content) {
    return "Combat"; //$NON-NLS-1$
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
