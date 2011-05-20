package net.sf.anathema.character.equipment.impl.creation.model;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IShieldStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class EquipmentStatisticsCreationModel implements IEquipmentStatisticsCreationModel {

  private final ICloseCombatStatsticsModel closeCombatStatisticsModel;
  private final IRangedCombatStatisticsModel rangedWeaponStatisticsModel;
  private final IShieldStatisticsModel shieldStatisticsModel = new ShieldStatisticsModel();
  private final IArmourStatisticsModel armourStatisticsModel = new ArmourStatsticsModel();
  private final IArtifactStatisticsModel artifactStatisticsModel = new ArtifactStatisticsModel();
  private final ChangeControl equpimentTypeChangeControl = new ChangeControl();
  private final IWeaponTagsModel weaponTagsModel = new WeaponTagsModel();
  private EquipmentStatisticsType statisticsType;
  private final String[] existingNames;

  public EquipmentStatisticsCreationModel(String[] existingNames, IExaltedRuleSet ruleset) {
    this.existingNames = existingNames;
    this.closeCombatStatisticsModel = new CloseCombatStatsticsModel(createOffensiveSpeedModel(ruleset), ruleset);
    this.rangedWeaponStatisticsModel = new RangedWeaponStatisticsModel(createOffensiveSpeedModel(ruleset));
  }

  private IIntValueModel createOffensiveSpeedModel(IExaltedRuleSet ruleset) {
    final IIntValueModel[] speedModel = new IIntValueModel[1];
    ruleset.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        speedModel[0] = new RangedIntValueModel(new Range(Integer.MIN_VALUE, Integer.MAX_VALUE), 1);
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        speedModel[0] = new RangedIntValueModel(new Range(Integer.MIN_VALUE, Integer.MAX_VALUE), 1);
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        speedModel[0] = new RangedIntValueModel(new Range(1, Integer.MAX_VALUE), 1);
      }
    });
    return speedModel[0];
  }

  public void setEquipmentType(EquipmentStatisticsType statisticsType) {
    if (this.statisticsType == statisticsType) {
      return;
    }
    if (statisticsType == EquipmentStatisticsType.RangedCombat) {
      getWeaponTagsModel().setTagsRangedCombatStyle();
    }
    else {
      getWeaponTagsModel().setTagsCloseCombatStyle();
    }
    this.statisticsType = statisticsType;
    equpimentTypeChangeControl.fireChangedEvent();
  }

  public ICloseCombatStatsticsModel getCloseCombatStatsticsModel() {
    return closeCombatStatisticsModel;
  }

  public void addEquipmentTypeChangeListener(IChangeListener changeListener) {
    equpimentTypeChangeControl.addChangeListener(changeListener);
  }

  public boolean isEquipmentTypeSelected(EquipmentStatisticsType type) {
    return this.statisticsType == type;
  }

  public IWeaponTagsModel getWeaponTagsModel() {
    return weaponTagsModel;
  }

  public IRangedCombatStatisticsModel getRangedWeaponStatisticsModel() {
    return rangedWeaponStatisticsModel;
  }

  public IShieldStatisticsModel getShieldStatisticsModel() {
    return shieldStatisticsModel;
  }

  public IArmourStatisticsModel getArmourStatisticsModel() {
    return armourStatisticsModel;
  }
  
  public IArtifactStatisticsModel getArtifactStatisticsModel() {
	    return artifactStatisticsModel;
	  }

  public EquipmentStatisticsType getEquipmentType() {
    return statisticsType;
  }

  public boolean isNameUnique(String name) {
    return !ArrayUtilities.contains(existingNames, name);
  }
}