package net.sf.anathema.character.equipment.impl.item.model;

import java.awt.Component;

import net.disy.commons.swing.dialog.core.IDialogResult;
import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IShieldStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.EquipmentTypeChoicePresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArtifactStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.MeleeWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.RangedWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ShieldStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.TraitModifyingStats;
import net.sf.anathema.character.equipment.impl.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class EquipmentStatsCreationFactory implements IEquipmentStatsCreationFactory {

  private final ICollectionFactory collectionFactory;

  public EquipmentStatsCreationFactory(ICollectionFactory collectionFactory) {
    this.collectionFactory = collectionFactory;
  }

  public IEquipmentStats createNewStats(
      Component parentComponent,
      IResources resources,
      IEquipmentTemplateEditModel editModel,
      String[] definedNames,
      IExaltedRuleSet ruleset) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(definedNames, ruleset);
    return runDialog(parentComponent, resources, editModel, model);
  }

  public IEquipmentStats editStats(
      Component parentComponent,
      IResources resources,
      IEquipmentTemplateEditModel editModel,
      String[] definedNames,
      IEquipmentStats stats,
      IExaltedRuleSet ruleset) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(definedNames, ruleset);
    createModel(model, stats);
    return runDialog(parentComponent, resources, editModel, model);
  }

  private IEquipmentStats runDialog(
      Component parentComponent,
      IResources resources,
      IEquipmentTemplateEditModel editModel,
      IEquipmentStatisticsCreationModel model) {
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    EquipmentTypeChoicePresenterPage startPage = new EquipmentTypeChoicePresenterPage(resources, model, editModel, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    IDialogResult result = dialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return createStats(model);
  }

  private void createModel(IEquipmentStatisticsCreationModel model, IEquipmentStats stats) {
    if (stats.getApplicableMaterials() == null)
    	for (MagicalMaterial material : MagicalMaterial.values())
    		model.getApplicableMaterialsModel().getSelectedModel(material).setValue(true);
    else
    {
    	for (MagicalMaterial material : MagicalMaterial.values())
    		model.getApplicableMaterialsModel().getSelectedModel(material).setValue(false);
	    for (Object matObj : stats.getApplicableMaterials())
	    	model.getApplicableMaterialsModel().getSelectedModel((MagicalMaterial)matObj).setValue(true);
    }
	if (stats instanceof IWeaponStats) {
      IWeaponStats weaponStats = (IWeaponStats) stats;
      fillWeaponTagsModel(model.getWeaponTagsModel(), weaponStats);
      if (!weaponStats.isRangedCombat()) {
        model.setEquipmentType(EquipmentStatisticsType.CloseCombat);
        fillOffensiveModel(model.getCloseCombatStatsticsModel(), weaponStats);
        model.getCloseCombatStatsticsModel().getDefenseModel().setValue(weaponStats.getDefence());
      }
      else {
        model.setEquipmentType(EquipmentStatisticsType.RangedCombat);
        fillOffensiveModel(model.getRangedWeaponStatisticsModel(), weaponStats);
        model.getRangedWeaponStatisticsModel().getRangeModel().setValue(weaponStats.getRange());
      }
    }
    else if (stats instanceof IArmourStats) {
      IArmourStats armourStats = (IArmourStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.Armor);
      IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
      armourModel.getName().setText(armourStats.getName().getId());
      armourModel.getBashingHardnessModel().setValue(armourStats.getHardness(HealthType.Bashing));
      armourModel.getBashingSoakModel().setValue(armourStats.getSoak(HealthType.Bashing));
      armourModel.getLethalHardnessModel().setValue(armourStats.getHardness(HealthType.Lethal));
      armourModel.getLethalSoakModel().setValue(armourStats.getSoak(HealthType.Lethal));
      armourModel.getAggravatedSoakModel().setValue(armourStats.getSoak(HealthType.Aggravated));
      armourModel.getFatigueModel().setValue(armourStats.getFatigue());
      armourModel.getMobilityPenaltyModel().setValue(armourStats.getMobilityPenalty());
    }
    else if (stats instanceof IShieldStats) {
      IShieldStats shieldStats = (IShieldStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.Shield);
      IShieldStatisticsModel shieldModel = model.getShieldStatisticsModel();
      shieldModel.getName().setText(shieldStats.getName().getId());
      shieldModel.getCloseCombatDvBonusModel().setValue(shieldStats.getCloseCombatBonus());
      shieldModel.getFatigueModel().setValue(shieldStats.getFatigue());
      shieldModel.getMobilityPenaltyModel().setValue(shieldStats.getMobilityPenalty());
      shieldModel.getRangedCombatDvBonusModel().setValue(shieldStats.getRangedCombatBonus());
    }
    else if (stats instanceof IArtifactStats) {
      IArtifactStats artifactStats = (IArtifactStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.Artifact);
      IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
      artifactModel.getName().setText(artifactStats.getName().getId());
      artifactModel.getAttuneCostModel().setValue(artifactStats.getAttuneCost());
      artifactModel.getForeignAttunementModel().setValue(artifactStats.allowForeignAttunement());
      artifactModel.getRequireAttunementModel().setValue(artifactStats.requireAttunementToUse());
    }
    else if (stats instanceof ITraitModifyingStats) {
        ITraitModifyingStats modifierStats = (ITraitModifyingStats) stats;
        model.setEquipmentType(EquipmentStatisticsType.TraitModifying);
        ITraitModifyingStatisticsModel modifierModel = model.getTraitModifyingStatisticsModel();
        modifierModel.getName().setText(modifierStats.getName().getId());
        modifierModel.getDDVModel().setValue(modifierStats.getDDVMod());
        modifierModel.getPDVModel().setValue(modifierStats.getPDVMod());
        modifierModel.getMDDVModel().setValue(modifierStats.getMDDVMod());
        modifierModel.getMPDVModel().setValue(modifierStats.getMPDVMod());
        modifierModel.getMeleeWeaponSpeedModel().setValue(modifierStats.getMeleeSpeedMod());
        modifierModel.getMeleeWeaponAccuracyModel().setValue(modifierStats.getMeleeAccuracyMod());
        modifierModel.getMeleeWeaponDamageModel().setValue(modifierStats.getMeleeDamageMod());
        modifierModel.getMeleeWeaponRateModel().setValue(modifierStats.getMeleeRateMod());
        modifierModel.getRangedWeaponSpeedModel().setValue(modifierStats.getRangedSpeedMod());
        modifierModel.getRangedWeaponAccuracyModel().setValue(modifierStats.getRangedAccuracyMod());
        modifierModel.getRangedWeaponDamageModel().setValue(modifierStats.getRangedDamageMod());
        modifierModel.getRangedWeaponRateModel().setValue(modifierStats.getRangedRateMod());
        modifierModel.getJoinBattleModel().setValue(modifierStats.getJoinBattleMod());
        modifierModel.getJoinDebateModel().setValue(modifierStats.getJoinDebateMod());
        modifierModel.getJoinWarModel().setValue(modifierStats.getJoinWarMod());
      }
    else {
      throw new NotYetImplementedException();
    }
  }

  private void fillWeaponTagsModel(IWeaponTagsModel weaponTagsModel, IWeaponStats weaponStats) {
    for (IIdentificate tag : weaponStats.getTags()) {
      weaponTagsModel.getSelectedModel((WeaponTag) tag).setValue(true);
    }
  }

  private void fillOffensiveModel(IOffensiveStatisticsModel offensiveModel, IWeaponStats weaponStats) {
    offensiveModel.getAccuracyModel().setValue(weaponStats.getAccuracy());
    offensiveModel.getName().setText(weaponStats.getName().getId());
    if (offensiveModel.supportsRate()) {
      offensiveModel.getRateModel().setValue(weaponStats.getRate());
    }
    offensiveModel.getSpeedModel().setValue(weaponStats.getSpeed());
    offensiveModel.getWeaponDamageModel().setValue(weaponStats.getDamage());
    offensiveModel.getWeaponDamageModel().setHealthType(weaponStats.getDamageType());
  }

  private IEquipmentStats createStats(IEquipmentStatisticsCreationModel model) {
    switch (model.getEquipmentType()) {
      case Armor:
        ArmourStats armourStats = new ArmourStats(collectionFactory);
        applyCommon(armourStats, model);
        IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
        setName(armourStats, armourModel);
        armourStats.setFatigue(armourModel.getFatigueModel().getValue());
        armourStats.setMobilityPenalty(armourModel.getMobilityPenaltyModel().getValue());
        for (HealthType healthType : HealthType.values()) {
          armourStats.setSoak(healthType, armourModel.getSoakModel(healthType).getValue());
          armourStats.setHardness(healthType, armourModel.getHardnessModel(healthType).getValue());
        }
        return armourStats;
      case Shield:
        ShieldStats shieldStats = new ShieldStats();
        applyCommon(shieldStats, model);
        IShieldStatisticsModel shieldModel = model.getShieldStatisticsModel();
        setName(shieldStats, shieldModel);
        shieldStats.setCloseCombatDv(shieldModel.getCloseCombatDvBonusModel().getValue());
        shieldStats.setRangedCombatDv(shieldModel.getRangedCombatDvBonusModel().getValue());
        shieldStats.setFatigue(shieldModel.getFatigueModel().getValue());
        shieldStats.setMobilityPenalty(shieldModel.getMobilityPenaltyModel().getValue());
        return shieldStats;
      case CloseCombat:
        AbstractWeaponStats closeCombatStats = new MeleeWeaponStats(collectionFactory);
        applyCommon(closeCombatStats, model);
        ICloseCombatStatsticsModel closeCombatModel = model.getCloseCombatStatsticsModel();
        setBasicWeaponStats(closeCombatStats, closeCombatModel, model.getWeaponTagsModel());
        closeCombatStats.setDefence(closeCombatModel.getDefenseModel().getValue());
        return closeCombatStats;
      case RangedCombat:
        AbstractWeaponStats rangedCombatStats = new RangedWeaponStats(collectionFactory);
        applyCommon(rangedCombatStats, model);
        IRangedCombatStatisticsModel rangedCombatModel = model.getRangedWeaponStatisticsModel();
        setBasicWeaponStats(rangedCombatStats, rangedCombatModel, model.getWeaponTagsModel());
        rangedCombatStats.setRange(rangedCombatModel.getRangeModel().getValue());
        return rangedCombatStats;
      case Artifact:
    	ArtifactStats artifactStats = new ArtifactStats();
    	applyCommon(artifactStats, model);
    	IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
    	setName(artifactStats, artifactModel);
    	artifactStats.setAttuneCost(artifactModel.getAttuneCostModel().getValue());
    	artifactStats.setAllowForeignAttunement(artifactModel.getForeignAttunementModel().getValue());
    	artifactStats.setRequireAttunement(artifactModel.getRequireAttunementModel().getValue());
    	return artifactStats;
      case TraitModifying:
    	TraitModifyingStats modifierStats = new TraitModifyingStats();
    	applyCommon(modifierStats, model);
    	ITraitModifyingStatisticsModel modifierModel = model.getTraitModifyingStatisticsModel();
    	setName(modifierStats, modifierModel);
    	modifierStats.setDDVMod(modifierModel.getDDVModel().getValue());
    	modifierStats.setPDVMod(modifierModel.getPDVModel().getValue());
    	modifierStats.setMDDVMod(modifierModel.getMDDVModel().getValue());
    	modifierStats.setMPDVMod(modifierModel.getMPDVModel().getValue());
    	modifierStats.setMeleeSpeedMod(modifierModel.getMeleeWeaponSpeedModel().getValue());
    	modifierStats.setMeleeAccuracyMod(modifierModel.getMeleeWeaponAccuracyModel().getValue());
    	modifierStats.setMeleeDamageMod(modifierModel.getMeleeWeaponDamageModel().getValue());
    	modifierStats.setMeleeRateMod(modifierModel.getMeleeWeaponRateModel().getValue());
    	modifierStats.setRangedSpeedMod(modifierModel.getRangedWeaponSpeedModel().getValue());
    	modifierStats.setRangedAccuracyMod(modifierModel.getRangedWeaponAccuracyModel().getValue());
    	modifierStats.setRangedDamageMod(modifierModel.getRangedWeaponDamageModel().getValue());
    	modifierStats.setRangedRateMod(modifierModel.getRangedWeaponRateModel().getValue());
    	modifierStats.setJoinBattleMod(modifierModel.getJoinBattleModel().getValue());
    	modifierStats.setJoinDebateMod(modifierModel.getJoinDebateModel().getValue());
    	modifierStats.setJoinWarMod(modifierModel.getJoinWarModel().getValue());
    	return modifierStats;
    }
    return null;
  }
  
  private void applyCommon(IEquipmentStats stats, IEquipmentStatisticsCreationModel model)
  {
	stats.setApplicableMaterials(model.getApplicableMaterialsModel().getValidMaterials());  
  }

  private void setBasicWeaponStats(
      AbstractWeaponStats stats,
      IOffensiveStatisticsModel model,
      IWeaponTagsModel tagsModel) {
    setName(stats, model);
    stats.setAccuracy(model.getAccuracyModel().getValue());
    stats.setDamage(model.getWeaponDamageModel().getValue());
    stats.setDamageType(model.getWeaponDamageModel().getHealthType());
    stats.setRate(model.supportsRate() ? model.getRateModel().getValue() : null);
    stats.setSpeed(model.getSpeedModel().getValue());
    for (IWeaponTag tag : tagsModel.getSelectedTags()) {
      stats.addTag(tag);
    }
  }

  private void setName(AbstractStats stats, IEquipmentStatisticsModel model) {
    String name = model.getName().getText();
    if (name != null) {
      stats.setName(new Identificate(name));
    }
  }
}