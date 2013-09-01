package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.framework.environment.Resources;

public class NewEquipmentItem implements Command {
  private final IEquipmentDatabaseManagement model;
  private final Resources resources;

  public NewEquipmentItem(IEquipmentDatabaseManagement model, Resources resources) {
    this.model = model;
    this.resources = resources;
  }

  @Override
  public void execute() {
    DiscardChangesVetor vetor = new DiscardChangesVetor(resources, new DirtyEquipmentCondition(model));
    vetor.requestPermissionFor(new Command() {
      @Override
      public void execute() {
        model.getTemplateEditModel().setNewTemplate();
      }
    });
  }
}