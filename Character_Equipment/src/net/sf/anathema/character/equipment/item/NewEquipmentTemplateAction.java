package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentDatabaseView;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.data.ICondition;
import net.sf.anathema.lib.resources.IResources;

public class NewEquipmentTemplateAction {

  private final IResources resources;
  private final IEquipmentDatabaseManagement model;

  public NewEquipmentTemplateAction(IResources resources, IEquipmentDatabaseManagement model) {
    this.resources = resources;
    this.model = model;
  }

  public void addToolTo(EquipmentDatabaseView view) {
    Tool newTool = view.addEditTemplateTool();
    newTool.setIcon("icons/TaskBarNew24.png");
    newTool.setTooltip(resources.getString("Equipment.Creation.Item.NewActionTooltip"));
    newTool.setCommand(new NewEquipmentItem());
  }

  private class NewEquipmentItem implements Command {
    @Override
    public void execute() {
      DiscardChangesVetor vetor = new DiscardChangesVetor(resources, new ICondition() {
        @Override
        public boolean isFulfilled() {
          return model.getTemplateEditModel().isDirty();
        }
      }, SwingApplicationFrame.getParentComponent());
      if (vetor.vetos()) {
        return;
      }
      model.getTemplateEditModel().setNewTemplate();
    }
  }
}