package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.ConfigurableVetor;
import net.sf.anathema.lib.resources.Resources;

public class RemoveEquipmentItem implements Command {
  private final EquipmentNavigation view;
  private final IEquipmentDatabaseManagement model;
  private final Resources resources;

  public RemoveEquipmentItem(EquipmentNavigation view, IEquipmentDatabaseManagement model, Resources resources) {
    this.view = view;
    this.model = model;
    this.resources = resources;
  }

  @Override
  public void execute() {
    String messageText = model.getTemplateEditModel().getEditTemplateId() + " - " + resources.getString(
            "Equipment.Creation.DeleteMessage.Text");
    String okText = resources.getString("Equipment.Creation.DeleteMessage.OKButton");
    ConfigurableVetor vetor = new ConfigurableVetor(SwingApplicationFrame.getParentComponent(), messageText, okText);
    if (vetor.vetos()) {
      return;
    }
    model.getDatabase().deleteTemplate(view.getTemplateListView().getSelectedObject());
    model.getTemplateEditModel().setNewTemplate();
  }
}