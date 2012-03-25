package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class CombatStatsContentBoxEncoder implements ContentEncoder {

  private final static float PADDING = 3;

  private IResources resources;
  private final ITableEncoder combatRulesEncoder;
  private final IContentEncoder combatValueEncoder;

  public CombatStatsContentBoxEncoder(IResources resources, ITableEncoder combatRulesEncoder, IContentEncoder combatValueEncoder) {
    this.resources = resources;
    this.combatRulesEncoder = combatRulesEncoder;
    this.combatValueEncoder = combatValueEncoder;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    float height = combatValueEncoder.encode(graphics, reportSession, bounds);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - height - PADDING);
    encodeRules(graphics, reportSession, ruleBounds);
  }

  private void encodeRules(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    combatRulesEncoder.encodeTable(graphics, session, bounds);
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Combat");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
