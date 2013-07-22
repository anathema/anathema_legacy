package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.ITraitModifyingStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;

public class StatsToModel {

  public IEquipmentStatisticsCreationModel createModel(IEquipmentStats stats) {
    EquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel();
    if (stats instanceof IWeaponStats) {
      IWeaponStats weaponStats = (IWeaponStats) stats;
      fillWeaponTagsModel(model.getWeaponTagsModel(), weaponStats);
      if (!weaponStats.isRangedCombat()) {
        model.setEquipmentType(EquipmentStatisticsType.CloseCombat);
        fillOffensiveModel(model.getCloseCombatStatsticsModel(), weaponStats);
        model.getCloseCombatStatsticsModel().getDefenseModel().setValue(weaponStats.getDefence());
      } else {
        model.setEquipmentType(EquipmentStatisticsType.RangedCombat);
        fillOffensiveModel(model.getRangedWeaponStatisticsModel(), weaponStats);
        model.getRangedWeaponStatisticsModel().getRangeModel().setValue(weaponStats.getRange());
      }
    } else if (stats instanceof IArmourStats) {
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
    } else if (stats instanceof ArtifactStats) {
      ArtifactStats artifactStats = (ArtifactStats) stats;
      model.setEquipmentType(Artifact);
      IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
      artifactModel.getName().setText(artifactStats.getName().getId());
      artifactModel.getAttuneCostModel().setValue(artifactStats.getAttuneCost());
      artifactModel.getForeignAttunementModel().setValue(artifactStats.allowForeignAttunement());
      artifactModel.getRequireAttunementModel().setValue(artifactStats.requireAttunementToUse());
    } else if (stats instanceof ITraitModifyingStats) {
      ITraitModifyingStats modifierStats = (ITraitModifyingStats) stats;
      model.setEquipmentType(EquipmentStatisticsType.TraitModifying);
      ITraitModifyingStatisticsModel modifierModel = model.getTraitModifyingStatisticsModel();
      modifierModel.getName().setText(modifierStats.getName().getId());
      modifierModel.getDDVModel().setValue(modifierStats.getDDVPoolMod());
      modifierModel.getPDVModel().setValue(modifierStats.getPDVPoolMod());
      modifierModel.getMDDVModel().setValue(modifierStats.getMDDVPoolMod());
      modifierModel.getMPDVModel().setValue(modifierStats.getMPDVPoolMod());
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
    return model;
  }

  private void fillWeaponTagsModel(IWeaponTagsModel weaponTagsModel, IWeaponStats weaponStats) {
    for (Identifier tag : weaponStats.getTags()) {
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
    offensiveModel.getWeaponDamageModel().getDamageModel().setValue(weaponStats.getDamage());
    offensiveModel.getWeaponDamageModel().getMinDamageModel().setValue(weaponStats.getMinimumDamage());
    offensiveModel.getWeaponDamageModel().setHealthType(weaponStats.getDamageType());
  }
}