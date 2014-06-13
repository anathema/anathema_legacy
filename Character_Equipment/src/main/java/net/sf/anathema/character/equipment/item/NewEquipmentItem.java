package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Command;

public class NewEquipmentItem implements Command {
  private final IEquipmentDatabaseManagement model;
  private final EquipmentNavigation view;
  private final Resources resources;
  private StatsEditModel editModel;

  public NewEquipmentItem(IEquipmentDatabaseManagement model, EquipmentNavigation view, Resources resources, StatsEditModel editModel) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.editModel = editModel;
  }

  @Override
  public void execute() {
    DiscardChangesVetor vetor = new DiscardChangesVetor(model, view, resources);
    vetor.requestPermissionFor(() -> {
      model.getTemplateEditModel().setNewTemplate();
      editModel.clearStatsSelection();
      view.getTemplateListView().clearSelection();
    });
  }
}