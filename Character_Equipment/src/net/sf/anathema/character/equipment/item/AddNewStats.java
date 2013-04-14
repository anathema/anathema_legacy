package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class AddNewStats {
  protected final Resources resources;
  protected final IEquipmentTemplateEditModel editModel;
  protected final IEquipmentStatsCreationFactory statsFactory;

  public AddNewStats(Resources resources, IEquipmentTemplateEditModel editModel,
                     IEquipmentStatsCreationFactory statsFactory) {
    this.editModel = editModel;
    this.statsFactory = statsFactory;
    this.resources = resources;
  }

  public void addTool(final NewStatsConfiguration newStatsConfiguration, ToolListView<IEquipmentStats> statsListView) {
    Tool newTool = statsListView.addTool();
    newTool.setTooltip(resources.getString(newStatsConfiguration.getTooltipKey()));
    newTool.setIcon(newStatsConfiguration.getIconPath());
    newTool.setCommand(new Command() {
      @Override
      public void execute() {
        List<String> definedNames = new ArrayList<>();
        for (IEquipmentStats stats : editModel.getStats()) {
          definedNames.add(stats.getName().getId());
        }
        String nameProposal = resources.getString(newStatsConfiguration.getNameKey());
        String[] nameArray = definedNames.toArray(new String[definedNames.size()]);
        IEquipmentStats equipmentStats = statsFactory.createNewStatsQuickly(nameArray, nameProposal,
                newStatsConfiguration.getType());
        editModel.addStatistics(equipmentStats);
      }
    });
  }
}
