package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.model.StatsEditor;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

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
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        IEquipmentStats selectedStats = statsListView.getSelectedItems().get(0);
        List<String> definedNames = new ArrayList<>();
        for (IEquipmentStats stats : editModel.getStats()) {
          if (stats == selectedStats) {
            continue;
          }
          definedNames.add(stats.getName().getId());
        }
        String[] nameArray = definedNames.toArray(new String[definedNames.size()]);
        IEquipmentStats equipmentStats = factory.editStats(resources, nameArray, selectedStats);
        if (equipmentStats == null) {
          return;
        }
        editModel.replaceStatistics(selectedStats, equipmentStats);
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
    if (statsListView.getSelectedItems().size() == 1) {
      tool.enable();
    } else {
      tool.disable();
    }
  }
}