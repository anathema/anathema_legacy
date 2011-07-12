package net.sf.anathema.character.impl.generic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IDefensiveStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.model.ICharacterStatistics;

public class EquipmentModifiers implements IEquipmentModifiers
{
	private final List<ITraitModifyingStats> stats = new ArrayList<ITraitModifyingStats>();
	private int mobilityPenalty;
	
	public EquipmentModifiers(ICharacterStatistics statistics)
	{
		IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel)
    		statistics.getCharacterContext().getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
		
		for (IEquipmentItem item : equipmentModel.getEquipmentItems())
			for (IEquipmentStats equipmentStats : item.getStats())
			{
				if (equipmentStats instanceof ITraitModifyingStats &&
					item.isPrintEnabled(equipmentStats))
					stats.add((ITraitModifyingStats) equipmentStats);
				if (equipmentStats instanceof IDefensiveStats &&
					item.isPrintEnabled(equipmentStats))
					mobilityPenalty += ((IDefensiveStats)equipmentStats).getMobilityPenalty();
			}
	}
	
	public int getMobilityPenalty()
	{
		return mobilityPenalty;
	}
	
	@Override
	public int getDDVMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getDDVMod();
		return total;
	}

	@Override
	public int getJoinBattleMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getJoinBattleMod();
		return total;
	}

	@Override
	public int getJoinDebateMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getJoinDebateMod();
		return total;
	}

	@Override
	public int getJoinWarMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getJoinWarMod();
		return total;
	}

	@Override
	public int getMDDVMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getMDDVMod();
		return total;
	}

	@Override
	public int getMPDVMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getMPDVMod();
		return total;
	}

	@Override
	public int getMeleeAccuracyMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getMeleeAccuracyMod();
		return total;
	}

	@Override
	public int getMeleeDamageMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getMeleeDamageMod();
		return total;
	}

	@Override
	public int getMeleeRateMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getMeleeRateMod();
		return total;
	}

	@Override
	public int getMeleeSpeedMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getMeleeSpeedMod();
		return total;
	}

	@Override
	public int getPDVMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getPDVMod();
		return total;
	}

	@Override
	public int getRangedAccuracyMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getRangedAccuracyMod();
		return total;
	}

	@Override
	public int getRangedDamageMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getRangedDamageMod();
		return total;
	}

	@Override
	public int getRangedRateMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getRangedRateMod();
		return total;
	}

	@Override
	public int getRangedSpeedMod() {
		int total = 0;
		for (ITraitModifyingStats stat : stats)
			total += stat.getMeleeSpeedMod();
		return total;
	}
}
