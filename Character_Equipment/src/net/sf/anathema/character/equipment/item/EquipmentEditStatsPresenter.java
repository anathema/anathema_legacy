package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentEditStatsPresenter implements Presenter {

  private final Resources resources;
  private final EquipmentDetails view;
  private final StatsEditModel model;

  public EquipmentEditStatsPresenter(Resources resources, StatsEditModel model, EquipmentDetails view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    String title = resources.getString("Equipment.Creation.Stats");
    final ToolListView<IEquipmentStats> statsListView = view.initStatsListView(title,
            new EquipmentStatsUIConfiguration(resources));
    model.addStatsChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateStatListContent(statsListView);
      }
    });
    initButtons(statsListView);
  }

  private void initButtons(ToolListView<IEquipmentStats> statsListView) {
    AddNewStats addNewStats = new AddNewStats(resources, model, model.getStatsCreationFactory());
    addNewStats.addTool(new MeleeStatsConfiguration(), statsListView);
    addNewStats.addTool(new RangedStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArmourStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArtifactStatsConfiguration(), statsListView);
    addNewStats.addTool(new TraitModifierStatsConfiguration(), statsListView);
    new EditStats(resources, model, model.getStatsEditor()).addToolTo(statsListView);
    new RemoveStats(resources, model).addToolTo(statsListView);
  }

  private void updateStatListContent(ToolListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getStats());
  }
}