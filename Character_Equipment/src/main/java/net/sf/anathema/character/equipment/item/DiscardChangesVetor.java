package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.repository.tree.VetorFactory;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.data.Condition;
import net.sf.anathema.lib.gui.list.veto.Vetor;

public class DiscardChangesVetor implements Vetor {

  private final Condition preCondition;
  private final Resources resources;
  private final VetorFactory factory;

  public DiscardChangesVetor(IEquipmentDatabaseManagement model, VetorFactory factory, Resources resources) {
    this.preCondition = new DirtyEquipmentCondition(model);
    this.factory = factory;
    this.resources = resources;
  }

  @Override
  public void requestPermissionFor(Command command) {
    if (!preCondition.isFulfilled()) {
      command.execute();
      return;
    }
    String messageText = resources.getString("Equipment.Creation.UnsavedChangesMessage.Text");
    String title = resources.getString("AnathemaCore.DialogTitle.ConfirmationDialog");
    Vetor vetor = factory.createVetor(title, messageText);
    vetor.requestPermissionFor(command);
  }
}