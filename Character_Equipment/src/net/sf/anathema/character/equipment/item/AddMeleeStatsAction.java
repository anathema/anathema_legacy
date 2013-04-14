package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.CloseCombat;

public class AddMeleeStatsAction {
  private final Resources resources;
  private final IEquipmentTemplateEditModel editModel;
  private final IEquipmentStatsCreationFactory statsFactory;

  public AddMeleeStatsAction(Resources resources, IEquipmentTemplateEditModel editModel,
                             IEquipmentStatsCreationFactory statsFactory) {
    this.resources = resources;
    this.editModel = editModel;
    this.statsFactory = statsFactory;
  }

  public void addToolTo(ToolListView<IEquipmentStats> statsListView) {
    Tool newTool = statsListView.addTool();
    newTool.setTooltip(resources.getString("Equipment.Creation.Stats.AddMeleeTooltip"));
    newTool.setIcon(new RelativePath("icons/CloseCombat16.png"));
    newTool.setCommand(new Command() {
      @Override
      public void execute() {
        List<String> definedNames = new ArrayList<>();
        for (IEquipmentStats stats : editModel.getStats()) {
          definedNames.add(stats.getName().getId());
        }
        String nameProposal = resources.getString("EquipmentStats.CloseCombat");
        String[] nameArray = definedNames.toArray(new String[definedNames.size()]);
        IEquipmentStats equipmentStats = statsFactory.createNewStatsQuickly(nameArray, nameProposal, CloseCombat);
        editModel.addStatistics(equipmentStats);
      }
    });
  }
}