package net.sf.anathema.character.equipment.item;

import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.framework.environment.Resources;

public class NewEquipmentTemplateAction {

  private final Resources resources;
  private final IEquipmentDatabaseManagement model;

  public NewEquipmentTemplateAction(Resources resources, IEquipmentDatabaseManagement model) {
    this.resources = resources;
    this.model = model;
  }

  public void addToolTo(EquipmentNavigation view) {
    Tool newTool = view.addEditTemplateTool();
    newTool.setIcon(new BasicUi().getNewIconPathForTaskbar());
    newTool.setTooltip(resources.getString("Equipment.Creation.Item.NewActionTooltip"));
    newTool.setCommand(new NewEquipmentItem(model, resources));
  }
}