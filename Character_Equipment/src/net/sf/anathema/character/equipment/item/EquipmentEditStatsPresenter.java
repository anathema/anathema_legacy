package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentEditStatsPresenter implements IPresenter {

  private final class RuleSetSelectionListener implements IObjectValueChangedListener<IExaltedRuleSet> {

    private final IActionAddableListView<IEquipmentStats> statsListView;

    public RuleSetSelectionListener(IActionAddableListView<IEquipmentStats> statsListView) {
      this.statsListView = statsListView;
    }

    public void valueChanged(IExaltedRuleSet newValue) {
      updateStatListContent(newValue, statsListView);
    }
  }

  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentEditStatsPresenter(
      IResources resources,
      IEquipmentDatabaseManagement model,
      IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  public void initPresentation() {
    ObjectUiListCellRenderer statsRenderer = new ObjectUiListCellRenderer(new EquipmentStatsUi());
    final IActionAddableListView<IEquipmentStats> statsListView = view.initStatsListView(statsRenderer);
    final IObjectSelectionView<IExaltedRuleSet> ruleSetView = initRuleSetPresentation(statsListView);
    model.getTemplateEditModel().addStatsChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateStatListContent(ruleSetView.getSelectedObject(), statsListView);
      }
    });
    initButtons(statsListView);
  }

  private void initButtons(IActionAddableListView<IEquipmentStats> statsListView) {
    SmartAction addAction = new SmartAction(new BasicUi(resources).getAddIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        // TODO Auto-generated method stub

      }
    };
    addAction.setToolTipText("&Add new stats...");
    statsListView.addAction(addAction);
  }

  private IObjectSelectionView<IExaltedRuleSet> initRuleSetPresentation(
      IActionAddableListView<IEquipmentStats> statsListView) {
    ObjectUiListCellRenderer ruleSetRenderer = new ObjectUiListCellRenderer(new ExaltedRuleSetUi());
    final IObjectSelectionView<IExaltedRuleSet> ruleSetView = view.initRuleSetSelectionView("Ruleset", ruleSetRenderer);
    ruleSetView.setObjects(model.getSupportedExaltedRuleSets());
    ruleSetView.addObjectSelectionChangedListener(new RuleSetSelectionListener(statsListView));
    return ruleSetView;
  }

  private void updateStatListContent(IExaltedRuleSet newValue, IActionAddableListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getTemplateEditModel().getStats(newValue));
  }
}