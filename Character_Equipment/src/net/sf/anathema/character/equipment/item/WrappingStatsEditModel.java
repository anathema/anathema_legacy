package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.List;

public class WrappingStatsEditModel implements StatsEditModel {

  private static final IEquipmentStats NO_SELECTION = null;
  private final IEquipmentDatabaseManagement model;
  private final Announcer<IChangeListener> announcer = new Announcer<>(IChangeListener.class);
  private IEquipmentStats selectedStats;

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
  public void replaceSelectedStatistics(IEquipmentStats newStats) {
    editModel().replaceStatistics(this.selectedStats, newStats);
  }

  @Override
  public void removeSelectedStatistics() {
    if(!hasSelectedStats()) {
      return;
    }
    editModel().removeStatistics(selectedStats);
    selectStats(NO_SELECTION);
  }

  @Override
  public void selectStats(IEquipmentStats selected) {
    if (this.selectedStats == selected) {
      return;
    }
    this.selectedStats = selected;
    announcer.announce().changeOccurred();
  }

  @Override
  public IEquipmentStats getSelectedStats() {
    return selectedStats;
  }

  @Override
  public void whenSelectedStatsChanges(IChangeListener listener) {
    announcer.addListener(listener);
  }

  @Override
  public boolean hasSelectedStats() {
    return selectedStats != NO_SELECTION;
  }

  private IEquipmentTemplateEditModel editModel() {
    return model.getTemplateEditModel();
  }
}