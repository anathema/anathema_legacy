package net.sf.anathema.character.equipment.item.view;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.resources.Resources;

public interface StatsEditorDisplay {

  void hideEditor();

  NewStatsEditor showEditorFor(EquipmentStatisticsType artifact, Resources resources);
}
