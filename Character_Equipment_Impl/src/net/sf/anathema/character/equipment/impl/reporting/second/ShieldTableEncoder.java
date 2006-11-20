package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.shieldstats.CloseCombatShieldStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.shieldstats.RangedCombatShieldStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;

public class ShieldTableEncoder extends AbstractEquipmentTableEncoder<IShieldStats> {

  private final IResources resources;

  public ShieldTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    IEquipmentStatsGroup<IShieldStats>[] groups = createStatsGroups(character);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable shieldTable = new PdfPTable(columnWidths);
    shieldTable.setTotalWidth(bounds.width);
    encodeContent(shieldTable, character, bounds);
    return shieldTable;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IShieldStats>[] createStatsGroups(IGenericCharacter character) {
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
  protected IShieldStats[] getPrintStats(IGenericCharacter character) {
    IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) character.getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
    return model.getPrintShield();
  }
}