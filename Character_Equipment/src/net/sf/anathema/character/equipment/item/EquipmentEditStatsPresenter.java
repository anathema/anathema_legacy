package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

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
    final IActionAddableListView<IEquipmentStats> statsListView = view.initStatsListView(new EquipmentStatsUIConfiguration(resources));
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
    IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
    statsListView.addAction(new AddNewStatsAction(resources, editModel, model.getStatsCreationFactory()));
    statsListView.addAction(new RemoveStatsAction(resources, editModel, statsListView));
    statsListView.addAction(new EditStatsAction(resources, editModel, statsListView, model.getStatsCreationFactory()));
  }

  private void updateStatListContent(IActionAddableListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getTemplateEditModel().getStats());
  }
}