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
import net.sf.anathema.character.equipment.impl.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ShieldStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.WeaponStats;
import net.sf.anathema.character.equipment.impl.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.gui.wizard.AnathemaWizardDialog;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class EquipmentStatsCreationFactory implements IEquipmentStatsCreationFactory {

  private final ICollectionFactory collectionFactory;

  public EquipmentStatsCreationFactory(ICollectionFactory collectionFactory) {
    this.collectionFactory = collectionFactory;
  }

  public IEquipmentStats createNewStats(Component parentComponent, IResources resources, String[] definedNames) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(definedNames);
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    EquipmentTypeChoicePresenterPage startPage = new EquipmentTypeChoicePresenterPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(parentComponent, startPage);
    dialog.show();
    if (dialog.isCanceled()) {
      return null;
    }
    return createStats(model);
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
        WeaponStats closeCombatStats = new WeaponStats(collectionFactory, true);
        ICloseCombatStatsticsModel closeCombatModel = model.getCloseCombatStatsticsModel();
        setBasicWeaponStats(closeCombatStats, closeCombatModel, model.getWeaponTagsModel());
        closeCombatStats.setDefence(closeCombatModel.getDefenseModel().getValue());
        return closeCombatStats;
      case RangedCombat:
        WeaponStats rangedCombatStats = new WeaponStats(collectionFactory, false);
        IRangedCombatStatisticsModel rangedCombatModel = model.getRangedWeaponStatisticsModel();
        setBasicWeaponStats(rangedCombatStats, rangedCombatModel, model.getWeaponTagsModel());
        rangedCombatStats.setRange(rangedCombatModel.getRangeModel().getValue());
        return rangedCombatStats;
    }
    return null;
  }

  private void setBasicWeaponStats(WeaponStats stats, IOffensiveStatisticsModel model, IWeaponTagsModel tagsModel) {
    setName(stats, model);
    stats.setAccuracy(model.getAccuracyModel().getValue());
    stats.setDamage(model.getWeaponDamageModel().getValue());
    stats.setDamageType(model.getWeaponDamageModel().getHealthType());
    stats.setRate(model.getRateModel().getValue());
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