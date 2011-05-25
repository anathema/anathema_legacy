package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface ITraitModifyingStatisticsModel extends IEquipmentStatisticsModel
{
	public IIntValueModel getDDVModel();
	
	public IIntValueModel getPDVModel();
	
	public IIntValueModel getMDDVModel();
	
	public IIntValueModel getMPDVModel();
	
	public IIntValueModel getMeleeWeaponSpeedModel();
	
	public IIntValueModel getMeleeWeaponAccuracyModel();
	
	public IIntValueModel getMeleeWeaponDamageModel();
	
	public IIntValueModel getMeleeWeaponRateModel();
	
	public IIntValueModel getRangedWeaponSpeedModel();
	
	public IIntValueModel getRangedWeaponAccuracyModel();
	
	public IIntValueModel getRangedWeaponDamageModel();
	
	public IIntValueModel getRangedWeaponRateModel();
	
	public IIntValueModel getJoinBattleModel();
	
	public IIntValueModel getJoinDebateModel();
	
	public IIntValueModel getJoinWarModel();
}
