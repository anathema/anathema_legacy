package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsModel;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.MaterialComposition;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;

public class SimpleEquipmentStatsFactory implements EquipmentStatsFactory {

  private final ModelToStats modelToStats = new ModelToStats();

  @Override
  public boolean canHaveThisKindOfStats(EquipmentStatisticsType type, MaterialComposition materialComposition) {
    boolean canHaveArtifactStats = materialComposition != MaterialComposition.None;
    return type != Artifact || canHaveArtifactStats;
  }

  @Override
  public IEquipmentStats createNewStats(String[] definedNames, String nameProposal, EquipmentStatisticsType type) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel();
    model.setForbiddenNames(definedNames);
    model.setEquipmentType(type);
    String finalName = createUniqueName(nameProposal, model);
    setNameOnCorrectModel(model, finalName);
    ensureModelIsValid(model);
    return modelToStats.createStats(model);
  }

  private void ensureModelIsValid(IEquipmentStatisticsCreationModel model) {
    if (model.getEquipmentType() == EquipmentStatisticsType.RangedCombat) {
      model.getWeaponTagsModel().getSelectedModel(WeaponTag.BowType).setValue(true);
    }
  }

  private void setNameOnCorrectModel(IEquipmentStatisticsCreationModel model, String finalName) {
    findMatchingModel(model).getName().setText(finalName);
  }

  private IEquipmentStatisticsModel findMatchingModel(IEquipmentStatisticsCreationModel model) {
    switch (model.getEquipmentType()) {
      case CloseCombat:
        return model.getCloseCombatStatsticsModel();
      case RangedCombat:
        return model.getRangedWeaponStatisticsModel();
      case Armor:
        return model.getArmourStatisticsModel();
      case TraitModifying:
        return model.getTraitModifyingStatisticsModel();
      case Artifact:
        return model.getArtifactStatisticsModel();
      default:
        throw new IllegalStateException("Type not seleted.");
    }
  }

  private String createUniqueName(String nameProposal, IEquipmentStatisticsCreationModel model) {
    int count = 1;
    String finalName = nameProposal;
    while (!model.isNameUnique(finalName)) {
      count++;
      finalName = nameProposal + " " + count;
    }
    return finalName;
  }
}