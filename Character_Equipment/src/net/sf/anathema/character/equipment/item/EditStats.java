package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;

public class EditStats {
  private final StatsEditor factory;
  private final Resources resources;
  private final IEquipmentTemplateEditModel editModel;

  public EditStats(Resources resources, IEquipmentTemplateEditModel editModel, StatsEditor factory) {
    this.resources = resources;
    this.editModel = editModel;
    this.factory = factory;
  }

  public void addToolTo(final ToolListView<IEquipmentStats> statsListView) {
    final Tool tool = statsListView.addTool();
    tool.setIcon(new BasicUi().getEditIconPath());
    tool.setTooltip(resources.getString("Equipment.Creation.Stats.EditActionTooltip"));
    tool.setCommand(new EditStatsCommand(statsListView, factory, editModel, resources));
    statsListView.addListSelectionListener(new Runnable() {
      @Override
      public void run() {
        updateEnabled(statsListView, tool);
      }
    });
    updateEnabled(statsListView, tool);
  }

  private void updateEnabled(ToolListView<IEquipmentStats> statsListView, Tool tool) {
    if (statsListView.getSelectedItems().size() == 1) {
      tool.enable();
    } else {
      tool.disable();
    }
  }
}