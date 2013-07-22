package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.EquipmentStatsFactory;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class AddNewStats {
  protected final Resources resources;
  protected final StatsEditModel editModel;
  protected final EquipmentStatsFactory statsFactory;

  public AddNewStats(Resources resources, StatsEditModel editModel,
                     EquipmentStatsFactory statsFactory) {
    this.editModel = editModel;
    this.statsFactory = statsFactory;
    this.resources = resources;
  }

  public void addTool(final NewStatsConfiguration newStatsConfiguration, ToolListView<IEquipmentStats> statsListView) {
    final Tool newTool = statsListView.addTool();
    newTool.setTooltip(resources.getString(newStatsConfiguration.getTooltipKey()));
    newTool.setIcon(newStatsConfiguration.getIconPath());
    newTool.setOverlay(new RelativePath("icons/ButtonPlus16.png"));
    newTool.setCommand(new Command() {
      @Override
      public void execute() {
        List<String> definedNames = new ArrayList<>();
        for (IEquipmentStats stats : editModel.getStats()) {
          definedNames.add(stats.getName().getId());
        }
        String nameProposal = resources.getString(newStatsConfiguration.getNameKey());
        String[] nameArray = definedNames.toArray(new String[definedNames.size()]);
        IEquipmentStats equipmentStats = statsFactory.createNewStats(nameArray, nameProposal,
                newStatsConfiguration.getType());
        editModel.addStatistics(equipmentStats);
      }
    });
    controlAvailability(newStatsConfiguration, newTool);
    editModel.addCompositionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        controlAvailability(newStatsConfiguration, newTool);
      }
    });
  }

  private void controlAvailability(NewStatsConfiguration newStatsConfiguration, Tool newTool) {
    if (statsFactory.canHaveThisKindOfStats(newStatsConfiguration.getType(), editModel.getMaterialComposition())) {
      newTool.enable();
    } else {
      newTool.disable();
    }
  }
}
