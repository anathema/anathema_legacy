package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ICloseCombatStatsticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IRangedCombatStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.control.ChangeListener;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

public class EquipmentStatisticsCreationModel implements IEquipmentStatisticsCreationModel {

  private final IWeaponTagsModel weaponTagsModel = new WeaponTagsModel();
  private final ICloseCombatStatsticsModel closeCombatStatisticsModel = new CloseCombatStatsticsModel();
  private final IRangedCombatStatisticsModel rangedWeaponStatisticsModel = new RangedWeaponStatisticsModel(weaponTagsModel);
  private final IArmourStatisticsModel armourStatisticsModel = new ArmourStatsticsModel();
  private final IArtifactStatisticsModel artifactStatisticsModel = new ArtifactStatisticsModel();
  private final ITraitModifyingStatisticsModel traitModifyingStatisticsModel = new TraitModifyingStatisticsModel();
  private final Announcer<ChangeListener> equipmentTypeChangeControl = Announcer.to(ChangeListener.class);
  private EquipmentStatisticsType statisticsType;
  private String[] existingNames;

  @Override
  public void setEquipmentType(EquipmentStatisticsType statisticsType) {
    if (this.statisticsType == statisticsType) {
      return;
    }
    if (statisticsType == EquipmentStatisticsType.RangedCombat) {
      getWeaponTagsModel().setTagsRangedCombatStyle();
    } else {
      getWeaponTagsModel().setTagsCloseCombatStyle();
    }
    this.statisticsType = statisticsType;
    equipmentTypeChangeControl.announce().changeOccurred();
  }

  @Override
  public ICloseCombatStatsticsModel getCloseCombatStatsticsModel() {
    return closeCombatStatisticsModel;
  }

  @Override
  public IWeaponTagsModel getWeaponTagsModel() {
    return weaponTagsModel;
  }

  @Override
  public IRangedCombatStatisticsModel getRangedWeaponStatisticsModel() {
    return rangedWeaponStatisticsModel;
  }

  @Override
  public IArmourStatisticsModel getArmourStatisticsModel() {
    return armourStatisticsModel;
  }

  @Override
  public IArtifactStatisticsModel getArtifactStatisticsModel() {
    return artifactStatisticsModel;
  }

  @Override
  public ITraitModifyingStatisticsModel getTraitModifyingStatisticsModel() {
    return traitModifyingStatisticsModel;
  }

  @Override
  public EquipmentStatisticsType getEquipmentType() {
    return statisticsType;
  }

  @Override
  public boolean isNameUnique(String name) {
    return !ArrayUtils.contains(existingNames, name);
  }

  @Override
  public void setForbiddenNames(String[] definedNames) {
    this.existingNames = definedNames;
  }
}