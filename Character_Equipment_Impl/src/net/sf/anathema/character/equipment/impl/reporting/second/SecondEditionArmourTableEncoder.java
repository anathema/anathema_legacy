package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.HardnessStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.SoakArmourStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionArmourTableEncoder extends AbstractEquipmentTableEncoder<IArmour> {

  private final IResources resources;

  public SecondEditionArmourTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IArmour>[] createEquipmentGroups() {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IArmour>(resources),
        new SoakArmourStatsGroup(resources),
        new HardnessStatsGroup(resources),
        new MobilityPenaltyStatsGroup(resources),
        new FatigueStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 4;
  }

  @Override
  protected IArmour[] getPrintEquipments(IGenericCharacter character) {
    return getEquipmentModel(character).getPrintArmours();
  }

  @Override
  protected IGenericTrait getTrait(IGenericCharacter character, IArmour equipment) {
    return null;
  }
}