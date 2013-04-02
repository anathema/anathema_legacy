package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.list.actionview.ToolListView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.IStringResourceHandler;

public class RemoveStatsAction {

  private final IStringResourceHandler resources;
  private final IEquipmentTemplateEditModel editModel;

  public RemoveStatsAction(IStringResourceHandler resources, IEquipmentTemplateEditModel editModel) {
    this.resources = resources;
    this.editModel = editModel;

  }

  public void addToolTo(final ToolListView<IEquipmentStats> statsListView) {
    final Tool tool = statsListView.addTool();
    tool.setIcon("icons/ButtonMinus16.png");
    tool.setTooltip(resources.getString("Equipment.Stats.Action.Remove.Tooltip"));
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        IEquipmentStats[] equipmentStats = statsListView.getSelectedItems();
        editModel.removeStatistics(equipmentStats);
      }
    });
    statsListView.addListSelectionListener(new Runnable() {
      @Override
      public void run() {
        updateEnabled(statsListView, tool);
      }
    });
    updateEnabled(statsListView, tool);
  }

  private void updateEnabled(ToolListView<IEquipmentStats> statsListView, Tool tool) {
    if (statsListView.getSelectedItems().length > 0) {
      tool.enable();
    } else {
      tool.disable();
    }
  }
}