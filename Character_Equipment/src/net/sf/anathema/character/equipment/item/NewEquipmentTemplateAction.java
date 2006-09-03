package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public final class NewEquipmentTemplateAction extends SmartAction {
  private final IEquipmentDatabaseManagement model;

  public NewEquipmentTemplateAction(IResources resources, IEquipmentDatabaseManagement model) {
    super("new");
    this.model = model;
  }

  @Override
  protected void execute(Component parentComponent) {
    DiscardChangesVetor vetor = new DiscardChangesVetor(new ICondition() {
      public boolean isFullfilled() {
        return model.getTemplateEditModel().isDirty();
      }
    }, parentComponent);
    if (vetor.vetos()) {
      return;
    }
    model.getTemplateEditModel().setNewTemplate();
  }
}