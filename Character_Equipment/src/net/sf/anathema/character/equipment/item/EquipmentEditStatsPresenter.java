package net.sf.anathema.character.equipment.item;

import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.equipment.character.EquipmentStringBuilder;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JList;
import java.awt.Component;

public class EquipmentEditStatsPresenter implements Presenter {

  private final IResources resources;
  private final IEquipmentDatabaseView view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentEditStatsPresenter(IResources resources, IEquipmentDatabaseManagement model,
                                     IEquipmentDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    final EquipmentStringBuilder equipmentStringBuilder = new EquipmentStringBuilder(resources);
    ObjectUiListCellRenderer statsRenderer = new ObjectUiListCellRenderer(new EquipmentStatsUi(resources)) {
      private static final long serialVersionUID = 1L;

      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                    boolean cellHasFocus) {
        JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        component.setToolTipText(equipmentStringBuilder.createString(null, (IEquipmentStats) value));
        return component;
      }
    };
    final IActionAddableListView<IEquipmentStats> statsListView = view.initStatsListView(statsRenderer);
    view.setStatsListHeader(resources.getString("Equipment.Creation.Stats")); //$NON-NLS-1$
    model.getTemplateEditModel().addStatsChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateStatListContent(statsListView);
      }
    });
    initButtons(statsListView);
  }

  private void initButtons(IActionAddableListView<IEquipmentStats> statsListView) {
    IExaltedRuleSet ruleset = model.getSupportedExaltedRuleSets()[0];
    statsListView.addAction(
            new AddNewStatsAction(resources, model.getTemplateEditModel(), ruleset, model.getStatsCreationFactory()));
    statsListView.addAction(new RemoveStatsAction(resources, model.getTemplateEditModel(), ruleset, statsListView));
    statsListView.addAction(new EditStatsAction(resources, model.getTemplateEditModel(), ruleset, statsListView,
            model.getStatsCreationFactory()));
  }

  private void updateStatListContent(IActionAddableListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getTemplateEditModel().getStats(model.getSupportedExaltedRuleSets()[0]));
  }
}
