package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.ArmourStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.ArtifactStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.CloseCombatStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.IEquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.creation.presenter.stats.RangedCombatStatisticsPresenterPage;
import net.sf.anathema.character.equipment.creation.presenter.stats.TraitModifyingStatisticsPresenterPage;
import net.sf.anathema.character.equipment.impl.creation.EquipmentStatisticsCreationViewFactory;
import net.sf.anathema.character.equipment.impl.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.equipment.wizard.AnathemaWizardDialog;
import net.sf.anathema.character.equipment.wizard.IAnathemaWizardPage;
import net.sf.anathema.character.equipment.wizard.WizardDialog;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;

public class SwingStatsEditor implements StatsEditor {

  private final StatsFactory statsFactory;

  public SwingStatsEditor(ICollectionFactory collectionFactory) {
    this.statsFactory = new StatsFactory(collectionFactory);
  }

  @Override
  public IEquipmentStats editStats(Resources resources, String[] definedNames, IEquipmentStats stats) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(definedNames);
    createModel(model, stats);
    return runDialog(resources, model);
  }

  private IEquipmentStats runDialog(Resources resources, IEquipmentStatisticsCreationModel model) {
    IEquipmentStatisticsCreationViewFactory viewFactory = new EquipmentStatisticsCreationViewFactory();
    IAnathemaWizardPage startPage = chooseStartPage(resources, model, viewFactory);
    WizardDialog dialog = new AnathemaWizardDialog(SwingApplicationFrame.getParentComponent(), startPage);
    IDialogResult result = dialog.show();
    if (result.isCanceled()) {
      return null;
    }
    return statsFactory.createStats(model);
  }

  private IAnathemaWizardPage chooseStartPage(Resources resources, IEquipmentStatisticsCreationModel model,
                                              IEquipmentStatisticsCreationViewFactory viewFactory) {
    switch (model.getEquipmentType()) {
      case CloseCombat:
        return new CloseCombatStatisticsPresenterPage(resources, model, viewFactory);
      case RangedCombat:
        return new RangedCombatStatisticsPresenterPage(resources, model, viewFactory);
      case Armor:
        return new ArmourStatisticsPresenterPage(resources, model, viewFactory);
      case Artifact:
        return new ArtifactStatisticsPresenterPage(resources, model, viewFactory);
      case TraitModifying:
        return new TraitModifyingStatisticsPresenterPage(resources, model, viewFactory);
      default:
        throw new IllegalArgumentException("Type must be defined to edit.");
    }
  }

  private void createModel(IEquipmentStatisticsCreationModel model, IEquipmentStats stats) {
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
    } else if (stats instanceof IArtifactStats) {
      IArtifactStats artifactStats = (IArtifactStats) stats;
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
    } else {
      throw new NotYetImplementedException();
    }
  }

  private void fillWeaponTagsModel(IWeaponTagsModel weaponTagsModel, IWeaponStats weaponStats) {
    for (Identified tag : weaponStats.getTags()) {
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