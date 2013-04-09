package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentEditStatsPresenter implements Presenter {

  private final Resources resources;
  private final EquipmentDetails view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentEditStatsPresenter(Resources resources, IEquipmentDatabaseManagement model,
                                     EquipmentDetails view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    final ToolListView<IEquipmentStats> statsListView = view.initStatsListView(new EquipmentStatsUIConfiguration(resources));
    view.setStatsListHeader(resources.getString("Equipment.Creation.Stats"));
    model.getTemplateEditModel().addStatsChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateStatListContent(statsListView);
      }
    });
    initButtons(statsListView);
  }

  private void initButtons(ToolListView<IEquipmentStats> statsListView) {
    IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
    new AddNewStatsAction(resources, editModel, model.getStatsCreationFactory()).addToolTo(statsListView);
    new RemoveStatsAction(resources, editModel).addToolTo(statsListView);
    new EditStatsAction(resources, editModel, model.getStatsCreationFactory()).addToolTo(statsListView);
  }

  private void updateStatListContent(ToolListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getTemplateEditModel().getStats());
  }
}