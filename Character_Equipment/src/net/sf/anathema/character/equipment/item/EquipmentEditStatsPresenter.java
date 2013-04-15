package net.sf.anathema.character.equipment.item;

import com.google.common.eventbus.EventBus;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.item.view.EquipmentDetails;
import net.sf.anathema.character.equipment.item.view.ToolListView;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import java.util.List;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.Artifact;

public class EquipmentEditStatsPresenter implements Presenter {

  private final Resources resources;
  private final EquipmentDetails view;
  private final IEquipmentDatabaseManagement model;

  public EquipmentEditStatsPresenter(Resources resources, IEquipmentDatabaseManagement model, EquipmentDetails view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    String title = resources.getString("Equipment.Creation.Stats");
    final ToolListView<IEquipmentStats> statsListView = view.initStatsListView(title,
            new EquipmentStatsUIConfiguration(resources));
    model.getTemplateEditModel().addStatsChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateStatListContent(statsListView);
      }
    });
    statsListView.addListSelectionListener(new Runnable() {
      @Override
      public void run() {
        updateEditor(statsListView.getSelectedItems());
      }
    });
    initButtons(statsListView);
  }

  private void updateEditor(List<IEquipmentStats> selectedItems) {
    if (selectedItems.size() != 1) {
      view.hideEditor();
    }
    IEquipmentStats currentStats = selectedItems.get(0);
    if (!(currentStats instanceof MutableArtifactStats)) {
      view.hideEditor();
      return;
    }
    EventBus eventBus = new EventBus();
    ArtifactEditor editor = (ArtifactEditor) view.showEditorFor(Artifact, resources);
    editor.registerOn(eventBus);
    new ArtifactEditPresenter((MutableArtifactStats) currentStats, eventBus).initPresentation();
  }

  private void initButtons(ToolListView<IEquipmentStats> statsListView) {
    IEquipmentTemplateEditModel editModel = model.getTemplateEditModel();
    AddNewStats addNewStats = new AddNewStats(resources, editModel, model.getStatsCreationFactory());
    addNewStats.addTool(new MeleeStatsConfiguration(), statsListView);
    addNewStats.addTool(new RangedStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArmourStatsConfiguration(), statsListView);
    addNewStats.addTool(new ArtifactStatsConfiguration(), statsListView);
    addNewStats.addTool(new TraitModifierStatsConfiguration(), statsListView);
    new EditStats(resources, editModel, model.getStatsEditor()).addToolTo(statsListView);
    new RemoveStats(resources, editModel).addToolTo(statsListView);
  }

  private void updateStatListContent(ToolListView<IEquipmentStats> statsListView) {
    statsListView.setObjects(model.getTemplateEditModel().getStats());
  }
}