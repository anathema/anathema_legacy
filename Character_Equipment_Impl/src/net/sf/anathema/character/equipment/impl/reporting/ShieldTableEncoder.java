package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.stats.armour.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.armour.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.shields.CloseCombatShieldStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.shields.RangedCombatShieldStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class ShieldTableEncoder extends AbstractEquipmentTableEncoder<IShieldStats> {

  private final IResources resources;

  public ShieldTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) {
    IEquipmentStatsGroup<IShieldStats>[] groups = createStatsGroups(content);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable shieldTable = new PdfPTable(columnWidths);
    shieldTable.setTotalWidth(bounds.width);
    encodeContent(shieldTable, content, bounds);
    return shieldTable;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IShieldStats>[] createStatsGroups(ReportContent content) {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IArmourStats>(resources),
        new CloseCombatShieldStatsGroup(resources),
        new RangedCombatShieldStatsGroup(resources),
        new MobilityPenaltyStatsGroup(resources),
        new FatigueStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 1;
  }

  @Override
  protected IShieldStats[] getPrintStats(ReportContent content) {
    return getEquipmentModel(content.getCharacter()).getPrintShield();
  }
}
