package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.List;

public class WrappingStatsEditModel implements StatsEditModel {

  private static final IEquipmentStats NO_SELECTION = null;
  private final IEquipmentDatabaseManagement model;
  private final Announcer<ChangeListener> announcer = new Announcer<>(ChangeListener.class);
  private IEquipmentStats selectedStats;

  public WrappingStatsEditModel(IEquipmentDatabaseManagement model) {
    this.model = model;
  }

  @Override
  public void addStatsChangeListener(ChangeListener listener) {
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
  public void addCompositionChangeListener(ChangeListener listener) {
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
  public void whenSelectedStatsChanges(ChangeListener listener) {
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