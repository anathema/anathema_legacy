package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.util.Closure;

import java.util.ArrayList;
import java.util.List;

public class EditStatsCommand implements Command {
  private final StatsEditor factory;
  private final StatsEditModel editModel;
  private final Resources resources;

  public EditStatsCommand(StatsEditor factory, StatsEditModel editModel, Resources resources) {
    this.factory = factory;
    this.editModel = editModel;
    this.resources = resources;
  }

  @Override
  public void execute() {
    String[] names = getNamesOfAllOtherStats();
    factory.whenChangesAreConfirmed(new ReplaceStats());
    IEquipmentStats selectedStats = editModel.getSelectedStats();
    factory.editStats(resources, names, selectedStats);
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