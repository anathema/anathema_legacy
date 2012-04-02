package net.sf.anathema.character.equipment.item;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class EditStatsAction extends SmartAction {
  private final IEquipmentStatsCreationFactory factory;
  private final IResources resources;
  private final IEquipmentTemplateEditModel editModel;
  private final IActionAddableListView<IEquipmentStats> statsListView;

  public EditStatsAction(IResources resources, IEquipmentTemplateEditModel editModel, IActionAddableListView<IEquipmentStats> statsListView,
                         IEquipmentStatsCreationFactory factory) {
    super(new BasicUi(resources).getEditIcon());
    this.resources = resources;
    this.editModel = editModel;
    this.statsListView = statsListView;
    this.factory = factory;
    statsListView.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        updateEnabled();
      }
    });
    updateEnabled();
    setToolTipText(resources.getString("Equipment.Creation.Stats.EditActionTooltip")); //$NON-NLS-1$
  }

  private void updateEnabled() {
    setEnabled(statsListView.getSelectedItems().length == 1);
  }

  @Override
  protected void execute(Component parentComponent) {
    IEquipmentStats selectedStats = statsListView.getSelectedItems()[0];
    List<String> definedNames = new ArrayList<String>();
    for (IEquipmentStats stats : editModel.getStats()) {
      if (stats == selectedStats) {
        continue;
      }
      definedNames.add(stats.getName().getId());
    }
    String[] nameArray = definedNames.toArray(new String[definedNames.size()]);
    IEquipmentStats equipmentStats = factory.editStats(parentComponent, resources, nameArray, selectedStats);
    if (equipmentStats == null) {
      return;
    }
    editModel.replaceStatistics(selectedStats, equipmentStats);
  }
}