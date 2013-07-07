package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Closure;

public class EquipmentEditStatsPresenter implements Presenter {

  private final Resources resources;
  private final EquipmentDetails equipmentView;
  private final StatsEditModel model;

  public EquipmentEditStatsPresenter(Resources resources, StatsEditModel model, EquipmentDetails equipmentView) {
    this.resources = resources;
    this.model = model;
    this.equipmentView = equipmentView;
  }

  @Override
  public void initPresentation() {
    String title = resources.getString("Equipment.Creation.Stats");
    ToolListView<IEquipmentStats> statsListView = equipmentView.initStatsListView(title,
            new EquipmentStatsUIConfiguration(resources));
    initListening(statsListView);
    initButtons(statsListView);
  }

  private void initListening(final ToolListView<IEquipmentStats> view) {
    model.addStatsChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        updateStatListContent(view);
      }
    });
    view.addListSelectionListener(new Closure<IEquipmentStats>() {
      @Override
      public void execute(IEquipmentStats selected) {
        model.selectStats(selected);
      }
    });
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