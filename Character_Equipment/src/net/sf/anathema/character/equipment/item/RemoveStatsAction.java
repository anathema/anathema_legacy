package net.sf.anathema.character.equipment.item;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;

public class RemoveStatsAction extends SmartAction {

  private final IEquipmentTemplateEditModel editModel;
  private final IExaltedRuleSet ruleset;
  private final IActionAddableListView<IEquipmentStats> statsListView;

  public RemoveStatsAction(IResources resources, IEquipmentTemplateEditModel editModel, IExaltedRuleSet ruleset, final IActionAddableListView<IEquipmentStats> statsListView) {
    super(new BasicUi(resources).getRemoveIcon());
    this.editModel = editModel;
    this.ruleset = ruleset;
    this.statsListView = statsListView;
    statsListView.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        updateEnabled();
      }
    });
    updateEnabled();
    setToolTipText(resources.getString("Equipment.Stats.Action.Remove.Tooltip")); //$NON-NLS-1$
  }

  private void updateEnabled() {
    setEnabled(statsListView.getSelectedItems().length > 0);
  }

  @Override
  protected void execute(Component parentComponent) {
    IEquipmentStats[] equipmentStats = statsListView.getSelectedItems();
    editModel.removeStatistics(ruleset, equipmentStats);
  }
}