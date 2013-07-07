package net.sf.anathema.character.reporting.pdf.content.combat.encoder;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.IContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.table.ITableEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class CombatStatsContentBoxEncoder implements ContentEncoder {

  private final static float PADDING = 3;

  private Resources resources;
  private final ITableEncoder combatRulesEncoder;
  private final IContentEncoder combatValueEncoder;

  public CombatStatsContentBoxEncoder(Resources resources, ITableEncoder combatRulesEncoder, IContentEncoder combatValueEncoder) {
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
