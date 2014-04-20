package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.equipment.item.model.StatsToModel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.util.Closure;

import java.util.ArrayList;
import java.util.List;

public class EditStatsCommand implements Command {
  private final StatsEditor statsEditor;
  private final StatsEditModel editModel;
  private final Resources resources;

  public EditStatsCommand(StatsEditor statsEditor, StatsEditModel editModel, Resources resources) {
    this.statsEditor = statsEditor;
    this.editModel = editModel;
    this.resources = resources;
  }

  @Override
  public void execute() {
    String[] forbiddenNames = getNamesOfAllOtherStats();
    statsEditor.whenChangesAreConfirmed(new ReplaceStats());
    IEquipmentStats selectedStats = editModel.getSelectedStats();
    IEquipmentStatisticsCreationModel model = new StatsToModel().createModel(selectedStats);
    model.setForbiddenNames(forbiddenNames);
    statsEditor.editStats(resources, model);
  }

  private String[] getNamesOfAllOtherStats() {
    IEquipmentStats selectedStats = editModel.getSelectedStats();
    List<String> definedNames = new ArrayList<>();
    for (IEquipmentStats stats : editModel.getStats()) {
      if (stats == selectedStats) {
        continue;
      }
      definedNames.add(stats.getName().getId());
    }
    return definedNames.toArray(new String[definedNames.size()]);
  }

  private class ReplaceStats implements Closure<IEquipmentStats> {

    @Override
    public void execute(IEquipmentStats newStats) {
      editModel.replaceSelectedStatistics(newStats);
    }
  }
}