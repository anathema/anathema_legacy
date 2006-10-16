package net.sf.anathema.character.equipment.item;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentStatsCreationFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class EditStatsAction extends SmartAction {
  private final IEquipmentStatsCreationFactory factory;
  private final IResources resources;
  private final IEquipmentTemplateEditModel editModel;
  private final IObjectSelectionView<IExaltedRuleSet> ruleSetView;
  private final IActionAddableListView<IEquipmentStats> statsListView;

  public EditStatsAction(
      IResources resources,
      IEquipmentTemplateEditModel editModel,
      IObjectSelectionView<IExaltedRuleSet> ruleSetView,
      final IActionAddableListView<IEquipmentStats> statsListView,
      IEquipmentStatsCreationFactory factory) {
    super(new BasicUi(resources).getEditIcon());
    this.resources = resources;
    this.editModel = editModel;
    this.ruleSetView = ruleSetView;
    this.statsListView = statsListView;
    this.factory = factory;
    ruleSetView.addObjectSelectionChangedListener(new IObjectValueChangedListener<IExaltedRuleSet>() {
      public void valueChanged(IExaltedRuleSet newValue) {
        updateEnabled();
      }
    });
    statsListView.addListSelectionListener(new ListSelectionListener() {
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
    IExaltedRuleSet ruleset = ruleSetView.getSelectedObject();
    IEquipmentStats selectedStats = statsListView.getSelectedItems()[0];
    List<String> definedNames = new ArrayList<String>();
    for (IEquipmentStats stats : editModel.getStats(ruleset)) {
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
    editModel.replaceStatistics(ruleset, selectedStats, equipmentStats);
  }
}