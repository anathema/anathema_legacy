package net.sf.anathema.character.equipment.item;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.lib.gui.wizard.workflow.ICondition;
import net.sf.anathema.lib.resources.IResources;

public final class NewEquipmentTemplateAction extends SmartAction {
  private static final long serialVersionUID = -4191792139824501586L;
  private final IEquipmentDatabaseManagement model;
  private final IResources resources;

  public NewEquipmentTemplateAction(IResources resources, IEquipmentDatabaseManagement model) {
    super(new PlatformUI(resources).getNewIcon());
    this.resources = resources;
    this.model = model;
  }

  @Override
  protected void execute(Component parentComponent) {
    DiscardChangesVetor vetor = new DiscardChangesVetor(resources, new ICondition() {
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