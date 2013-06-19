package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.List;

public interface StatsEditModel {
  void addStatsChangeListener(IChangeListener iChangeListener);

  EquipmentStatsFactory getStatsCreationFactory();

  List<IEquipmentStats> getStats();

  void addStatistics(IEquipmentStats equipmentStats);

  void addCompositionChangeListener(IChangeListener iChangeListener);

  MaterialComposition getMaterialComposition();

  StatsEditor getStatsEditor();

  void replaceSelectedStatistics(IEquipmentStats newStats);

  void removeSelectedStatistics();

  void selectStats(IEquipmentStats selected);

  IEquipmentStats getSelectedStats();

  void whenSelectedStatsChanges(IChangeListener iChangeListener);

  boolean hasSelectedStats();
}
