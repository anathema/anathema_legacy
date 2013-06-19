package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.List;

public class WrappingStatsEditModel implements StatsEditModel {

  private IEquipmentDatabaseManagement model;

  public WrappingStatsEditModel(IEquipmentDatabaseManagement model) {
    this.model = model;
  }

  @Override
  public void addStatsChangeListener(IChangeListener listener) {
    editModel().addStatsChangeListener(listener);
  }

  @Override
  public EquipmentStatsFactory getStatsCreationFactory() {
    return model.getStatsCreationFactory();
  }

  @Override
  public List<IEquipmentStats> getStats() {
    return editModel().getStats();
  }

  @Override
  public void addStatistics(IEquipmentStats equipmentStats) {
    editModel().addStatistics(equipmentStats);
  }

  @Override
  public void addCompositionChangeListener(IChangeListener listener) {
    editModel().addCompositionChangeListener(listener);
  }

  @Override
  public MaterialComposition getMaterialComposition() {
    return editModel().getMaterialComposition();
  }

  @Override
  public StatsEditor getStatsEditor() {
    return model.getStatsEditor();
  }

  @Override
  public void replaceStatistics(IEquipmentStats selectedStats, IEquipmentStats newStats) {
    editModel().replaceStatistics(selectedStats, newStats);
  }

  @Override
  public void removeStatistics(IEquipmentStats[] stats) {
    editModel().removeStatistics(stats);
  }

  private IEquipmentTemplateEditModel editModel() {
    return model.getTemplateEditModel();
  }
}