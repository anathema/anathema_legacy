package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.ConfigurableVetor;
import net.sf.anathema.lib.resources.IResources;

public class RemoveEquipmentTemplateAction {
  private final IEquipmentDatabaseManagement model;
  private final IResources resources;

  public RemoveEquipmentTemplateAction(IResources resources, IEquipmentDatabaseManagement model) {
    this.resources = resources;
    this.model = model;
  }

  public void addToolTo(IEquipmentDatabaseView view) {
    final Tool removeTool = view.addEditTemplateTool();
    removeTool.setIcon("icons/ButtonCross24.png");
    removeTool.setTooltip(resources.getString("Equipment.Creation.Item.RemoveActionTooltip"));
    view.getTemplateListView().addObjectSelectionChangedListener(new EnableWhenItemSelected(removeTool));
    updateEnabled(removeTool, view.getTemplateListView().getSelectedObject());
    removeTool.setCommand(new RemoveEquipmentItem(view));
  }

  private void updateEnabled(Tool removeTool, String selectedObject) {
    if (selectedObject != null) {
      removeTool.enable();
    } else {
      removeTool.disable();
    }
  }

  private class RemoveEquipmentItem implements Command {
    private final IEquipmentDatabaseView view;

    public RemoveEquipmentItem(IEquipmentDatabaseView view) {
      this.view = view;
    }

    @Override
    public void execute() {
      String messageText = model.getTemplateEditModel().getEditTemplateId() + " - " + resources.getString("Equipment.Creation.DeleteMessage.Text");
      String okText = resources.getString("Equipment.Creation.DeleteMessage.OKButton");
      ConfigurableVetor vetor = new ConfigurableVetor(SwingApplicationFrame.getParentComponent(), messageText, okText);
      if (vetor.vetos()) {
        return;
      }
      model.getDatabase().deleteTemplate(view.getTemplateListView().getSelectedObject());
      model.getTemplateEditModel().setNewTemplate();
    }
  }

  private class EnableWhenItemSelected implements ObjectValueListener<String> {
    private final Tool removeTool;

    public EnableWhenItemSelected(Tool removeTool) {
      this.removeTool = removeTool;
    }

    @Override
    public void valueChanged(String newValue) {
      updateEnabled(removeTool, newValue);
    }
  }
}