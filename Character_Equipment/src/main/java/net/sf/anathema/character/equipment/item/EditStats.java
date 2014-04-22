package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;

public class EditStats {
  private final StatsEditor statsEditor;
  private final Resources resources;
  private final StatsEditModel editModel;

  public EditStats(Resources resources, StatsEditModel editModel, StatsEditor statsEditor) {
    this.resources = resources;
    this.editModel = editModel;
    this.statsEditor = statsEditor;
  }

  public void addToolTo(final ToolListView<IEquipmentStats> statsListView) {
    final Tool tool = statsListView.addTool();
    tool.setIcon(new BasicUi().getEditIconPath());
    tool.setTooltip(resources.getString("Equipment.Creation.Stats.EditActionTooltip"));
    tool.setCommand(new EditStatsCommand(statsEditor, editModel, resources));
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