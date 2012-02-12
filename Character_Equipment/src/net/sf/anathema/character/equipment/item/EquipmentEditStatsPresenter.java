package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JList;

import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
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
    final EquipmentStringBuilder equipmentStringBuilder = new EquipmentStringBuilder(resources);
    ObjectUiListCellRenderer statsRenderer = new ObjectUiListCellRenderer(new EquipmentStatsUi(resources)) {
		private static final long serialVersionUID = 1L;

	@Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        JComponent component = (JComponent) super.getListCellRendererComponent(
            list,
            value,
            index,
            isSelected,
            cellHasFocus);
        component.setToolTipText(equipmentStringBuilder.createString(null, (IEquipmentStats) value));
        return component;
      }
    };
    final IActionAddableListView<IEquipmentStats> statsListView = view.initStatsListView(statsRenderer);
    view.setStatsListHeader(resources.getString("Equipment.Creation.Stats")); //$NON-NLS-1$
    final IObjectSelectionView<IExaltedRuleSet> ruleSetView = initRuleSetPresentation(statsListView);
    model.getTemplateEditModel().addStatsChangeListener(new IChangeListener() {
      public void changeOccurred() {
        updateStatListContent(ruleSetView.getSelectedObject(), statsListView);
      }
    });
    initButtons(statsListView, ruleSetView);
  }

  private void initButtons(
      IActionAddableListView<IEquipmentStats> statsListView,
      IObjectSelectionView<IExaltedRuleSet> ruleSetView) {
    statsListView.addAction(new AddNewStatsAction(
        resources,
        model.getTemplateEditModel(),
        ruleSetView,
        model.getStatsCreationFactory()));
    statsListView.addAction(new RemoveStatsAction(resources, model.getTemplateEditModel(), ruleSetView, statsListView));
    statsListView.addAction(new EditStatsAction(
        resources,
        model.getTemplateEditModel(),
        ruleSetView,
        statsListView,
        model.getStatsCreationFactory()));
  }

  private IObjectSelectionView<IExaltedRuleSet> initRuleSetPresentation(
      IActionAddableListView<IEquipmentStats> statsListView) {
    ObjectUiListCellRenderer ruleSetRenderer = new ObjectUiListCellRenderer(new ExaltedRuleSetUi(resources));
    final IObjectSelectionView<IExaltedRuleSet> ruleSetView = view.initRuleSetSelectionView(
        resources.getString("Equipment.Creation.Ruleset") + ":", ruleSetRenderer); //$NON-NLS-1$ //$NON-NLS-2$
    ruleSetView.setObjects(model.getSupportedExaltedRuleSets());
    ruleSetView.setSelectedObject(AnathemaCharacterPreferences.getDefaultPreferences().getPreferredRuleset());
    ruleSetView.addObjectSelectionChangedListener(new RuleSetSelectionListener(statsListView));
    return ruleSetView;
  }

  private void updateStatListContent(IExaltedRuleSet newValue, IActionAddableListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getTemplateEditModel().getStats(newValue));
  }
}