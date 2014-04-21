package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Command;

public class NewEquipmentItem implements Command {
  private final IEquipmentDatabaseManagement model;
  private final EquipmentNavigation view;
  private final Resources resources;

  public NewEquipmentItem(IEquipmentDatabaseManagement model, EquipmentNavigation view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  @Override
  public void execute() {
    DiscardChangesVetor vetor = new DiscardChangesVetor(model, view, resources);
    vetor.requestPermissionFor(new Command() {
      @Override
      public void execute() {
        model.getTemplateEditModel().setNewTemplate();
      }
    });
  }
}