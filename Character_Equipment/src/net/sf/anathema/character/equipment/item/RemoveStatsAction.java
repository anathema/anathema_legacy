package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class RemoveStatsAction extends SmartAction {

  private final IResources resources;
  private final IObjectSelectionView<IExaltedRuleSet> ruleSetView;
  private final IEquipmentTemplateEditModel editModel;
  private final IActionAddableListView<IEquipmentStats> statsListView;

  public RemoveStatsAction(
      IResources resources,
      IEquipmentTemplateEditModel editModel,
      IObjectSelectionView<IExaltedRuleSet> ruleSetView,
      final IActionAddableListView<IEquipmentStats> statsListView) {
    super(new BasicUi(resources).getRemoveIcon());
    this.resources = resources;
    this.editModel = editModel;
    this.ruleSetView = ruleSetView;
    this.statsListView = statsListView;
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
    setToolTipText("Remove selected stats...");
  }

  private void updateEnabled() {
    setEnabled(statsListView.getSelectedItems().length > 0 && ruleSetView.getSelectedObject() != null);
  }

  @Override
  protected void execute(Component parentComponent) {
    IEquipmentStats[] equipmentStats = statsListView.getSelectedItems();
    editModel.removeStatistics(ruleSetView.getSelectedObject(), equipmentStats);
  }
}