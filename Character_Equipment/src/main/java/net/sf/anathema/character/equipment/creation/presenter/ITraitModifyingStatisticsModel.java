package net.sf.anathema.character.equipment.creation.presenter;

public interface ITraitModifyingStatisticsModel extends IEquipmentStatisticsModel
{
	IIntValueModel getDDVModel();
	
	IIntValueModel getPDVModel();
	
	IIntValueModel getMDDVModel();
	
	IIntValueModel getMPDVModel();
	
	IIntValueModel getMeleeWeaponSpeedModel();
	
	IIntValueModel getMeleeWeaponAccuracyModel();
	
	IIntValueModel getMeleeWeaponDamageModel();
	
	IIntValueModel getMeleeWeaponRateModel();
	
	IIntValueModel getRangedWeaponSpeedModel();
	
	IIntValueModel getRangedWeaponAccuracyModel();
	
	IIntValueModel getRangedWeaponDamageModel();
	
	IIntValueModel getRangedWeaponRateModel();
	
	IIntValueModel getJoinBattleModel();
	
	IIntValueModel getJoinDebateModel();
	
	IIntValueModel getJoinWarModel();
}
