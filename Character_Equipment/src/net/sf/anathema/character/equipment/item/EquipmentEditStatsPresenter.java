package net.sf.anathema.character.equipment.item;

import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentEditStatsPresenter implements IPresenter {

  private final class RuleSetSelectionListener implements IObjectValueChangedListener<IExaltedRuleSet> {
    public void valueChanged(IExaltedRuleSet newValue) {
      updateStatListContent(newValue);
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
    final IObjectSelectionView<IExaltedRuleSet> ruleSetView = initRuleSetPresentation();
    view.getStatsListView().setCellRenderer(new ObjectUiListCellRenderer(new EquipmentStatsUi()));
    model.getTemplateEditModel().addStatsChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateStatListContent(ruleSetView.getSelectedObject());
      }
    });
  }

  private IObjectSelectionView<IExaltedRuleSet> initRuleSetPresentation() {
    ObjectUiListCellRenderer ruleSetRenderer = new ObjectUiListCellRenderer(new ExaltedRuleSetUi());
    final IObjectSelectionView<IExaltedRuleSet> ruleSetView = view.initRuleSetSelectionView("Ruleset", ruleSetRenderer);
    ruleSetView.setObjects(model.getSupportedExaltedRuleSets());
    ruleSetView.addObjectSelectionChangedListener(new RuleSetSelectionListener());
    return ruleSetView;
  }

  private void updateStatListContent(IExaltedRuleSet newValue) {
    view.getStatsListView().setObjects(model.getTemplateEditModel().getStats(newValue));
  }
}