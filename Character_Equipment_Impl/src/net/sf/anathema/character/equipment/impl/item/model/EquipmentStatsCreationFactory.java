package net.sf.anathema.character.equipment.impl.item.model;

import java.awt.Component;

import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IShieldStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.EquipmentTypeChoicePresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.MeleeWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.RangedWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ShieldStats;
import net.sf.anathema.character.equipment.impl.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
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
      String[] definedNames,
      IExaltedRuleSet ruleset) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(definedNames, ruleset);
    return runDialog(parentComponent, resources, model);
  }

  public IEquipmentStats editStats(
      Component parentComponent,
      IResources resources,
      String[] definedNames,
      IEquipmentStats stats,
      IExaltedRuleSet ruleset) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(definedNames, ruleset);
    createModel(model, stats);
    return runDialog(parentComponent, resources, model);
  }

  private IEquipmentStats runDialog(
      Component parentComponent,
      IResources resources,
      IEquipmentStatisticsCreationModel model) {
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    EquipmentTypeChoicePresenterPage startPage = new EquipmentTypeChoicePresenterPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    dialog.show();
    if (dialog.isCanceled()) {
      return null;
    }
    return createStats(model);
  }

  private void createModel(IEquipmentStatisticsCreationModel model, IEquipmentStats stats) {
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
        IShieldStatisticsModel shieldModel = model.getShieldStatisticsModel();
        setName(shieldStats, shieldModel);
        shieldStats.setCloseCombatDv(shieldModel.getCloseCombatDvBonusModel().getValue());
        shieldStats.setRangedCombatDv(shieldModel.getRangedCombatDvBonusModel().getValue());
        shieldStats.setFatigue(shieldModel.getFatigueModel().getValue());
        shieldStats.setMobilityPenalty(shieldModel.getMobilityPenaltyModel().getValue());
        return shieldStats;
      case CloseCombat:
        AbstractWeaponStats closeCombatStats = new MeleeWeaponStats(collectionFactory);
        ICloseCombatStatsticsModel closeCombatModel = model.getCloseCombatStatsticsModel();
        setBasicWeaponStats(closeCombatStats, closeCombatModel, model.getWeaponTagsModel());
        closeCombatStats.setDefence(closeCombatModel.getDefenseModel().getValue());
        return closeCombatStats;
      case RangedCombat:
        AbstractWeaponStats rangedCombatStats = new RangedWeaponStats(collectionFactory);
        IRangedCombatStatisticsModel rangedCombatModel = model.getRangedWeaponStatisticsModel();
        setBasicWeaponStats(rangedCombatStats, rangedCombatModel, model.getWeaponTagsModel());
        rangedCombatStats.setRange(rangedCombatModel.getRangeModel().getValue());
        return rangedCombatStats;
    }
    return null;
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