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
    String title = resources.getString("Equipment.Creation.Stats");
    final ToolListView<IEquipmentStats> statsListView = view.initStatsListView(title, new EquipmentStatsUIConfiguration(resources));
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
    AddNewStats addNewStats = new AddNewStats(resources, editModel, model.getStatsCreationFactory());
    addNewStats.addTool(new MeleeStatsConfiguration(), statsListView);
    addNewStats.addTool(new RangedStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArmourStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArtifactStatsConfiguration(), statsListView);
    addNewStats.addTool(new TraitModifierStatsConfiguration(), statsListView);
    new EditStatsAction(resources, editModel, model.getStatsCreationFactory()).addToolTo(statsListView);
    new RemoveStatsAction(resources, editModel).addToolTo(statsListView);
  }

  private void updateStatListContent(ToolListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getTemplateEditModel().getStats());
  }
}