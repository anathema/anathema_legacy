package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.list.actionview.ToolListView;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class AddNewStatsAction {
  private final IEquipmentStatsCreationFactory statsFactory;
  private final IResources resources;
  private final IEquipmentTemplateEditModel editModel;

  public AddNewStatsAction(IResources resources, IEquipmentTemplateEditModel editModel,
                           IEquipmentStatsCreationFactory statsFactory) {
    this.resources = resources;
    this.editModel = editModel;
    this.statsFactory = statsFactory;
  }

  public void addToolTo(ToolListView<IEquipmentStats> statsListView) {
    Tool newTool = statsListView.addTool();
    newTool.setTooltip(resources.getString("Equipment.Creation.Stats.AddActionTooltip"));
    newTool.setIcon("icons/ButtonPlus16.png");
    newTool.setCommand(new Command() {
      @Override
      public void execute() {
        List<String> definedNames = new ArrayList<>();
        for (IEquipmentStats stats : editModel.getStats()) {
          definedNames.add(stats.getName().getId());
        }
        String[] nameArray = definedNames.toArray(new String[definedNames.size()]);
        MaterialComposition materialComposition = editModel.getMaterialComposition();
        IEquipmentStats equipmentStats = statsFactory.createNewStats(SwingApplicationFrame.getParentComponent(), resources, nameArray, materialComposition);
        if (equipmentStats == null) {
          return;
        }
        editModel.addStatistics(equipmentStats);
      }
    });
  }
}