package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;

public class TraitModifyingStats extends AbstractNonCombatStats implements ITraitModifyingStats
{
	Integer DDVMod;
	Integer PDVMod;
	Integer MDDVMod;
	Integer MPDVMod;
	Integer MeleeSpeedMod;
	Integer MeleeAccuracyMod;
	Integer MeleeDamageMod;
	Integer MeleeRateMod;
	Integer RangedSpeedMod;
	Integer RangedAccuracyMod;
	Integer RangedDamageMod;
	Integer RangedRateMod;
	Integer JoinBattleMod;
	Integer JoinDebateMod;
	Integer JoinWarMod;
	
	@Override
	public Integer getDDVPoolMod() {
		return DDVMod;
	}

	@Override
	public Integer getJoinBattleMod() {
		return JoinBattleMod;
	}

	@Override
	public Integer getJoinDebateMod() {
		return JoinDebateMod;
	}

	@Override
	public Integer getJoinWarMod() {
		return JoinWarMod;
	}

	@Override
	public Integer getMDDVPoolMod() {
		return MDDVMod;
	}

	@Override
	public Integer getMPDVPoolMod() {
		return MPDVMod;
	}

	@Override
	public Integer getMeleeAccuracyMod() {
		return MeleeAccuracyMod;
	}

	@Override
	public Integer getMeleeDamageMod() {
		return MeleeDamageMod;
	}

	@Override
	public Integer getMeleeRateMod() {
		return MeleeRateMod;
	}

	@Override
	public Integer getMeleeSpeedMod() {
		return MeleeSpeedMod;
	}

	@Override
	public Integer getPDVPoolMod() {
		return PDVMod;
	}

	@Override
	public Integer getRangedAccuracyMod() {
		return RangedAccuracyMod;
	}

	@Override
	public Integer getRangedDamageMod() {
		return RangedDamageMod;
	}

	@Override
	public Integer getRangedRateMod() {
		return RangedRateMod;
	}

	@Override
	public Integer getRangedSpeedMod() {
		return RangedSpeedMod;
	}
	
	public void setDDVPoolMod(int value) {
		DDVMod = value;
	}

	public void setJoinBattleMod(int value) {
		JoinBattleMod = value;
	}

	public void setJoinDebateMod(int value) {
		JoinDebateMod = value;
	}

	public void setJoinWarMod(int value) {
		JoinWarMod = value;
	}

	public void setMDDVPoolMod(int value) {
		MDDVMod = value;
	}

	public void setMPDVPoolMod(int value) {
		MPDVMod = value;
	}

	public void setMeleeAccuracyMod(int value) {
		MeleeAccuracyMod = value;
	}

	public void setMeleeDamageMod(int value) {
		MeleeDamageMod = value;
	}

	public void setMeleeRateMod(int value) {
		MeleeRateMod = value;
	}

	public void setMeleeSpeedMod(int value) {
		MeleeSpeedMod = value;
	}

	public void setPDVPoolMod(int value) {
		PDVMod = value;
	}

	public void setRangedAccuracyMod(int value) {
		RangedAccuracyMod = value;
	}

	public void setRangedDamageMod(int value) {
		RangedDamageMod = value;
	}

	public void setRangedRateMod(int value) {
		RangedRateMod = value;
	}

	public void setRangedSpeedMod(int value) {
		RangedSpeedMod = value;
	}

	@Override
	public String getId() {
		return getName().getId();
	}
}
