package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArtifactStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.MeleeWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.RangedWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.TraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class ModelToStats {

  public IEquipmentStats createStats(IEquipmentStatisticsCreationModel model) {
    switch (model.getEquipmentType()) {
      case Armor:
        ArmourStats armourStats = new ArmourStats();
        IArmourStatisticsModel armourModel = model.getArmourStatisticsModel();
        setName(armourStats, armourModel);
        armourStats.setFatigue(armourModel.getFatigueModel().getValue());
        armourStats.setMobilityPenalty(armourModel.getMobilityPenaltyModel().getValue());
        for (HealthType healthType : HealthType.values()) {
          armourStats.setSoak(healthType, armourModel.getSoakModel(healthType).getValue());
          armourStats.setHardness(healthType, armourModel.getHardnessModel(healthType).getValue());
        }
        return armourStats;
      case CloseCombat:
        AbstractWeaponStats closeCombatStats = new MeleeWeaponStats();
        ICloseCombatStatsticsModel closeCombatModel = model.getCloseCombatStatsticsModel();
        setBasicWeaponStats(closeCombatStats, closeCombatModel, model.getWeaponTagsModel());
        closeCombatStats.setDefence(closeCombatModel.getDefenseModel().getValue());
        return closeCombatStats;
      case RangedCombat:
        AbstractWeaponStats rangedCombatStats = new RangedWeaponStats();
        IRangedCombatStatisticsModel rangedCombatModel = model.getRangedWeaponStatisticsModel();
        setBasicWeaponStats(rangedCombatStats, rangedCombatModel, model.getWeaponTagsModel());
        rangedCombatStats.setRange(rangedCombatModel.getRangeModel().getValue());
        return rangedCombatStats;
      case Artifact:
        ArtifactStats artifactStats = new ArtifactStats();
        IArtifactStatisticsModel artifactModel = model.getArtifactStatisticsModel();
        setName(artifactStats, artifactModel);
        artifactStats.setAttuneCost(artifactModel.getAttuneCostModel().getValue());
        artifactStats.setAllowForeignAttunement(artifactModel.getForeignAttunementModel().getValue());
        artifactStats.setRequireAttunement(artifactModel.getRequireAttunementModel().getValue());
        return artifactStats;
      case TraitModifying:
        TraitModifyingStats modifierStats = new TraitModifyingStats();
        ITraitModifyingStatisticsModel modifierModel = model.getTraitModifyingStatisticsModel();
        setName(modifierStats, modifierModel);
        modifierStats.setDDVPoolMod(modifierModel.getDDVModel().getValue());
        modifierStats.setPDVPoolMod(modifierModel.getPDVModel().getValue());
        modifierStats.setMDDVPoolMod(modifierModel.getMDDVModel().getValue());
        modifierStats.setMPDVPoolMod(modifierModel.getMPDVModel().getValue());
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

  private void setBasicWeaponStats(AbstractWeaponStats stats, IOffensiveStatisticsModel model, IWeaponTagsModel tagsModel) {
    setName(stats, model);
    stats.setAccuracy(model.getAccuracyModel().getValue());
    stats.setDamage(model.getWeaponDamageModel().getDamageModel().getValue());
    stats.setMinimumDamage(model.getWeaponDamageModel().getMinDamageModel().getValue());
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
      stats.setName(new SimpleIdentifier(name));
    }
  }
}
