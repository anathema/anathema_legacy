package net.sf.anathema.character.equipment.impl.item.model;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsModel;
import net.sf.anathema.character.equipment.impl.creation.model.EquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;

public class SimpleEquipmentStatsFactory implements EquipmentStatsFactory {

  private final StatsFactory statsFactory;

  public SimpleEquipmentStatsFactory(ICollectionFactory collectionFactory) {
    this.statsFactory = new StatsFactory(collectionFactory);
  }

  @Override
  public boolean canHaveThisKindOfStats(EquipmentStatisticsType type, MaterialComposition materialComposition) {
    boolean canHaveArtifactStats = materialComposition != MaterialComposition.None;
    return type != Artifact || canHaveArtifactStats;
  }

  @Override
  public IEquipmentStats createNewStats(String[] definedNames, String nameProposal, EquipmentStatisticsType type) {
    IEquipmentStatisticsCreationModel model = new EquipmentStatisticsCreationModel(definedNames);
    model.setEquipmentType(type);
    String finalName = createUniqueName(nameProposal, model);
    setNameOnCorrectModel(model, finalName);
    return statsFactory.createStats(model);
  }

  private void setNameOnCorrectModel(IEquipmentStatisticsCreationModel model, String finalName) {
    IEquipmentStatisticsModel typeModel = null;
    switch (model.getEquipmentType()) {
      case CloseCombat:
        typeModel = model.getCloseCombatStatsticsModel();
        break;
      case RangedCombat:
        typeModel = model.getRangedWeaponStatisticsModel();
        break;
      case Armor:
        typeModel = model.getArmourStatisticsModel();
        break;
      case TraitModifying:
        typeModel = model.getTraitModifyingStatisticsModel();
        break;
      case Artifact:
        typeModel = model.getArtifactStatisticsModel();
        break;
    }
    typeModel.getName().setText(finalName);
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