package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.HardnessStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.IArmourStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.SoakArmourStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class SecondEditionArmourTableEncoder extends AbstractEquipmentTableEncoder<IArmour> {

  private final IResources resources;

  public SecondEditionArmourTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(IGenericCharacter character) {
    PdfPTable armourTable = super.createTable(character);
    IArmour totalArmour = getEquipmentModel(character).getTotalPrintArmour(getLineCount());
    IEquipmentStatsGroup<IArmour>[] groups = createStatsGroups(character);
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        armourTable.addCell(createSpaceCell());
      }
      IEquipmentStatsGroup<IArmour> group = groups[index];
      if (group instanceof IArmourStatsGroup) {
        ((IArmourStatsGroup) group).addTotal(armourTable, getFont(), totalArmour);
      }
      else {
        group.addContent(armourTable, getFont(), totalArmour);
      }
    }
    return armourTable;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IArmour>[] createStatsGroups(IGenericCharacter character) {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IArmour>(resources),
        new SoakArmourStatsGroup(resources),
        new HardnessStatsGroup(resources),
        new MobilityPenaltyStatsGroup(resources),
        new FatigueStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 3;
  }

  @Override
  protected IArmour[] getPrintStats(IGenericCharacter character) {
    return getEquipmentModel(character).getPrintArmours();
  }

  @Override
  protected IGenericTrait getTrait(IGenericCharacter character, IArmour equipment) {
    return null;
  }
}