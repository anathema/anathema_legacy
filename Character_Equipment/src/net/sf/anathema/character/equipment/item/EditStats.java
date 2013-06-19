package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.Resources;

public class EditStats {
  private final StatsEditor factory;
  private final Resources resources;
  private final StatsEditModel editModel;

  public EditStats(Resources resources, StatsEditModel editModel, StatsEditor factory) {
    this.resources = resources;
    this.editModel = editModel;
    this.factory = factory;
  }

  public void addToolTo(final ToolListView<IEquipmentStats> statsListView) {
    final Tool tool = statsListView.addTool();
    tool.setIcon(new BasicUi().getEditIconPath());
    tool.setTooltip(resources.getString("Equipment.Creation.Stats.EditActionTooltip"));
    tool.setCommand(new EditStatsCommand(factory, editModel, resources));
    editModel.whenSelectedStatsChanges(new IChangeListener() {
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