package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.ChangeListener;

import java.util.List;

public interface StatsEditModel {
  void addStatsChangeListener(ChangeListener changeListener);

  EquipmentStatsFactory getStatsCreationFactory();

  List<IEquipmentStats> getStats();

  void addStatistics(IEquipmentStats equipmentStats);

  void addCompositionChangeListener(ChangeListener changeListener);

  MaterialComposition getMaterialComposition();

  void replaceSelectedStatistics(IEquipmentStats newStats);

  void removeSelectedStatistics();

  void selectStats(IEquipmentStats selected);

  IEquipmentStats getSelectedStats();

  void whenSelectedStatsChanges(ChangeListener changeListener);

  boolean hasSelectedStats();
}
