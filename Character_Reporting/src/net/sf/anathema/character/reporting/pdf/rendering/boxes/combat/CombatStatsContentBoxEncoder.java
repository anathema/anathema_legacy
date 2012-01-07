package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
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
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float height = combatValueEncoder.encode(graphics, reportContent, bounds);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - height - PADDING);
    encodeRules(graphics, reportContent, ruleBounds);
  }

  private void encodeRules(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    combatRulesEncoder.encodeTable(graphics, content, bounds);
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Combat");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
