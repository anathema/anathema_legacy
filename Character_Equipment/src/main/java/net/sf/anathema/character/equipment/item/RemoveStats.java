package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.resources.Resources;

public class RemoveStats {

  private final Resources resources;
  private final StatsEditModel editModel;

  public RemoveStats(Resources resources, StatsEditModel editModel) {
    this.resources = resources;
    this.editModel = editModel;
  }

  public void addToolTo(final ToolListView<IEquipmentStats> statsListView) {
    final Tool tool = statsListView.addTool();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setTooltip(resources.getString("Equipment.Stats.Action.Remove.Tooltip"));
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        editModel.removeSelectedStatistics();
      }
    });
    editModel.whenSelectedStatsChanges(new ChangeListener() {
      @Override
      public void changeOccurred() {
        updateEnabled(tool);
      }
    });
    updateEnabled(tool);
  }

  private void updateEnabled(Tool tool) {
    if (editModel.hasSelectedStats()) {
      tool.enable();
    } else {
      tool.disable();
    }
  }
}